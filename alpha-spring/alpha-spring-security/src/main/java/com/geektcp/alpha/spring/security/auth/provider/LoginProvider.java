package com.geektcp.alpha.spring.security.auth.provider;

import com.geektcp.alpha.spring.security.bean.LoginParameters;
import com.geektcp.alpha.spring.security.bean.SecurityProperties;
import com.geektcp.alpha.spring.security.constant.HttpHead;
import com.geektcp.alpha.spring.security.exception.BaseException;
import com.geektcp.alpha.spring.security.service.UserService;
import com.geektcp.alpha.spring.security.util.EncryptUtils;
import io.jsonwebtoken.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.Objects;
import java.util.stream.Collectors;

@Component
@Slf4j
public class LoginProvider implements AuthenticationProvider {

    private static final String CACHE_PRE = "login";

    private LoginParameters loginParameters;
    private SecurityProperties properties;
    private UserService userService;
    private static final String AUTHORITIES_KEY = "auth";

    @Autowired
    public void setAutowired(UserService userService, LoginParameters loginParameters,SecurityProperties properties) {
        this.userService = userService;
        this.loginParameters = loginParameters;
        this.properties = properties;
    }

//    @Cacheable(cacheNames = "loginCache", key = "'login.'+#p0")
    public boolean validateToken(String token) {
        try {
            Jwts.parser().setSigningKey(EncryptUtils.buildKey(loginParameters.getEncryptSecret())).parseClaimsJws(token);
            log.info("check authorization success!");
            return true;
        } catch (Exception ex) {
            log.error("validate failed : {}", ex.getMessage());
            return false;
        }
    }

    @Override
    public Authentication authenticate(Authentication authentication) {
        log.info("33333333323");
        UserDetails userDetails = userService.getUserDetailByUserName(authentication.getPrincipal().toString());
        if(Objects.isNull(userDetails)){
            throw new BaseException("userDetails is null!");
        }
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return new LoginToken(userDetails, userDetails.getAuthorities());
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return false;
    }

    public String createToken(Authentication authentication) {
        String authorities = authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(","));
        Date expireTime = new Date(System.currentTimeMillis() + loginParameters.getExpiration());
        String username = authentication.getName();
        String token = Jwts.builder()
                .setSubject(username)
                .setExpiration(expireTime)
                .claim(AUTHORITIES_KEY, authorities)
                .signWith(EncryptUtils.buildKey(loginParameters.getEncryptSecret()), SignatureAlgorithm.HS512)
                .compact();
        log.info("token: {}", token);
        return HttpHead.TOKEN_PREFIX + token;
    }


    ////////////////////////////////////////////////////////////
    private Authentication getAuthentication(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(loginParameters.getEncryptSecret())
                .parseClaimsJws(token)
                .getBody();

        Collection<? extends GrantedAuthority> authorities =
                Arrays.stream(claims.get(AUTHORITIES_KEY).toString().split(","))
                        .map(SimpleGrantedAuthority::new)
                        .collect(Collectors.toList());

        User principal = new User(claims.getSubject(), "", authorities);

        return new UsernamePasswordAuthenticationToken(principal, token, authorities);
    }

    private boolean validateToken2(String authToken) {
        try {
            Jwts.parser().setSigningKey(loginParameters.getEncryptSecret()).parseClaimsJws(authToken);
            return true;
        } catch (io.jsonwebtoken.security.SecurityException | MalformedJwtException e) {
            log.error("Invalid JWT signature: {}", e.getMessage());
        } catch (ExpiredJwtException e) {
            log.error("Expired JWT token: {}", e.getMessage());
        } catch (UnsupportedJwtException e) {
            log.error("Unsupported JWT token: {}", e.getMessage());
        } catch (IllegalArgumentException e) {
            log.error("JWT token compact of handler are invalid: {}", e.getMessage());
        }
        return false;
    }

    private String getToken(HttpServletRequest request) {
        final String requestHeader = request.getHeader(properties.getHeader());
        if (requestHeader != null && requestHeader.startsWith(properties.getTokenStartWith())) {
            return requestHeader.substring(7);
        }
        return null;
    }

}
