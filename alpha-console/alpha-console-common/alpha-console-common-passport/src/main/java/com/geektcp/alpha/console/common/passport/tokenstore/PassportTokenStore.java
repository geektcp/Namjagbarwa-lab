package com.geektcp.alpha.console.common.passport.tokenstore;

import com.geektcp.alpha.console.common.passport.token.PassportToken;

public interface PassportTokenStore {

    public PassportToken storeToken(PassportToken token);

    public boolean deleteToken(PassportToken token);

    public boolean deleteToken(String tokenKey);

    public PassportToken getToken(String tokenKey);

}
