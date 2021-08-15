package com.geektcp.alpha.sys.auth.shiro.model;

import com.geektcp.alpha.sys.auth.model.po.SysUserPo;
import com.geektcp.alpha.sys.auth.model.vo.SysPermissionVo;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author tanghaiyang on 2018/1/4.
 */
@Data
public class UserRoleVo extends UserVo implements Serializable {
    private static final long serialVersionUID = 2L;
    protected List<RoleVo> roles;
    protected List<SysPermissionVo> permissions;

    public UserRoleVo(SysUserPo user) {
        super(user);
    }
}
