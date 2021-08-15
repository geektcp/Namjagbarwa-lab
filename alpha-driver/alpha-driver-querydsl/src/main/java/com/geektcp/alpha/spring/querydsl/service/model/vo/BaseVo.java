package com.geektcp.alpha.spring.querydsl.service.model.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class BaseVo implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "标记，0-正常，1-冻结", example = "0")
    protected String enabled_flag;

    @ApiModelProperty(value = "创建者", example = "admin")
    protected String created_by;

    @ApiModelProperty(value = "创建时间", example = "2018-12-24 14:31:27")
    protected String created_dt;

    @ApiModelProperty(value = "更新者", example = "alpha")
    protected String updated_by;

    @ApiModelProperty(value = "更新时间", example = "2018-12-24 14:31:27")
    protected String updated_dt;

}
