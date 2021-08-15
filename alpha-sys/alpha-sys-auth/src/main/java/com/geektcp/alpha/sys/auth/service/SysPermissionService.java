package com.geektcp.alpha.sys.auth.service;


import com.geektcp.alpha.sys.auth.model.vo.SysPermissionVo;

/**
 * @author tanghaiyang on 2018/1/4.
 */
public interface SysPermissionService {

    SysPermissionVo findPermissions(Long userId);


}
