package com.geektcp.alpha.sys.auth.model.suo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.Valid;

/**
 * @author tanghaiyang on 2018/1/4.
 */
@Data
@ApiModel(value = "用户新增或更新参数SysUserSuo",description = "用于用户新增或更新")
public class SysUserSuo {

    @ApiModelProperty(value = "用户ID，如果为NULL表示新增", example = "13")
    private Long id;

    @Valid
    @ApiModelProperty(value = "用户名(表示员工编号、登录名)", example = "test")
    private String userNo;

    @ApiModelProperty(value = "用户密码", example = "123456")
    private String password;

    @ApiModelProperty(value = "用户密码", example = "111111")
    private String passwordOld;

    @ApiModelProperty(value = "姓名")
    private String name;

    @ApiModelProperty(value = "手机或电话", example = "13811112222")
    private String phone;

    @ApiModelProperty(value = "电子邮箱", example = "abc@gmail.com")
    private String email;

    @ApiModelProperty(value = "用户状态，0-正常，1-冻结", example = "0")
    private int status;

    @ApiModelProperty(value = "角色id", example = "10")
    private Long roleId;

}
