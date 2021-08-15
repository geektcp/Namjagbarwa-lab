package com.geektcp.alpha.sys.auth.shiro.model;

import com.geektcp.alpha.sys.auth.model.po.SysUserPo;
import lombok.Data;

import java.io.Serializable;

/**
 * @author tanghaiyang on 2018/1/4.
 */
@Data
public class UserVo implements Serializable {
    private static final long serialVersionUID = 2L;
    protected Long id;
    protected String userNo;
    protected String name;
    protected String password;
    protected String updateTime;

    public UserVo(SysUserPo user) {
        this.id = user.getId();
        this.name = user.getName();
        this.userNo = user.getUserNo();
    }
}
