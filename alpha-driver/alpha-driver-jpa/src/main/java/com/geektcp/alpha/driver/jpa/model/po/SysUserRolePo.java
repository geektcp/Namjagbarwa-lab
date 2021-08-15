package com.geektcp.alpha.driver.jpa.model.po;

import lombok.Data;

import javax.persistence.*;

/**
 * @author tanghaiyang on 2018/1/4.
 */
@Data
@Entity
@Table(name = "sys_user_role")
public class SysUserRolePo {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "role_id")
    private Long roleId;

}
