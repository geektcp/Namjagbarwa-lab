package com.geektcp.alpha.sys.auth.shiro;

import com.geektcp.alpha.sys.auth.dao.SysUserDao;
import com.geektcp.alpha.sys.auth.model.po.SysUserPo;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.*;
import org.apache.shiro.authc.credential.CredentialsMatcher;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.HashSet;
import java.util.Set;

/**
 * @author tanghaiyang on 2018/1/4.
 */
@Slf4j
public class UserRealm extends AuthorizingRealm {

    @Autowired
//    @Lazy
    private SysUserDao sysUserDao;

    public UserRealm(CredentialsMatcher matcher) {
        super(matcher);
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        log.info("授权验证");
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        Set<String> roleSet = new HashSet<>();
        Set<String> permissionSet = new HashSet<>();
        String currentUrl = getCurrentUrl();

//        WebApplicationContext webApplicationContext = ContextLoader.getCurrentWebApplicationContext();
//        ServletContext servletContext = webApplicationContext.getServletContext();
//        String path = servletContext.getContextPath();
//        LOG.info(path);

//        SysUserPo userRoleDto = (SysUserPo) principals.getPrimaryPrincipal();
//        if (userRoleDto.getRoles() != null) {
//            for (RoleVo role : userRoleDto.getRoles()) {
//                roleSet.add(role.getId().toString());
//            }
//        }
        authorizationInfo.setRoles(roleSet);
        authorizationInfo.setStringPermissions(permissionSet);
        return authorizationInfo;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        UsernamePasswordToken credential = (UsernamePasswordToken) token;
        SysUserPo user = sysUserDao.findByUserNo(credential.getUsername());
        if (user == null) { throw new AccountException();}
        return new SimpleAuthenticationInfo(user, user.getPassword(), user.getUserNo());
    }


    private String getCurrentUrl(){
        HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.currentRequestAttributes()).getRequest();
        return request.getServletPath();
    }

}
