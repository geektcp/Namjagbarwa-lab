package com.geektcp.alpha.sys.auth.shiro;

import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.apache.shiro.web.mgt.CookieRememberMeManager;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;

import javax.servlet.Filter;
import java.util.LinkedHashMap;

/**
 * @author tanghaiyang on 2018/1/4.
 */
//@Configuration
public class ShiroConfig {

    @Value("${server.session.timeout:1800}")
    private int timeout;// seconds

    @Bean
    public ChainDefinitionProvider chainDefinitionProvider() {
        return new ChainDefinitionProvider();
    }

    @Bean
    public UserRealm userRealm() {
        return new UserRealm(new UserCredentialsMatcher());
    }

    @Bean
    public EhCacheManager ehCacheManager() {
        EhCacheManager cacheManager = new EhCacheManager();
        cacheManager.setCacheManagerConfigFile("classpath:ehcache.xml");
        return cacheManager;
    }

    @Bean
    public DefaultWebSessionManager sessionManager() {
        DefaultWebSessionManager sessionManager = new DefaultWebSessionManager();
        sessionManager.setDeleteInvalidSessions(true);
//        sessionManager.setCacheManager(ehCacheManager());
        sessionManager.setGlobalSessionTimeout(timeout * 1000);// milliseconds
        return sessionManager;
    }

    @Bean
    public SecurityManager securityManager() {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setSessionManager(sessionManager());
//        securityManager.setCacheManager(ehCacheManager());
        securityManager.setRememberMeManager(cookieRememberMeManager());
        securityManager.setRealm(userRealm());
        return securityManager;
    }

    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(SecurityManager securityManager) {
        ShiroFilterFactoryBean bean = new ShiroFilterFactoryBean();
        bean.setSecurityManager(securityManager);
        LinkedHashMap<String, Filter> filters = new LinkedHashMap<>();

        filters.put("cookie", cookieFilter());
        filters.put("roles", new UserRolesAuthorizationFilter());

        bean.setFilters(filters);

        bean.setLoginUrl("/auth/login");
        bean.setSuccessUrl("/index");
        bean.setUnauthorizedUrl("/auth/unauth");

        bean.setFilterChainDefinitionMap(chainDefinitionProvider().getAllRolesPermissions());
        return bean;
    }

    @Bean
    public FormAuthenticationFilter cookieFilter() {
        FormAuthenticationFilter filter = new FormAuthenticationFilter();
        filter.setRememberMeParam("alpha-gap");
        return filter;
    }

    @Bean
    public SimpleCookie simpleCookie() {
        SimpleCookie cookie = new SimpleCookie();
        cookie.setHttpOnly(true);
        cookie.setMaxAge(43200);// 12hours
//        cookie.setMaxAge(Cookie.ONE_YEAR);
//        cookie.setMaxAge(60);
        cookie.setName("alpha-gap");
        cookie.setPath("/");
        return cookie;
    }

    @Bean
    public CookieRememberMeManager cookieRememberMeManager() {
        CookieRememberMeManager manager = new CookieRememberMeManager();
        manager.setCookie(simpleCookie());
        //manager.setCipherKey(Base64.decode("2AvVhdsgUs0FSA3SDFAdag=="));
        return manager;
    }


    /*
    * enable doGetAuthorizationInfo
    * */
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
        return authorizationAttributeSourceAdvisor;
    }

}
