package com.geektcp.alpha.sys.auth.model.qo;

import com.geektcp.alpha.common.spring.model.PageQoBase;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * @author tanghaiyang on 2018/1/4.
 */
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "角色分页查询参数SysRoleQo",description = "用于分页查询")
public class SysRoleQo extends PageQoBase {

    @ApiModelProperty(value = "角色名(表示员工编号、登录名)", example = "普通用户" )
    private String name;
}
