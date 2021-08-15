package com.geektcp.alpha.sys.auth.model.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

/**
 * @author tanghaiyang on 2019/3/4.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "用户权限SysPermissionVo", description = "用于shiro授权")
public class SysPermissionVo {

    @ApiModelProperty(value = "用户id", example = "2")
    private Long id;

    @ApiModelProperty(value = "用户名", example = "admin")
    private String userNo;

    @ApiModelProperty(value = "用户角色列表")
    private Set<Long> roleIds;

    @ApiModelProperty(value = "用户权限即资源列表")
    private Set<String> permissions;

}
