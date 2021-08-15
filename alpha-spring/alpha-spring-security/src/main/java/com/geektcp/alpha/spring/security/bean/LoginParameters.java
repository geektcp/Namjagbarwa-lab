package com.geektcp.alpha.spring.security.bean;

import com.geektcp.alpha.spring.security.exception.BaseException;
import com.geektcp.alpha.spring.security.util.EncryptUtils;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Objects;

/**
 * @author haiyang on 2020-04-12 17:12
 */
@Data
@Component
@Slf4j
public class LoginParameters {

    private String secret;

    private long expiration;


    private  String encryptKey = null;

    @Value("${jwt.secret:#{ T(java.util.UUID).randomUUID().toString()}}")
    public void setSecret(String secret) {
        this.secret = secret;
    }

    @Value("${jwt.expiration:3600}")
    public void setExpiration(long expiration) {
        this.expiration = expiration;
    }

    public String getEncryptSecret() {
        try {
            if(Objects.isNull(encryptKey)) {
                encryptKey = EncryptUtils.encrypt(secret);
                log.info("encryptKey: {}");
                return encryptKey;
            }else {
                return encryptKey;
            }
        } catch (Exception e) {
            log.error("generate key failed: {}", e.getMessage());
            throw new BaseException("generate key failed!");
        }
    }
}
