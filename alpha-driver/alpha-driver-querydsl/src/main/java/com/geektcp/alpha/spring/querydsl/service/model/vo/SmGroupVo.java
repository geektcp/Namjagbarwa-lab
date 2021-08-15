package com.geektcp.alpha.spring.querydsl.service.model.vo;

import com.geektcp.alpha.spring.querydsl.service.model.po.SmGroupPo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "组查询vo对象 ", description = "用于展示服务组基础信息")
public class SmGroupVo extends BaseVo {

    @ApiModelProperty(value = "服务id", example = "admin")
    private Long id;

    @ApiModelProperty(value = "服务名", example = "我的服务")
    private String name;

    @ApiModelProperty(value = "服务类型", example = "图查询")
    private String type;

    public SmGroupVo(SmGroupPo po) {
        this.id = po.getId();
        this.name = po.getName();
        this.type = po.getType();

        this.created_by = po.getCreatedBy();
        this.updated_by = po.getUpdatedBy();
        this.created_dt = po.getCreatedDt().toString();
        this.updated_dt = po.getUpdatedDt().toString();
    }
}
