package com.geektcp.alpha.driver.jpa.model.suo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author tanghaiyang on 2018/1/4.
 */
@Data
@ApiModel(value = "角色新增或更新参数SysRoleSuo",description = "用于角色新增或更新")
public class SysRoleSuo {

    @ApiModelProperty(value = "角色ID，如果为NULL表示新增", example = "3")
    private Long id;

    @ApiModelProperty(value = "角色名", example = "普通用户")
    private String name;

    @ApiModelProperty(value = "角色备注", example = "Y")
    private String remark;

}
