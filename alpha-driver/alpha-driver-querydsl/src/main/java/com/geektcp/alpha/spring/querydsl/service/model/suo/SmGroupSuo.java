package com.geektcp.alpha.spring.querydsl.service.model.suo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * Created by HaiyangWork on 2018/12/27.
 */

@Data
@ApiModel(value = "组查询suo对象", description = "")
public class SmGroupSuo {
    @ApiModelProperty(value = "主键id")
    private Long id;

    @ApiModelProperty(value = "服务组类型", example = "社区发现")
    private String type;

    @ApiModelProperty(value = "服务组名", example = "第一组")
    private String name;

    @ApiModelProperty(value = "启用标识-char类型", example = "8")
    private char enabledFlag;

}

