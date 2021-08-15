package com.geektcp.alpha.sso.server.service;

import com.geektcp.alpha.sso.server.entity.SysPermission;

import java.util.List;

/**
 * @author tanghaiyang

 */
public interface PermissionService {

    List<SysPermission> findByUserId(Integer userId);

}
