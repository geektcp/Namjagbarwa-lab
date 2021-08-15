package com.geektcp.alpha.console.common.passport.tokenstore;

import com.geektcp.alpha.console.common.passport.token.PassportToken;

public interface TokenKeyGenerator {

    String extractKey(PassportToken token);
}
