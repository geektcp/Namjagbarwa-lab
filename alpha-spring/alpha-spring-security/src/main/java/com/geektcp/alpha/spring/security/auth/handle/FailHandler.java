package com.geektcp.alpha.spring.security.auth.handle;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Service;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by tanghaiyang
 * 22:50 2018/9/2
 */
@Service("authenticationFailHandler")
public class FailHandler extends SimpleUrlAuthenticationFailureHandler {

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception)
            throws IOException {
        response.sendError(HttpServletResponse.SC_HTTP_VERSION_NOT_SUPPORTED, exception.getMessage());
    }
}
