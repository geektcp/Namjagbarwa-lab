package com.geektcp.alpha.spring.security.auth.handle;

import com.alibaba.fastjson.JSON;
import com.geektcp.alpha.spring.security.bean.LoginParameters;
import com.geektcp.alpha.spring.security.domain.vo.JwtVo;
import com.geektcp.alpha.spring.security.exception.BaseException;
import com.google.common.base.Throwables;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.Objects;

/**
 * Will run when user login username and pass word validate successfully.
 * There will generate a token return to response.
 */
@Service("authenticationSuccessHandler")
@Slf4j
public class SuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

    private LoginParameters loginParameters;

    @Autowired
    public void setAutowired(LoginParameters loginParameters) {
        this.loginParameters = loginParameters;
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
        log.info("User: Login successfully.");
        this.returnJson(response, authentication);
    }

    private void returnJson(HttpServletResponse response, Authentication authentication) {
        response.setStatus(HttpServletResponse.SC_OK);
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        JwtVo vo = new JwtVo();
        vo.setTokenType("Bearer");
        vo.setToken(createJwtToken(authentication));
        responseWriter(response, vo);
    }

    private void responseWriter(HttpServletResponse response, JwtVo vo) {
        try {
            response.setCharacterEncoding("utf-8");
            response.setContentType("application/json; charset=utf-8");
            BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(response.getOutputStream());
            bufferedOutputStream.write(JSON.toJSONBytes(vo));
            bufferedOutputStream.close();
        } catch (IOException e) {
            log.error(Throwables.getStackTraceAsString(e));
        }
    }

    private String createJwtToken(Authentication authentication) {
        if (Objects.isNull(authentication)) {
            throw new BaseException("authentication is null!");
        }
//        User userObject = (User) authentication.getPrincipal();
//        String username = userObject.getUsername();
        String username = authentication.getPrincipal().toString();
        Date expireTime = new Date(System.currentTimeMillis() + loginParameters.getExpiration());
        String token = Jwts.builder()
                .setSubject(username)
                .setExpiration(expireTime)
                .setIssuedAt(new Date())
                .signWith(SignatureAlgorithm.HS512, loginParameters.getEncryptSecret())
                .compact();
        log.info("token: {}", token);
        return token;
    }
}