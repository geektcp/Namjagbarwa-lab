package com.geektcp.alpha.spring.security.domain.qo;

import javax.persistence.*;
import java.util.List;

/**
 * Store role information.
 */
@Entity
@Table(name = "sys_role")
public class UserRoleVo {

    private String roleCode;

    private String parentRoleCode;

    private String roleDescription;

    private List<UserQo> userQos;

    @Id
    @Column(name = "role_code")
    public String getRoleCode() {
        return roleCode;
    }

    public void setRoleCode(String roleCode) {
        this.roleCode = roleCode;
    }

    @Column(name = "parent_role_code")
    public String getParentRoleCode() {
        return parentRoleCode;
    }

    public void setParentRoleCode(String parentRoleCode) {
        this.parentRoleCode = parentRoleCode;
    }

    @Column(name = "role_desc")
    public String getRoleDescription() {
        return roleDescription;
    }

    public void setRoleDescription(String roleDescription) {
        this.roleDescription = roleDescription;
    }

    @ManyToMany(mappedBy = "roles")
    public List<UserQo> getUserQos() {
        return userQos;
    }

    public void setUserQos(List<UserQo> userQos) {
        this.userQos = userQos;
    }
}
