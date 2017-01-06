package com.zcbspay.platform.member.exception;

import java.util.ResourceBundle;

import com.zcbspay.platform.member.commons.exception.AbstractDescribeException;

public abstract class AbstractCoopInstiException extends AbstractDescribeException{

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = -6135706535011218528L;
    
    private static final  ResourceBundle RESOURCE = ResourceBundle.getBundle("coopinstiexception_des");
	@Override
	public ResourceBundle getResourceBundle() {
		return RESOURCE;
	}
}
