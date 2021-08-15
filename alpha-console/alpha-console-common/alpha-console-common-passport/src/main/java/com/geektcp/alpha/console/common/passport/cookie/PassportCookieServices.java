package com.geektcp.alpha.console.common.passport.cookie;

import com.geektcp.alpha.console.common.passport.auth.PassportAuthentication;
import org.springframework.security.core.AuthenticationException;

public interface PassportCookieServices {

    PassportAuthentication loadAuthentication(String cookieValue) throws AuthenticationException;

    boolean removeAuthentication(String cookieValue) throws AuthenticationException;
}
