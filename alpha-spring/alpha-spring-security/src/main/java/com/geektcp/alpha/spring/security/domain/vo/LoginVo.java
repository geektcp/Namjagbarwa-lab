package com.geektcp.alpha.spring.security.domain.vo;

import lombok.Data;

/**
 * @author haiyang on 2020-04-18 09:47
 */
@Data
public class LoginVo {

    private String username;

    private String token;

    private String account;

    private String cellphone;

    private String nickName;

    private String companyId;

    private String companyName;

}
