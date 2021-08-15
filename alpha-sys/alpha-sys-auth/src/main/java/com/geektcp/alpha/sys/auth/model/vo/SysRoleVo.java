package com.geektcp.alpha.sys.auth.model.vo;

import com.geektcp.alpha.sys.auth.model.po.SysRolePo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author tanghaiyang on 2018/1/4.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "角色基础信息类SysRoleVo", description = "用于展示角色基础信息")
public class SysRoleVo {

    @ApiModelProperty(value = "角色名", example = "admin")
    private String name;

    @ApiModelProperty(value = "删除标记", example = "Y")
    private String remark;

    public SysRoleVo(SysRolePo po){
        this.name = po.getName();
        this.remark = po.getRemark();
    }

}
