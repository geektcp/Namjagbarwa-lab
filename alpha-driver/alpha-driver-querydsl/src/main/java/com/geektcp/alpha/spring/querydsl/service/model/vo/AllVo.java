package com.geektcp.alpha.spring.querydsl.service.model.vo;

import com.geektcp.alpha.spring.querydsl.service.model.po.SmGroupPo;
import io.swagger.annotations.ApiModelProperty;

/**
 * Created by HaiyangWork on 2019/1/2.
 */
public class AllVo extends BaseVo {

    @ApiModelProperty(value = "服务id", example = "admin")
    private Long id;

    @ApiModelProperty(value = "服务名", example = "我的服务")
    private String name;

    @ApiModelProperty(value = "服务类型", example = "图查询")
    private String type;

    public AllVo(SmGroupPo po) {
        this.id = po.getId();
        this.name = po.getName();
        this.type = po.getType();

        this.created_by = po.getCreatedBy();
        this.updated_by = po.getUpdatedBy();
        this.created_dt = po.getCreatedDt().toString();
        this.updated_dt = po.getUpdatedDt().toString();
    }

}

