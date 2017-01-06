/* 
 * MemberBankCardService.java  
 * 
 * version TODO
 *
 * 2016年1月15日 
 * 
 * Copyright (c) 2016,zlebank.All rights reserved.
 * 
 */
package com.zcbspay.platform.member.individual.service;

import com.zcbspay.platform.member.individual.bean.QuickpayCustBean;
import com.zcbspay.platform.member.individual.bean.RealNameBean;
import com.zlebank.zplatform.member.commons.bean.PagedResult;
import com.zlebank.zplatform.member.exception.DataCheckFailedException;
import com.zlebank.zplatform.member.exception.UnbindBankFailedException;

/**
 * 会员银行卡相关服务
 *
 * @author Luxiaoshuai
 * @version
 * @date 2016年1月15日 下午2:50:27
 * @since 
 */
public interface MemberBankCardService {
    /**
     * 保存实名认证信息
     * @param bean
     */
    public void saveRealNameInfo(RealNameBean bean);

    /**
     * 查询实名认证信息【根据会员ID】
     * @param bean
     */
    public RealNameBean queryRealNameInfo(RealNameBean bean);
    
    /**
     * 保存银行卡绑卡信息
     * @param bean
     * @ Return long 绑卡ID
     */
    public long saveQuickPayCust(QuickpayCustBean bean);
    
    /**
     * 银行卡解绑
     * @param bean
     * @throws DataCheckFailedException 
     * @throws UnbindBankFailedException 
     */
    public void unbindQuickPayCust(QuickpayCustBean bean) throws DataCheckFailedException, UnbindBankFailedException;
    
    /**
     * 查询签约银行卡信息（会员）
     * @param memberId 会员号
     * @param cardType 卡类型 
     *          0：借记卡+贷记卡
     *          1：借记卡
     *          2：贷记卡
     * @return
     */
    public PagedResult<QuickpayCustBean> queryMemberBankCard(String memberId, String cardType, String devId,int offset,  int pageSize);
    /**
     * 保存银行卡绑卡信息
     * @param bean
     * @ Return long 绑卡ID
     */
    public long saveQuickPayCustExt(QuickpayCustBean bean);
    
    /**
     * 根据主键取得银行卡绑卡信息
     * @param id
     * @return
     */
    public QuickpayCustBean getMemberBankCardById(Long id);
    
    /**
     * 通过会员ID，卡号，身份证号，账户名，手机号得到绑卡对象
     * @param memberId
     * @param cardNo
     * @param idnum
     * @param accname
     * @param phone
     * @return
     */
    public QuickpayCustBean getCardList(String cardNo, String accName,String phone, String cerId, String memberId);
    
}
