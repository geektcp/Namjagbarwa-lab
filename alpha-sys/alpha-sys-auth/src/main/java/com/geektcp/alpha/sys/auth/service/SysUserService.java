package com.geektcp.alpha.sys.auth.service;


import com.geektcp.alpha.common.spring.model.PageResponse;
import com.geektcp.alpha.common.spring.model.TResponse;
import com.geektcp.alpha.sys.auth.model.qo.SysUserQo;
import com.geektcp.alpha.sys.auth.model.suo.SysUserSuo;

/**
 * @author tanghaiyang on 2018/1/4.
 */
public interface SysUserService {

    PageResponse findPage(SysUserQo qo);

    boolean existsUser(String userNo);

    TResponse confirmPassword(String password);

    TResponse detail();

    TResponse save(SysUserSuo suo);

    TResponse update(SysUserSuo suo);

    TResponse delete(SysUserSuo suo);

    TResponse updatePassword(SysUserSuo suo);

}
