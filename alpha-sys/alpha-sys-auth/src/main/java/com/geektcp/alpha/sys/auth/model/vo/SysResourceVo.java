package com.geektcp.alpha.sys.auth.model.vo;

import com.geektcp.alpha.common.spring.model.BaseTreeNodeVo;
import com.alibaba.fastjson.annotation.JSONField;
import com.geektcp.alpha.sys.auth.model.po.SysResourcePo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @author tanghaiyang on 2019/1/10.
 */
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@ApiModel(value = "资源信息类SysResourceVo", description = "用于展示资源信息")
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

    public SysResourceVo(SysResourcePo po){
        this.id = po.getId();
        this.url = po.getUrl();
        this.name = po.getName();
        this.remark = po.getRemark();
        this.parentId = po.getParentId();
    }

}
