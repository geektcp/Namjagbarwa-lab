package com.geektcp.alpha.sys.auth.service;


import com.geektcp.alpha.common.spring.model.TResponse;
import com.geektcp.alpha.sys.auth.model.uo.SysRoleResourceUo;

/**
 * @author tanghaiyang on 2018/1/4.
 */
public interface SysRoleService {

    TResponse grantResources(SysRoleResourceUo uo);

    TResponse findAllResources(Long roleId);
}
