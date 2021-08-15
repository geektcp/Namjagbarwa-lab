package com.geektcp.alpha.sys.auth.shiro;

import com.geektcp.alpha.sys.auth.util.AuthUtils;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.CredentialsMatcher;

/**
 * @author tanghaiyang on 2018/1/4.
 */
public class UserCredentialsMatcher implements CredentialsMatcher {

    @Override
    public boolean doCredentialsMatch(AuthenticationToken token, AuthenticationInfo info) {
        UsernamePasswordToken pwdToken = (UsernamePasswordToken) token;
        return AuthUtils.validPassword((new String(pwdToken.getPassword())), (String) info.getCredentials());
    }
}
