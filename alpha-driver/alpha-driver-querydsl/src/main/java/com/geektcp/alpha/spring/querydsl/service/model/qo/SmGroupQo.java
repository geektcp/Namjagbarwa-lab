package com.geektcp.alpha.spring.querydsl.service.model.qo;

import com.geektcp.alpha.common.spring.model.PageQoBase;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * Created by HaiyangWork on 2018/12/24.
 */

@Data
@ApiModel(value = "组查询qo对象", description = "组搜索条件")
public class SmGroupQo extends PageQoBase {
    @ApiModelProperty(value = "组id", example = "16")
    private Long id;

    @ApiModelProperty(value = "服务名", example = "tee")
    private String name;

}
