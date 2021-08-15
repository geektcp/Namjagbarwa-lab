package com.geektcp.alpha.sys.auth.constant;


import alpha.common.base.constant.Status;

/**
 * @author tanghaiyang on 2018/1/4.
 */
public enum AuthStatus implements Status {

    // login
    USER_UOT_AUTH(10001, "用户未授权"),
    USER_NOT_LOGINED(10002, "用户未登录"),
    USER_NOT_EXISTS(10003, "用户不存在"),
    USER_LOGOUT_FAIL(10004, "用户登出失败"),
    USER_RELOAD_PERMISSION_FAIL(10005, "用户重新加载权限失败"),USER_NOT_ADDED(10200, "当前登录用户尚未被添加到图平台"),
    USER_SAVE_ERROR(10006, "保存用户信息异常"),
    USER_IS_EXISTS(10007, "用户已存在"),
    USER_DELETE_ERROR(10008,"删除用户异常"),
    USER_UPDATE_ERROR(10009,"修改用户异常"),
    USER_ROLE_IS_EXISTS(10010,"用户已有该角色"),
    USER_SEARCH_ERROR(10011,"用户查询异常"),
    USER_LOGIN_NOT_EXISTS(10012,"登录用户不存在"),
    USER_SWITCH_ERROR(10013, "用户切换失败"),
    USER_FIND_PAGE_ERROR(10014, "查询用户异常"),
    USER_FIND_ERROR(10015, "查询用户异常"),
    USER_RESET_PSW_WRONG(10016,"修改用户异常"),
    USER_RESET_ERROR(10017,"修改用户异常"),
    USER_PERMISSION_ERROR(10018,"用户权限查询异常"),

    ROLE_SEARCH_ERROR(10101, "查询角色信息异常"),
    ROLE_NOT_EXISTS(10102, "角色不存在"),
    ROLE_NAME_EXIST(10103,"角色名称已存在"),
    ROLE_ID_IS_NULL(10104, "角色ID为空"),
    ROLE_SAVE_ERROR(10105, "查询角色信息异常"),
    ROLE_UPDATE_ERROR(10106, "查询角色信息异常"),
    ROLE_LEVEL_TYPE_ERROR(10107, "角色层级类型不正确"),
    ROLE_FIND_ERROR(10108, "查询角色信息异常"),
    ROLE_NOT_URL_AUTH(10109,"该登录角色没有访问URL的权限"),

    ROLE_RESOURCE_SAVE_ERROR(10501,"角色资源保存失败"),
    ROLE_RESOURCE_FIND_ERROR(10502,"角色资源查找失败"),
    ROLE_RESOURCE_TREE_ERROR(10503,"角色资源树构建失败"),

    USER_ROLE_LIST_SEARCH_ERROR(10301, "查询用户角色列表异常"),
    USER_ROLE_LOGIN_NOT_EXISTS(10302,"登录用户所属角色不存在"),
    USER_ROLE_SAVE_ERROR(10303, "新增用户角色关系失败"),

    FIND_SCHEMA_RESOURCE_ERROR(10401,"查询表资源目录失败"),
    FIND_SCHEMA_ERROR(10402,"查询表信息失败"),
    FIND_SCHEMA_FIELD_ERROR(10403,"查询表字段信息失败"),
    FIND_ROLE_SCHEMA_ERROR(10404,"查询角色:{}拥有的表信息失败"),
    EMPLOYEE_IS_NOT_EXISTS(10405,"未查到该员工编号对应的员工信息"),
    LOGIN_USER_UPDATE_ERROR(10406, "更新登录用户信息失败"),
    PERMISSIONS_NO_ENOUGH(10407, "权限不足"),

    PASSWORD_MISMATCH(10607, "密码错误"),
    BLANK_NAME_OR_PWD(10608, "用户名或密码为空"),
    ;

    private int code;
    private String desc;

    AuthStatus(int code, String desc) {
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
