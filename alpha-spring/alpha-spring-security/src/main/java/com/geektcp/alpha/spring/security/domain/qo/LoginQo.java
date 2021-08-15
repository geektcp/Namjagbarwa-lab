package com.geektcp.alpha.spring.security.domain.qo;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.NotBlank;

/**
 * @author haiyang
 */
@Getter
@Setter
public class LoginQo {

    @NotBlank
    private String username;

    @NotBlank
    private String encryptPassword;

    private String code;

    private String uuid = "";

    @Override
    public String toString() {
        return "{username=" + username  + ", password= ******}";
    }
}
