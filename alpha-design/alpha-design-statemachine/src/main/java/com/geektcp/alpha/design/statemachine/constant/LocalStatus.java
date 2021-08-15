package com.geektcp.alpha.design.statemachine.constant;

/**
 * @author haiyang on 2020-03-31 16:33
 */
public enum LocalStatus implements Status {

    INIT(0, "初始化"),
    PROCESSING(1, "处理中"),
    SUCCESS(2, "成功"),
    FAILED(3, "失败"),
    ;

    private int code;
    private String desc;

    LocalStatus(int code, String desc) {
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

    public static LocalStatus fromState(Integer code) {
        if (code == null) {
            return null;
        }
        for (LocalStatus localStatus : LocalStatus.values()) {
            if (localStatus.getCode() == code) {
                return localStatus;
            }
        }
        return null;
    }
}
