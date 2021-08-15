package com.geektcp.alpha.sys.auth.model.vo;

import com.geektcp.alpha.sys.auth.model.po.SysRolePo;
import com.geektcp.alpha.sys.auth.model.po.SysUserPo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author tanghaiyang on 2018/1/4.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "用户基础信息类SysUserVo", description = "用于展示用户基础信息")
public class SysUserVo {

    @ApiModelProperty(value = "用户ID", example = "1")
    private long id;

    @ApiModelProperty(value = "用户名", example = "admin")
    private String userNo;

    @ApiModelProperty(value = "姓名(别名)", example = "管理员")
    private String name;

    @ApiModelProperty(value = "手机或电话", example = "13800138000")
    private String phone;

    @ApiModelProperty(value = "电子邮箱", example = "xx@139.com")
    private String email;

    @ApiModelProperty(value = "状态，0-正常，1-冻结", example = "0")
    private int status;

    @ApiModelProperty(value = "角色ID", example = "1")
    private Long roleId;

    @ApiModelProperty(value = "角色名", example = "管理员角色")
    private String roleName;

    public SysUserVo(SysUserPo po){
        this.id = po.getId();
        this.userNo = po.getUserNo();
        this.name = po.getName();
        this.phone = po.getPhone();
        this.email = po.getEmail();
        this.status = po.getStatus();
    }

    public SysUserVo(SysUserPo userPo, SysRolePo rolePo){
        this(userPo);
        this.roleId = rolePo.getId();
        this.roleName = rolePo.getName();
    }

}
