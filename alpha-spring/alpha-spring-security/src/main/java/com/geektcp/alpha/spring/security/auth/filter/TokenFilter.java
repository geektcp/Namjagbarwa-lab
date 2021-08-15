package com.geektcp.alpha.spring.security.auth.filter;

import com.geektcp.alpha.spring.security.bean.LoginParameters;
import com.geektcp.alpha.spring.security.auth.provider.LoginProvider;
import com.geektcp.alpha.spring.security.constant.HttpHead;
import com.geektcp.alpha.spring.security.exception.BaseException;
import com.geektcp.alpha.spring.security.service.UserService;
import io.jsonwebtoken.Jwts;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by tanghaiyang
 * 22:55 2018/10/15
 */
@Component
@Slf4j
public class TokenFilter extends OncePerRequestFilter {

    private LoginProvider loginProvider;

    private LoginParameters loginParameters;

    private UserService userService;

    @Autowired
    private void setAutowired(LoginProvider loginProvider,LoginParameters loginParameters,UserService userService){
        this.loginProvider = loginProvider;
        this.loginParameters = loginParameters;
        this.userService = userService;
    }

    //1.从每个请求header获取token
    //2.调用前面写的validateToken方法对token进行合法性验证
    //3.解析得到username，并从database取出用户相关信息权限
    //4.把用户信息以UserDetail形式放进SecurityContext以备整个请求过程使用。
    // （例如哪里需要判断用户权限是否足够时可以直接从SecurityContext取出去check
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        log.info("11111TokenFilter");
        String token = getJwtFromRequest(request);
        if(StringUtils.isNoneEmpty(token)) {
            if (loginProvider.validateToken(token)) {
                String username = getUsernameFromJwt(token, loginParameters.getEncryptSecret());
                UserDetails userDetails = userService.getUserDetailByUserName(username);
                Authentication authentication = new UsernamePasswordAuthenticationToken(
                        userDetails,
                        null,
                        userDetails.getAuthorities()
                );
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }else {
                throw new BaseException("authecation failed, token is invalid!");
            }
        }
        chain.doFilter(request, response);
    }

    ///////////////////////////////////////////////////////////////////
    /**
     * Get Bear jwt from request header Authorization.
     *
     * @param request servlet request.
     * @return token or null.
     */
    private String getJwtFromRequest(HttpServletRequest request) {
        String token = request.getHeader(HttpHead.HEAD_NAME);
        if (token != null && token.startsWith(HttpHead.TOKEN_PREFIX)) {
            return token.replace(HttpHead.TOKEN_PREFIX, "");
        }
        return null;
    }

    /**
     * Get user name from Jwt, the user name have set to jwt when generate token.
     *
     * @param token jwt token.
     * @param signKey jwt sign key, set in properties file.
     * @return user name.
     */
    private String getUsernameFromJwt(String token, String signKey) {
        return Jwts.parser().setSigningKey(signKey)
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }
}
