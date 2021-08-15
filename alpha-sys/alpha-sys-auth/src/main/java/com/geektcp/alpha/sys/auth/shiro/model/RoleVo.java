package com.geektcp.alpha.sys.auth.shiro.model;

import lombok.Data;

import java.io.Serializable;

/**
 * @author tanghaiyang on 2018/1/4.
 */
@Data
public class RoleVo implements Serializable {
    private static final long serialVersionUID = 2L;
    protected Long id;
    protected String name;
    protected String updateTime;
    protected String desc;
}
