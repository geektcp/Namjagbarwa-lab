package com.geektcp.alpha.spring.security.auth;

import com.geektcp.alpha.spring.security.auth.filter.LoginFilter;
import com.geektcp.alpha.spring.security.auth.filter.TokenFilter;
import com.geektcp.alpha.spring.security.auth.handle.FailHandler;
import com.geektcp.alpha.spring.security.auth.handle.SuccessHandler;
import com.geektcp.alpha.spring.security.auth.provider.LoginProvider;
import com.geektcp.alpha.spring.security.service.MyUserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 *
 */
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class MyConfig extends WebSecurityConfigurerAdapter{

    private MyUserDetailService myUserDetailService;

    private SuccessHandler successHandler;

    private FailHandler failHandler;

    private AuthenticationEntryPoint entryPoint;

    private TokenFilter tokenFilter;
    private LoginFilter loginFilter;
    private LoginProvider provider;

    private static final String[] IGNORE_PATHS = {
            "/auth/**",
            "/login/**",
            "/api/**"
    };

    @Autowired
    public void setAutowired(MyUserDetailService myUserDetailService,
                    SuccessHandler successHandler,
                    FailHandler failHandler,
                    AuthenticationEntryPoint entryPoint,
                    TokenFilter tokenFilter,
                    LoginFilter loginFilter,
                    LoginProvider provider
                             ){
        this.myUserDetailService = myUserDetailService;
        this.successHandler = successHandler;
        this.failHandler = failHandler;
        this.entryPoint = entryPoint;
        this.tokenFilter = tokenFilter;
        this.loginFilter = loginFilter;
        this.provider = provider;
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http
                .addFilterBefore(tokenFilter, UsernamePasswordAuthenticationFilter.class)
                .addFilterBefore(loginFilter, UsernamePasswordAuthenticationFilter.class)
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and().csrf().disable()

                .authorizeRequests().antMatchers(IGNORE_PATHS).permitAll()

                .anyRequest().authenticated().and().formLogin().loginProcessingUrl("/login")

                .successHandler(successHandler).failureHandler(failHandler).permitAll().and().logout()
                .and().exceptionHandling().authenticationEntryPoint(entryPoint);

        http.authenticationProvider(provider);
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(myUserDetailService);
    }
}
