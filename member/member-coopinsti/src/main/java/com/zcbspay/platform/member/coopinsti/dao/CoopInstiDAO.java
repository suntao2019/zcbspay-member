package com.zcbspay.platform.member.coopinsti.dao;

import java.util.List;

import com.zcbspay.platform.member.coopinsti.bean.enums.TerminalAccessType;
import com.zcbspay.platform.member.coopinsti.pojo.PojoCoopInsti;
import com.zcbspay.platform.member.coopinsti.pojo.PojoInstiMK;
import com.zcbspay.platform.member.coopinsti.pojo.ProductModel;
import com.zlebank.zplatform.member.commons.dao.BaseDAO;

public interface CoopInstiDAO extends BaseDAO<PojoCoopInsti>{
    
    /**
     * check if cooperative institution is exist
     * @param instiCode
     * @return
     */
    boolean isNameExist(String instiName);
    /**
     * get PojoInstiMK entity by instiCode and terminalAccessType
     * @param instiCode
     * @param terminalAccessType
     * @return
     */
    PojoInstiMK getMKByInstiCode(String instiCode,TerminalAccessType terminalAccessType);
    /**
     * get by institution id
     * @param coopInstiID
     * @return
     */
    PojoCoopInsti get(long id);
    /**
     * get cooperative institution
     * @return
     */
    List<PojoCoopInsti> getCoopInstiList();
    /**
     * get cooperative institution by insti code
     * @param instiCode
     * @return
     */
    PojoCoopInsti getByInstiCode(String instiCode);
    /**
     * get by institution id and fetch eager
     * @param coopInstiID
     * @return
     */
    PojoCoopInsti getEager(long id);
    
    /**
     * 取得合作机构产品列表
     * @param id
     * @return
     */
    public List<ProductModel> getCoopProductList(long id);
    /**
     * 取得合作机构默认版本
     * @param instiCode
     * @param busicode
     * @param verType
     * @return
     */
    public String getDefaultVerInfo(String instiCode, String busicode,int verType) ;
}
