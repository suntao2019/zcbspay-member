/* 
 * MemberAccountService.java  
 * 
 * version TODO
 *
 * 2016年1月15日 
 * 
 * Copyright (c) 2016,zlebank.All rights reserved.
 * 
 */
package com.zcbspay.platform.member.individual.service;

import com.zcbspay.platform.member.individual.bean.MemberAccountBean;
import com.zcbspay.platform.member.individual.bean.MemberBalanceDetailBean;
import com.zcbspay.platform.member.individual.bean.MemberBean;
import com.zcbspay.platform.member.individual.bean.enums.MemberType;
import com.zcbspay.platform.member.individual.exception.GetAccountFailedException;
import com.zlebank.zplatform.acc.bean.enums.Usage;
import com.zlebank.zplatform.member.commons.bean.PagedResult;
import com.zlebank.zplatform.member.exception.DataCheckFailedException;

/**
 * 会员账户相关服务
 *
 * @author Luxiaoshuai
 * @version
 * @date 2016年1月15日 下午2:51:54
 * @since 
 */
public interface MemberAccountService {
    /**
     * 查询余额
     * @param memberType
     * @param member
     * @return
     * @throws DataCheckFailedException 
     * @throws GetAccountFailedException 
     */
    public MemberAccountBean queryBalance (MemberType memberType, MemberBean member, Usage usage) throws DataCheckFailedException, GetAccountFailedException ;
    /**
     * 查询收支明细
     * @param memberType
     * @param member
     * @param page
     * @param pageSize
     * @return
     * @throws GetAccountFailedException 
     */
    public PagedResult<MemberBalanceDetailBean> queryBalanceDetail (MemberType memberType, MemberBean member, int page, int pageSize) throws GetAccountFailedException ;
}
