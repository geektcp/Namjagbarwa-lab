package com.geektcp.alpha.console.auth.config;

import com.geektcp.alpha.console.auth.mobile.MobileSecurityConfigurer;
import com.geektcp.alpha.console.auth.util.message.*;
import com.geektcp.alpha.console.common.core.bean.config.FilterIgnorePropertiesConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.DelegatingPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.annotation.Resource;

@EnableWebSecurity
public class SecurityConfigurerAdapter extends WebSecurityConfigurerAdapter {
    @Resource
    private FilterIgnorePropertiesConfig filterIgnorePropertiesConfig;
    @Autowired
    private MobileSecurityConfigurer mobileSecurityConfigurer;

    @Override
    public void configure(HttpSecurity http) throws Exception {
        ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry registry =
                http
//                        .exceptionHandling().accessDeniedHandler(new AuthAccessDeniedHandler())
//                        .authenticationEntryPoint(new AuthAuthenticationEntryPoint())
//                        .and()
                        .formLogin().loginPage("/authentication/require")
                        .loginProcessingUrl("/authentication/form")
                        //.successHandler(new AuthAuthenticationSuccessHandler())
                        .failureHandler(new AuthAuthenticationFailureHandler())
                        .and()
                        .logout().logoutUrl("/logout")
                        .logoutSuccessHandler(new AuthLogoutSuccessHandler())
                        .logoutSuccessUrl("/")
                        .invalidateHttpSession(true)
                        .clearAuthentication(true)
                        .deleteCookies("alpha")
                        .and()
                        .authorizeRequests();
        filterIgnorePropertiesConfig.getUrls().forEach(url -> registry.antMatchers(url).permitAll());
        registry.anyRequest().authenticated()
                .and()
                .csrf().disable();
        //?????????????????????token
        http.apply(mobileSecurityConfigurer);
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    // password ????????????????????????????????????????????????????????????
//        @Bean
//        PasswordEncoder passwordEncoder(){
//            return NoOpPasswordEncoder.getInstance();
//        }

    // password ??????????????? BCrypt ???????????????
    //    @Bean
    //    PasswordEncoder passwordEncoder(){
    //        return new BCryptPasswordEncoder();
    //    }

    @Bean
    PasswordEncoder passwordEncoder(){
        DelegatingPasswordEncoder delegatingPasswordEncoder =
                (DelegatingPasswordEncoder)  PasswordEncoderFactories.createDelegatingPasswordEncoder();
        return delegatingPasswordEncoder;


    }

}
