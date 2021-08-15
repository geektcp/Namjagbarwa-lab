package com.geektcp.alpha.algorithm.tree.simple.tree2;

import com.alibaba.fastjson.annotation.JSONField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * @author tanghaiyang on 2019/1/10.
 */
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "资源信息类SysResourceNodeVo", description = "用于展示资源信息")
public class SysResourceVo extends BaseTreeNodeVo {

    @JSONField(ordinal = 1)
    @ApiModelProperty(value = "资源名", example = "列表")
    private String name;

    @JSONField(ordinal = 2)
    @ApiModelProperty(value = "资源级别", example = "一级资源")
    private String remark;

    @JSONField(ordinal = 3)
    @ApiModelProperty(value = "资源url", example = "http://xxxx:xx/api/xx")
    private String url;

    public SysResourceVo(Long id, Long parentId, String name, String remark, String url) {
        this.id = id;
        this.url = url;
        this.name = name;
        this.remark = remark;
        this.parentId = parentId;
    }


}
