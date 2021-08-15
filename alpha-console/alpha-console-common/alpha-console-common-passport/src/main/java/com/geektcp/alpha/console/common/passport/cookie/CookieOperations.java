package com.geektcp.alpha.console.common.passport.cookie;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface CookieOperations {

    public String getCookieKey(HttpServletRequest request);

    public void storeCookie(HttpServletResponse response, String cookieValue);

    public void removeCookie(HttpServletRequest request, HttpServletResponse response);
}
