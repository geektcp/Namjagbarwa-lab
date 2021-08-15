package com.geektcp.alpha.common.spring.jpa;

import lombok.NonNull;

/**
 * @author tanghaiyang on 2019/1/3.
 */
public class JQL {

    public static String likeWrap(@NonNull String value) {
        return "%" + value + "%";
    }

    public static String likeWrapLeft(@NonNull String value) {
        return "%" + value;
    }

    public static String likeWrapRight(@NonNull String value) {
        return value + "%";
    }
}
