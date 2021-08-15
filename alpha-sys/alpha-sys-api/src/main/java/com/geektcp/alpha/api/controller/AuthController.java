package com.geektcp.alpha.api.controller;


import alpha.common.base.constant.Constants;
import com.geektcp.alpha.common.spring.model.TResponse;
import com.geektcp.alpha.sys.auth.constant.AuthStatus;
import com.geektcp.alpha.sys.auth.service.SysUserService;
import com.geektcp.alpha.sys.auth.shiro.model.LoginQo;
import com.geektcp.alpha.sys.auth.shiro.model.UserRoleVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;



/**
 * @author tanghaiyang on 2018/05/22.
 */
@Slf4j
@Api(description = "[权限]-登陆、退出、用户权限")
@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private SysUserService sysUserService;

    @ApiOperation(value = "用户登录", notes = "用户登录 输入参数 {\"userNo\": \"admin\",\"password\": \"123456\",\"autoLogin\": \"N\"}")
    @PostMapping(value = "login")
    public TResponse login(@RequestBody @Valid LoginQo loginQo) {
        String userNo = loginQo.getUserNo();
        String password = loginQo.getPassword();
        if(StringUtils.isAnyBlank(userNo, password)){
            return TResponse.error(AuthStatus.BLANK_NAME_OR_PWD);
        }
        boolean rememberMe = Constants.Y.equals(loginQo.getAutoLogin());
        if (!sysUserService.existsUser(userNo)) {
            return TResponse.error(AuthStatus.USER_NOT_EXISTS);
        }
        try {
            Subject currentUser = SecurityUtils.getSubject();
            UsernamePasswordToken token = new UsernamePasswordToken(userNo, password);
            token.setRememberMe(rememberMe);
            currentUser.login(token);
            UserRoleVo user = (UserRoleVo) currentUser.getPrincipal();
            currentUser.getSession().setAttribute("userID", user.getId());
            long timeout = rememberMe ? 31536000000L : 1800000L; // 1year : 30min
            currentUser.getSession().setTimeout(timeout);
            log.info("login success!!! user:{},remember me:{},timeout:{}", userNo, rememberMe, timeout);
        } catch (Exception e) {
            log.error(e.getMessage());
            return TResponse.error(AuthStatus.PASSWORD_MISMATCH);
        }
        return TResponse.success();
    }

    @ApiOperation(value = "用户登出", notes = "用户登出")
    @RequestMapping(value = "logout", method = RequestMethod.GET)
    public TResponse logout() {
        try {
            Subject currentUser = SecurityUtils.getSubject();
            if (currentUser != null && currentUser.getPrincipal() != null) {
                currentUser.logout();
            }
        } catch (Exception e) {
            log.error(e.getMessage());
            return TResponse.error(AuthStatus.USER_LOGOUT_FAIL);
        }
        return TResponse.success();
    }

    @ApiOperation("未授权警告")
    @RequestMapping(value = "unauth", method = RequestMethod.GET)
    public TResponse unauth() {
        return TResponse.error(AuthStatus.USER_UOT_AUTH);
    }

    @ApiOperation(value = "查询用户信息", notes = "查询用户信息")
    @RequestMapping(value = "info", method = RequestMethod.GET)
    public TResponse info() {
        TResponse info = sysUserService.detail();
        if (info != null) {
            return info;
        }
        return TResponse.error().setMessage(AuthStatus.USER_NOT_LOGINED);
    }

    @ApiOperation(value = "重新加载权限", notes = "重新加载权限")
    @RequestMapping(value = "reloadPermissions", method = RequestMethod.GET)
    public TResponse reloadPermissions() {
        try {
            //userService.loginAsGuestForAnon();
            //userService.reloadPermission();
        } catch (Exception e) {
            TResponse.error(AuthStatus.USER_RELOAD_PERMISSION_FAIL);
        }
        return TResponse.success();
    }

    /*@ApiOperation(value = "页面渲染-查询用户资源权限")
    @GetMapping(value = "findPermissions")
    public TResponse findPermissions(@RequestParam Long parentId, @RequestParam(required = false) String companyKey) {
        userService.loginAsGuestForAnon();
        return userService.findPermissions(parentId, companyKey);
    }

    @ApiOperation(value = "页面渲染-查询用户资源权限")
    @GetMapping(value = "findPermissionsByParentId")
    public TResponse findPermissionsByParentId(@RequestParam Long parentId, String companyKey) {
        userService.loginAsGuestForAnon();
        return userService.findPermissionsByParentId(parentId, companyKey);
    }*/

    @ApiOperation(value = "确认密码-公用")
    @GetMapping(value = "confirmPassword")
    public TResponse confirmPassword(@RequestParam String password) {
        return sysUserService.confirmPassword(password);
    }
}
