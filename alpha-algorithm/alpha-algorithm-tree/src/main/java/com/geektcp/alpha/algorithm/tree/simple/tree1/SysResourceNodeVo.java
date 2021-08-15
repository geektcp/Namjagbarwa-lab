package com.geektcp.alpha.algorithm.tree.simple.tree1;

import com.alibaba.fastjson.annotation.JSONField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.LinkedList;

/**
 * @author tanghaiyang on 2019/1/10.
 */
@Data
@AllArgsConstructor
@ApiModel(value = "资源信息类SysResourceNodeVo", description = "用于展示资源信息")
public class SysResourceNodeVo {

    @JSONField(ordinal = 1)
    @ApiModelProperty(value = "资源ID", example = "0")
    public Long id;

    @JSONField(ordinal = 2)
    @ApiModelProperty(value = "用户父ID", example = "8")
    public int parentId;

    @JSONField(ordinal = 3)
    @ApiModelProperty(value = "资源名", example = "列表")
    public String name;

    @JSONField(ordinal = 4)
    @ApiModelProperty(value = "资源url", example = "http://xxxx:xx/api/xx")
    public String url;

    @JSONField(ordinal = 5)
    @ApiModelProperty(value = "资源级别", example = "一级资源")
    public String remark;

    @JSONField(ordinal = 6)
    @ApiModelProperty(value = "子节点列表")
    public LinkedList<SysResourceNodeVo> childList;

    public SysResourceNodeVo() {
        this(0L, 0, "root", "根节点");
    }

    public SysResourceNodeVo(Long id, int parentId, String name, String remark) {
        this.id = id;
        this.parentId = parentId;
        this.name = name;
        this.remark = remark;
    }

    public SysResourceNodeVo(SysResourcePo po) {
        this.id = po.getId();
        this.parentId = po.getParentId();
        this.name = po.getName();
        this.remark = po.getRemark();
    }

    public boolean add(SysResourceNodeVo sysResourceNodeVo){
        if (childList == null) {
            childList = new LinkedList<>();
        }
        return childList.add(sysResourceNodeVo);
    }

}

