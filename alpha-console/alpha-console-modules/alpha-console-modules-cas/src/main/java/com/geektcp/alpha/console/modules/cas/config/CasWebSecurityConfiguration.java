package com.geektcp.alpha.console.modules.cas.config;

import com.geektcp.alpha.console.modules.cas.filter.HttpParamsFilter;
import org.jasig.cas.client.session.SingleSignOutFilter;
import org.jasig.cas.client.session.SingleSignOutHttpSessionListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.cas.authentication.CasAssertionAuthenticationToken;
import org.springframework.security.cas.authentication.CasAuthenticationProvider;
import org.springframework.security.cas.userdetails.GrantedAuthorityFromAssertionAttributesUserDetailsService;
import org.springframework.security.cas.web.CasAuthenticationEntryPoint;
import org.springframework.security.cas.web.CasAuthenticationFilter;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.AuthenticationUserDetailsService;
import org.springframework.security.web.authentication.logout.LogoutFilter;
import org.springframework.web.cors.CorsUtils;

@Configuration
public class CasWebSecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    private CasAuthenticationEntryPoint casAuthenticationEntryPoint;

    @Autowired
    private CasAuthenticationProvider casAuthenticationProvider;

    @Autowired
    private CasAuthenticationFilter casAuthenticationFilter;

    @Autowired
    private LogoutFilter logoutFilter;

    @Autowired
    private CasServerConfig casServerConfig;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.headers().frameOptions().disable();

        http.csrf().disable();

        http.authorizeRequests()
                .requestMatchers(CorsUtils::isPreFlightRequest).permitAll()
                .antMatchers("/static/**").permitAll() // ?????????????????????
                .antMatchers("/api/**").permitAll()  // ???????????????API
                .anyRequest().authenticated();  // ????????????????????????????????????????????????

        http.logout().permitAll();  // ???????????????

        http.exceptionHandling().authenticationEntryPoint(casAuthenticationEntryPoint);
        // ??????????????????????????????????????????SpringSecurity??????????????????????????????????????????Web????????????????????????????????????????????????????????????????????????
        SingleSignOutFilter singleSignOutFilter = new SingleSignOutFilter();
        singleSignOutFilter.setCasServerUrlPrefix(this.casServerConfig.getHost());
        http.addFilter(casAuthenticationFilter)
                .addFilterBefore(logoutFilter, LogoutFilter.class)
                .addFilterBefore(singleSignOutFilter, CasAuthenticationFilter.class);

        http.antMatcher("/**");
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(casAuthenticationProvider);
    }

    @Bean
    public ServletListenerRegistrationBean<SingleSignOutHttpSessionListener> singleSignOutHttpSessionListener(){
        ServletListenerRegistrationBean<SingleSignOutHttpSessionListener> servletListenerRegistrationBean =
                new ServletListenerRegistrationBean<>();
        servletListenerRegistrationBean.setListener(new SingleSignOutHttpSessionListener());
        return servletListenerRegistrationBean;
    }

    @Bean
    public FilterRegistrationBean httpParamsFilter() {
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        filterRegistrationBean.setFilter(new HttpParamsFilter());
        filterRegistrationBean.setOrder(-999);
        filterRegistrationBean.addUrlPatterns("/");
        return filterRegistrationBean;
    }

    @Bean
    public AuthenticationUserDetailsService<CasAssertionAuthenticationToken> authenticationUserDetailsService() {
        String[]  attributes = new String[]{"test"};
        return new GrantedAuthorityFromAssertionAttributesUserDetailsService(attributes);
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }


}
