package com.geektcp.alpha.spring.shiro.authentication;

import com.geektcp.alpha.spring.shiro.annotation.Helper;
import org.apache.shiro.authz.AuthorizationInfo;

/**
 * @author tanghaiyang
 */
@Helper
public class ShiroHelper extends ShiroRealm {

    /**
     * 获取当前用户的角色和权限集合
     *
     * @return AuthorizationInfo
     */
    public AuthorizationInfo getCurrentuserAuthorizationInfo() {
        return super.doGetAuthorizationInfo(null);
    }
}
