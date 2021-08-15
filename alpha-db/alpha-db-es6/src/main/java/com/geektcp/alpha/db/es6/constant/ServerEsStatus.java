package com.geektcp.alpha.db.es6.constant;



public enum ServerEsStatus implements Status {

    SEARCH_ERROR(1, "通用搜索条件错误"),
    SEARCHBYIDS_ERROR(2, "主键搜索条件错误"),
    ;

    private int code;
    private String desc;

    ServerEsStatus(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    @Override
    public int getCode() {
        return this.code;
    }

    @Override
    public String getDesc() {
        return this.desc;
    }
}
