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
@ApiModel(value = "资源分页查询参数SysResourceQo",description = "用于分页查询")
public class SysResourceQo extends PageQoBase {

    @ApiModelProperty(value = "资源名", example = "部门管理")
    private String name;

    @ApiModelProperty(value = "资源备注", example = "顶级资源")
    private String remark;
}
