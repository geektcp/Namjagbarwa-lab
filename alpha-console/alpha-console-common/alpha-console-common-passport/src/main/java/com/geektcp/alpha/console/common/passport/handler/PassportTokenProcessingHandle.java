package com.geektcp.alpha.console.common.passport.handler;

import com.geektcp.alpha.console.common.passport.auth.PassportAuthentication;
import com.geektcp.alpha.console.common.passport.contants.PassportConstants;
import com.geektcp.alpha.console.common.passport.details.DetailsConvertPassportAuth;
import com.geektcp.alpha.console.common.passport.details.PassportUserDetails;
import com.geektcp.alpha.console.common.passport.token.DefaultPassportToken;
import com.geektcp.alpha.console.common.passport.token.PassportToken;
import com.geektcp.alpha.console.common.passport.tokenstore.PassportTokenStore;
import com.geektcp.alpha.console.common.passport.utils.IpHelper;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.http.HttpStatus;
import org.springframework.util.StringUtils;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class PassportTokenProcessingHandle implements AuthenticationSuccessHandler {
    protected final Log logger = LogFactory.getLog(this.getClass());

    private String serviceParamName;

    private String targetParamName;

    private String tokenParamName;

    private PassportTokenStore passportTokenStore;

    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

    public void setTargetParamName(String targetParamName) {
        this.targetParamName = targetParamName;
    }

    public void setServiceParamName(String serviceParamName) {
        this.serviceParamName = serviceParamName;
    }

    public void setTokenParamName(String tokenParamName) {
        this.tokenParamName = tokenParamName;
    }

    public void setPassportTokenStore(PassportTokenStore passportTokenStore) {
        this.passportTokenStore = passportTokenStore;
    }

    protected void handle(HttpServletRequest request, HttpServletResponse response,
                          Authentication authentication) throws IOException, ServletException {
        logger.debug("Passport Server Success Authentication");
        String redirect = obtainRedirect(request);
        if(StringUtils.isEmpty(redirect)){
            logger.error("Error redirectURL is null for Passport Server");

            response.sendError(HttpStatus.UNAUTHORIZED.value(),
                    "Error redirectURL For Passport Server");
            return;
        }
        String target = obtainTarget(request);
        DefaultPassportToken token = new DefaultPassportToken();
        String ip = IpHelper.getIpAddr(request);
        token.setIp(ip);
        PassportAuthentication passportAuthentication = DetailsConvertPassportAuth.convert
                ((PassportUserDetails) authentication.getPrincipal());
        token.setValue(passportAuthentication);
        PassportToken pt = passportTokenStore.storeToken(token);
        String tokenKey = pt.getKey();
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(redirect);
        if (!StringUtils.isEmpty(target)) {
            builder.queryParam(getTargetParamName(), target);
        }
        builder.queryParam(getTokenParamName(), tokenKey);
        redirectStrategy.sendRedirect(request, response, builder.encode().toUriString());
    }


    protected String obtainRedirect(HttpServletRequest request) {

        return request.getParameter(getServiceParamName());
    }

    protected String obtainTarget(HttpServletRequest request) {

        return request.getParameter(getTargetParamName());
    }

    protected String getTargetParamName() {
        return !StringUtils.isEmpty(targetParamName) ? targetParamName : PassportConstants.DEFAULT_TARGET_PARAM;
    }

    protected String getServiceParamName() {
        return !StringUtils.isEmpty(serviceParamName) ? serviceParamName : PassportConstants.DEFAULT_SERVICE_PARAM;
    }

    protected String getTokenParamName() {
        return !StringUtils.isEmpty(tokenParamName) ? tokenParamName : PassportConstants.DEFAULT_TOKEN_PARAM;
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        handle(request,response,authentication);
    }
}
