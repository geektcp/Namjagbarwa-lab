package com.geektcp.alpha.algorithm.tree.simple.tree1;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @author tanghaiyang on 2018/1/4.
 */
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "sys_resource")
public class SysResourcePo extends BasePo {

    @Column(name = "name")
    private String name;

    @Column(name = "parent_id")
    private int parentId;

    @Column(name = "url")
    private String url;

    @Column(name = "remark")
    private String remark;
}

