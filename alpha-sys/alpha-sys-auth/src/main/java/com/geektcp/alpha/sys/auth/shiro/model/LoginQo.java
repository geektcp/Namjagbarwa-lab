package com.geektcp.alpha.sys.auth.shiro.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @author tanghaiyang on 2018/1/4.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginQo implements Serializable {

    private static final long serialVersionUID = 2L;

    @NotNull
    @ApiModelProperty(value = "登录用户名", example = "admin")
    private String userNo;

    @NotNull
    @ApiModelProperty(value = "登录密码", example = "123456")
    private String password;

    @ApiModelProperty(value = "是否自动登录(Y/N),默认为N", example = "N")
    private String autoLogin = "N";
}
