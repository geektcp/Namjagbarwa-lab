package com.geektcp.alpha.driver.jpa.model.po;

import lombok.Data;

import javax.persistence.*;

/**
 * @author tanghaiyang on 2018/1/4.
 */
@Data
@Entity
@Table(name = "sys_role_resource")
public class SysRoleResourcePo {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "role_id")
    private Long roleId;

    @Column(name = "resource_id")
    private Long resourceId;

}
