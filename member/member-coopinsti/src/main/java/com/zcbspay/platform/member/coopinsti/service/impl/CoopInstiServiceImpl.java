package com.zcbspay.platform.member.coopinsti.service.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.hibernate.HibernateException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.dubbo.config.annotation.Reference;
import com.zcbspay.platform.member.coopinsti.bean.CoopInsti;
import com.zcbspay.platform.member.coopinsti.bean.CoopInstiBusi;
import com.zcbspay.platform.member.coopinsti.bean.CoopInstiMK;
import com.zcbspay.platform.member.coopinsti.bean.enums.EncryptAlgorithm;
import com.zcbspay.platform.member.coopinsti.bean.enums.TerminalAccessType;
import com.zcbspay.platform.member.coopinsti.dao.CoopInstiDAO;
import com.zcbspay.platform.member.coopinsti.exception.OpenCoopInstiBusiAcctException;
import com.zcbspay.platform.member.coopinsti.pojo.PojoCoopInsti;
import com.zcbspay.platform.member.coopinsti.pojo.PojoInstiMK;
import com.zcbspay.platform.member.coopinsti.service.CoopInstiService;
import com.zcbspay.platform.member.exception.AbstractCoopInstiException;
import com.zcbspay.platform.member.exception.GenerateCoopInstiCodeException;
import com.zcbspay.platform.member.exception.GenerateCoopInstiMKException;
import com.zcbspay.platform.member.exception.InstiNameExistedException;
import com.zcbspay.platform.member.individual.service.PrimayKeyService;
import com.zlebank.zplatform.acc.bean.BusiAcct;
import com.zlebank.zplatform.acc.bean.BusiAcctSubjectMappingBean;
import com.zlebank.zplatform.acc.exception.AbstractAccException;
import com.zlebank.zplatform.acc.exception.AbstractBusiAcctException;
import com.zlebank.zplatform.acc.service.BusiAcctService;
import com.zlebank.zplatform.acc.service.SubjectSelector;
import com.zlebank.zplatform.member.commons.enums.BusinessActorType;
import com.zlebank.zplatform.member.commons.utils.BeanCopyUtil;
import com.zlebank.zplatform.member.commons.utils.RSAUtils;
import com.zlebank.zplatform.member.exception.PrimaykeyGeneratedException;

@Service
public class CoopInstiServiceImpl implements CoopInstiService {

    @Autowired
    private CoopInstiDAO coopInstiDAO;
    @Reference(version="1.0")
    private PrimayKeyService primayKeyService;
    @Reference(version="1.0")
    private SubjectSelector subjectSelector;
    @Reference(version="1.0")
    private BusiAcctService busiAcctService;

