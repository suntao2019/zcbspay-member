package com.zcbspay.platform.member.coopinsti.bean.enums;

public enum EncryptAlgorithm {
    RSA("RSA"), UNKNOW("");
    private String algName;
    private EncryptAlgorithm(String algName) {
        this.algName = algName;
    }

    public String getAlgName() {
        return algName;
    }

    public static EncryptAlgorithm fromValue(String algName) {
        if(algName==null){
            return UNKNOW;
        }
        for (EncryptAlgorithm encryptAlgorithm : EncryptAlgorithm.values()) {
            if (encryptAlgorithm.getAlgName().equals(algName)) {
                return encryptAlgorithm;
            }
        }
        return UNKNOW;
    }
}
