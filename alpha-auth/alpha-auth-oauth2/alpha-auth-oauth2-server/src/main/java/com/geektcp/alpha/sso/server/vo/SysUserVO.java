package com.geektcp.alpha.sso.server.vo;

import com.geektcp.alpha.sso.server.entity.SysUser;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * @author tanghaiyang

 */
@Data
@EqualsAndHashCode(callSuper = false)
public class SysUserVO extends SysUser {

    /**
     * ๆ้ๅ่กจ
     */
    private List<String> authorityList;

}
