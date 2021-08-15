package com.geektcp.alpha.spring.security.constant.status;

/**
 * @author haiyang on 2020-04-18 09:53
 */
public enum LoginStatus implements Status {

    ERROR_LOGIN_FAILED(10,"password parse failed!")


    ;

    private int code;
    private String desc;

    LoginStatus(int code,String desc){
        this.code = code;
        this.desc = desc;
    }

    @Override
    public Integer getCode() {
        return code;
    }

    @Override
    public String getDesc() {
        return desc;
    }
}