    private static final String COOPINST_PARA_TYPE = "COOPINSTIBIN";
    private static final String COOPINST_CODE_SEQ = "SEQ_COOP_INSTI_CODE";

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
    public CoopInstiMK getCoopInstiMK(String instiCode,
            TerminalAccessType terminalAccessType) {

        PojoInstiMK pojoInstiMK = coopInstiDAO.getMKByInstiCode(instiCode,
                terminalAccessType);
        if (pojoInstiMK == null) {
            return null;
        }
        CoopInstiMK coopInstiMK = new CoopInstiMK(pojoInstiMK.getCoopInsti()
                .getInstiCode());
        BeanUtils.copyProperties(pojoInstiMK, coopInstiMK, new String[]{"id",
                "coopInsti"});
        return coopInstiMK;
    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
    public String createCoopInsti(String instiName, long userId)
            throws AbstractCoopInstiException {

        /*
         * check if instiName is repeat
         */
        instiName = instiName.trim();
        if (coopInstiDAO.isNameExist(instiName)) {
            throw new InstiNameExistedException();
        }

        /*
         * generate coop insti code
         */
        String coopInstiCode;
        try {
            coopInstiCode = primayKeyService.getNexId(COOPINST_PARA_TYPE,
                    COOPINST_CODE_SEQ);
        } catch (PrimaykeyGeneratedException pge) {
            GenerateCoopInstiCodeException geice = new GenerateCoopInstiCodeException();
            geice.initCause(pge);
            throw geice;
        }
        /*
         * instance and init a pojos
         */
        PojoCoopInsti pojoCoopInsti = new PojoCoopInsti();
        pojoCoopInsti.setInstiCode(coopInstiCode);
        pojoCoopInsti.setInstiName(instiName);
        pojoCoopInsti.setInUserId(userId);
        pojoCoopInsti.setInTime(new Timestamp(new Date().getTime()));
        pojoCoopInsti.setStatus("00");
        List<PojoInstiMK> instiMKs = new ArrayList<PojoInstiMK>();

        /*
         * generate institution key
         */
        try {

            for (TerminalAccessType terminalAccessType : TerminalAccessType
                    .values()) {
                if (terminalAccessType == TerminalAccessType.UNKNOW) {
                    continue;
                }
                Map<String, Object> coopInsti_keyMap = RSAUtils.genKeyPair();
                Map<String, Object> plath_keyMap = RSAUtils.genKeyPair();
                String coopInsti_publicKey = RSAUtils
                        .getPublicKey(coopInsti_keyMap);
                String coopInsti_privateKey = RSAUtils
                        .getPrivateKey(coopInsti_keyMap);
                String plath_publicKey = RSAUtils.getPublicKey(plath_keyMap);
                String plath_privateKey = RSAUtils.getPrivateKey(plath_keyMap);

                PojoInstiMK instiMK = new PojoInstiMK();
                instiMK.setCoopInsti(pojoCoopInsti);
                instiMK.setEncryptAlgorithm(EncryptAlgorithm.RSA);
                instiMK.setTerminalAccessType(terminalAccessType);
                instiMK.setInstiPriKey(coopInsti_privateKey);
                instiMK.setInstiPubKey(coopInsti_publicKey);
                instiMK.setZplatformPriKey(plath_privateKey);
                instiMK.setZplatformPubKey(plath_publicKey);
                instiMKs.add(instiMK);
            }
            pojoCoopInsti.setInstisMKs(instiMKs);
        } catch (Exception e) {
            e.printStackTrace();
            throw new GenerateCoopInstiMKException();
        }
        // open institution account
        try {
            openDefaultBusiAcct(instiName, coopInstiCode, userId);
        } catch (Exception e) {
            e.printStackTrace();
            OpenCoopInstiBusiAcctException cie = new OpenCoopInstiBusiAcctException(
                    e.getMessage());
            throw cie;
        }

        // prestance to db
        coopInstiDAO.saveEntity(pojoCoopInsti);
        return pojoCoopInsti.getInstiCode();
    }
    
    /**
     * 为机构开通所有默认开通的账户
     * @param instiName
     * @param coopInstiCode
     * @param userId
     * @throws AbstractAccException
     * @throws AbstractBusiAcctException
     */
    private void openDefaultBusiAcct(String instiName,String coopInstiCode,long userId)
            throws Exception /*AbstractAccException, AbstractBusiAcctException*/ {
        
        List<BusiAcctSubjectMappingBean> busiAcctSubjectMappings = subjectSelector
                .getDefaultList(BusinessActorType.COOPINSTI);
        BusiAcct busiAcct;
        CoopInstiBusi businessActor;
        for (BusiAcctSubjectMappingBean busiAcctSubjectMapping : busiAcctSubjectMappings) {
            busiAcct = new BusiAcct();
            busiAcct.setBusiAcctName(instiName);
            busiAcct.setUsage(busiAcctSubjectMapping.getUsage());
            businessActor = new CoopInstiBusi();
            businessActor.setBusinessActorId(coopInstiCode);
            busiAcctService.openBusiAcct(BeanCopyUtil.copyBean(com.zlebank.zplatform.member.commons.service.impl.BusinessActor.class, businessActor), busiAcct, userId);
        }
    } 

    @Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
    public List<CoopInsti> getAllCoopInsti() {
        List<PojoCoopInsti> pojoCoopInstis = coopInstiDAO.getCoopInstiList();
        List<CoopInsti> coopInstis = new ArrayList<CoopInsti>();
        String[] ignoreProperties = new String[]{"instisMKs"};
        for (PojoCoopInsti copyFrom : pojoCoopInstis) {
            CoopInsti copyTo = new CoopInsti();
            BeanUtils.copyProperties(copyFrom, copyTo, ignoreProperties);
            coopInstis.add(copyTo);
        }
        return coopInstis;
    }

    @Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
    public CoopInsti getInstiByInstiCode(String instiCode) {
        PojoCoopInsti pojoCoopInsit = null;
        try {
            pojoCoopInsit = coopInstiDAO.getByInstiCode(instiCode);
        } catch (HibernateException e) {
            e.printStackTrace();
            return null;
        }
        if (pojoCoopInsit == null) {
            return null;
        }
        CoopInsti copyTo = new CoopInsti();
        String[] ignoreProperties = new String[]{"instisMKs"};
        BeanUtils.copyProperties(pojoCoopInsit, copyTo, ignoreProperties);
        return copyTo;
    }

    @Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
    public CoopInsti getInstiByInstiID(long instiID) {
        PojoCoopInsti pojoCoopInsit = null;
        try {
            pojoCoopInsit = coopInstiDAO.get(instiID);
        } catch (HibernateException e) {
            e.printStackTrace();
            return null;
        }
        if (pojoCoopInsit == null) {
            return null;
        }
        CoopInsti copyTo = new CoopInsti();
        String[] ignoreProperties = new String[]{"instisMKs"};
        BeanUtils.copyProperties(pojoCoopInsit, copyTo, ignoreProperties);
        return copyTo;
    }
    
   
}
