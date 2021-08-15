package com.geektcp.alpha.spring.security.controller;

import com.geektcp.alpha.spring.security.annotation.Anonymous;
import com.geektcp.alpha.spring.security.bean.SecurityProperties;
import com.geektcp.alpha.spring.security.auth.provider.LoginProvider;
import com.geektcp.alpha.spring.security.constant.status.LoginStatus;
import com.geektcp.alpha.spring.security.domain.TResponse;
import com.geektcp.alpha.spring.security.domain.qo.LoginQo;
import com.geektcp.alpha.spring.security.domain.vo.LoginVo;
import com.geektcp.alpha.spring.security.exception.BaseException;
import com.geektcp.alpha.spring.security.util.EncryptUtils;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
/**
 * @author tanghaiyang
 * 22:56 2018/9/2
 */
@RestController
@RequestMapping("/auth")
@Slf4j
public class AuthController {

    private SecurityProperties properties;
    private LoginProvider loginProvider;

    @Autowired
    public AuthController(SecurityProperties properties, LoginProvider loginProvider) {
        this.loginProvider = loginProvider;
        this.properties = properties;
    }

    @ApiOperation("登录授权")
    @Anonymous
    @PostMapping(value = "/login")
    public TResponse<LoginVo> login(@Validated @RequestBody LoginQo loginQo) {
        String password;
        try {
            password = EncryptUtils.decrypt(loginQo.getEncryptPassword());
            if(StringUtils.isEmpty(password)){
                return new TResponse<>(LoginStatus.ERROR_LOGIN_FAILED);
            }
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new BaseException("密码解析错误!");
        }
        try {
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                    loginQo.getUsername(), password);
            Authentication authentication = loginProvider.authenticate(authenticationToken);
            SecurityContextHolder.getContext().setAuthentication(authentication);
            String token = loginProvider.createToken(authentication);
            String username = authentication.getPrincipal().toString();
            LoginVo vo = new LoginVo();
            vo.setToken(token);
            vo.setUsername(username);
            return new TResponse<>(vo);
        } catch (Exception e) {
            throw new BaseException(e.getMessage());
        }
    }

}
