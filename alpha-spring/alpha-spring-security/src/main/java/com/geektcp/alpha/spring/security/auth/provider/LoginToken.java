package com.geektcp.alpha.spring.security.auth.provider;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

/**
 * @author haiyang on 2020-04-13 10:01
 */
public class LoginToken extends AbstractAuthenticationToken {

    public LoginToken(UserDetails userDetails, Collection<? extends GrantedAuthority> authorities) {
        super(authorities);
        setAuthenticated(true);
        setDetails(userDetails);
    }

    @Override
    public Object getCredentials() {
        return null;
    }

    @Override
    public String getPrincipal() {
         UserDetails userDetails = this.getDetails();
        return userDetails.getUsername();
    }

    @Override
    public UserDetails getDetails() {
        return (UserDetails)super.getDetails();
    }

}
