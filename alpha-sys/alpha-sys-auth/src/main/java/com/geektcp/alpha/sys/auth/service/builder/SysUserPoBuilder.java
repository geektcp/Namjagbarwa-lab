package com.geektcp.alpha.sys.auth.service.builder;


import alpha.common.base.json.JSONUtils;
import com.geektcp.alpha.sys.auth.model.po.SysUserPo;
import com.geektcp.alpha.sys.auth.model.suo.SysUserSuo;

/**
 * @author tanghaiyang on 2018/1/4.
 */
public class SysUserPoBuilder {

    public static SysUserPo get(SysUserSuo suo){
        return JSONUtils.copy(suo, SysUserPo.class);
    }

}
