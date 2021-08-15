package com.geektcp.alpha.spring.security.auth.filter;

import com.geektcp.alpha.spring.security.auth.handle.FailHandler;
import com.geektcp.alpha.spring.security.auth.handle.SuccessHandler;
import com.geektcp.alpha.spring.security.exception.LoginException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author haiyang on 2020-04-12 17:30
 */
@Component
@Slf4j
public class LoginFilter extends OncePerRequestFilter {

    private FailHandler failHandler;
    private SuccessHandler successHandler;

    @Autowired
    public  LoginFilter(FailHandler failHandler, SuccessHandler successHandler) {
        this.failHandler = failHandler;
        this.successHandler = successHandler;
    }

    @Override
    protected void doFilterInternal( HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {
        log.info("22222LoginFilter");
        if (!StringUtils.contains(request.getRequestURI(), "/login")) {
            chain.doFilter(request, response);
            return;
        }
        String username = request.getParameter("username");
        String password = request.getParameter("password");
//        if (StringUtils.isAnyEmpty(username, password)) {
//            failHandler.onAuthenticationFailure(request, response, new LoginException("登录失败，账户手机号为空！"));
//        }
        chain.doFilter(request, response);
    }
}
