package com.geektcp.alpha.sso.server.service;

import com.geektcp.alpha.sso.server.entity.SysUser;

/**
 * @author tanghaiyang

 */
public interface UserService {

    SysUser getByUsername(String username);
}
