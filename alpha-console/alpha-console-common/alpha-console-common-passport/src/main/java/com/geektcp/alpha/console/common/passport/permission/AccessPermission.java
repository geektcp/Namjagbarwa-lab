package com.geektcp.alpha.console.common.passport.permission;

import org.springframework.security.core.Authentication;

import javax.servlet.http.HttpServletRequest;

public interface AccessPermission {

    boolean hasPermission(HttpServletRequest request, Authentication authentication);
}
