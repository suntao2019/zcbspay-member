package com.zcbspay.platform.member.coopinsti.bean;

import java.io.Serializable;

import com.zcbspay.platform.member.coopinsti.bean.enums.EncryptAlgorithm;
import com.zcbspay.platform.member.coopinsti.bean.enums.TerminalAccessType;

/**
 * 
 * CoopInstiMK
 *
 * @author yangying
 * @version
 * @date 2016年1月13日 下午4:43:12
 * @since
 */
public class CoopInstiMK implements Serializable{

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = -7532675218623895174L;
    private String coopInstiCode;
    private EncryptAlgorithm encryptAlgorithm;
    private TerminalAccessType terminalAccessType;
    private String zplatformPriKey;
    private String zplatformPubKey;
    private String instiPriKey;
    private String instiPubKey;

    public CoopInstiMK() {
    }

    public CoopInstiMK(String coopInstiCode) {
        this.coopInstiCode = coopInstiCode;
    }

    public String getCoopInstiCode() {
        return coopInstiCode;
    }
    public EncryptAlgorithm getEncryptAlgorithm() {
        return encryptAlgorithm;
    }
    public TerminalAccessType getTerminalAccessType() {
        return terminalAccessType;
    }
    public String getZplatformPriKey() {
        return zplatformPriKey;
    }
    public String getZplatformPubKey() {
        return zplatformPubKey;
    }
    public String getInstiPriKey() {
        return instiPriKey;
    }
    public String getInstiPubKey() {
        return instiPubKey;
    }

    public void setEncryptAlgorithm(EncryptAlgorithm encryptAlgorithm) {
        this.encryptAlgorithm = encryptAlgorithm;
    }

    public void setTerminalAccessType(TerminalAccessType terminalAccessType) {
        this.terminalAccessType = terminalAccessType;
    }

    public void setZplatformPriKey(String zplatformPriKey) {
        this.zplatformPriKey = zplatformPriKey;
    }

    public void setZplatformPubKey(String zplatformPubKey) {
        this.zplatformPubKey = zplatformPubKey;
    }

    public void setInstiPriKey(String instiPriKey) {
        this.instiPriKey = instiPriKey;
    }

    public void setInstiPubKey(String instiPubKey) {
        this.instiPubKey = instiPubKey;
    }
}
