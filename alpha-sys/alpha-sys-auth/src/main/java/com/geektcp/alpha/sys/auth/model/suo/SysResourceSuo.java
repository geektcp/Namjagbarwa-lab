package com.geektcp.alpha.sys.auth.model.suo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author tanghaiyang on 2018/1/4.
 */
@Data
@ApiModel(value = "资源更新参数SysResourceSuo",description = "用于用户新增或更新")
public class SysResourceSuo {

    @ApiModelProperty(value = "资源id", example = "3")
    private Long id;

    @ApiModelProperty(value = "资源名", example = "保存")
    private String name;

    @ApiModelProperty(value = "资源父id", example = "6")
    private int parentId;

    @ApiModelProperty(value = "url串", example = "http://xxxx:xx/api/xx")
    private String url;

    @ApiModelProperty(value = "资源级别", example = "三级资源")
    private String remark;

}
