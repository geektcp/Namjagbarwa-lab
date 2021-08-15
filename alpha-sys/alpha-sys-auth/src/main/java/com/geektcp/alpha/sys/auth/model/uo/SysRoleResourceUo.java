package com.geektcp.alpha.sys.auth.model.uo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Set;

/**
 * @author tanghaiyang on 2018/1/4.
 */
@Data
@ApiModel(value = "角色授权SysRoleResourceUo",description = "角色授权")
public class SysRoleResourceUo {

    @ApiModelProperty(value = "角色id", example = "10")
    private Long roleId;

    @ApiModelProperty(value = "资源id列表")
    private Set<Long> resourceIds;

}
