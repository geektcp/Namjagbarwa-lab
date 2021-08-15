package com.geektcp.alpha.spring.querydsl.service.constant;


import alpha.common.base.constant.Status;

/**
 * Created by HaiyangWork on 2018/12/24.
 */
public enum ServiceStatus implements Status {

    GROUP_SAVE_ERROR(10001, "服务保存失败"),
    GROUP_DELETE_ERROR(10002, "服务删除失败"),
    GROUP_FIND_ERROR(10003, "服务删除失败"),

    SERVICE_SAVE_ERROR(10201, "服务保存失败"),
    SERVICE_DELETE_ERROR(10202, "服务删除失败"),

    SEARCH_SAVE_ERROR(10301, "搜索保存失败"),
    SEARCH_DELETE_ERROR(10301, "服务删除失败"),

    VERSION_SAVE_ERROR(10401, "版本保存失败"),
    VERSION_DELETE_ERROR(10402, "版本删除失败"),;

    private int code;
    private String desc;

    ServiceStatus(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    @Override
    public int getCode() {
        return code;
    }

    @Override
    public String getDesc() {
        return desc;
    }
}
