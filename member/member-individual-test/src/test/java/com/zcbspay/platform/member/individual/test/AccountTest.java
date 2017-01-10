/* 
 * AccountTest.java  
 * 
 * version TODO
 *
 * 2016年11月7日 
 * 
 * Copyright (c) 2016,zlebank.All rights reserved.
 * 
 */
package com.zcbspay.platform.member.individual.test;



import org.junit.Test;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.fastjson.JSONObject;
import com.zcbspay.platform.member.individual.bean.MemberAccountBean;
import com.zcbspay.platform.member.individual.bean.MemberBean;
import com.zcbspay.platform.member.individual.bean.PoMemberApplyBean;
import com.zcbspay.platform.member.individual.bean.enums.MemberType;
import com.zcbspay.platform.member.individual.service.MemberAccountService;
import com.zcbspay.platform.member.individual.service.MemberService;
import com.zlebank.zplatform.acc.bean.BusiAcctQuery;
import com.zlebank.zplatform.acc.bean.enums.Usage;
import com.zlebank.zplatform.acc.service.AccountQueryService;

/**
 * Class Description
 *
 * @author houyong
 * @version
 * @date 2016年11月7日 上午9:26:46
 * @since 
 */
//@Component
public class AccountTest extends BaseTest{
    
    @Reference(version="1.0")
    private AccountQueryService accountQuery;
    @Reference(version="1.0")
    private MemberService memberServie;
    @Reference(version="1.0")
    private MemberAccountService memberAccountService;
    @Test
    public void accountQueryService(){
        
       try {                                                    
           BusiAcctQuery accts=accountQuery.getBusiQueryBybCode("9010702200000000001223");
           System.out.println(JSONObject.toJSONString(accts));
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
    }
    //@Test
    public void memberService(){
          try {
              //BusiAcctQuery query=  memberServie.getBusiQueryBybCode("6010101100000000000616");
              //System.out.println(JSONObject.toJSONString(query));
              PoMemberApplyBean queryApply=  memberServie.getMemberApply("200000000000737");
              System.out.println(JSONObject.toJSONString(queryApply));
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
    }
   // @Test
    public void memberAccountService(){
          try {
              MemberBean memberBean=new MemberBean();
              memberBean.setMemberId("200000000000611");
              MemberAccountBean query=  memberAccountService.queryBalance(MemberType.ENTERPRISE,memberBean, Usage.BASICPAY);
              System.out.println(JSONObject.toJSONString(query));
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
    }
}
