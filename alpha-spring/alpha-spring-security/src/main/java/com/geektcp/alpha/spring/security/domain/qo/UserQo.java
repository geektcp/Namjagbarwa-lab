package com.geektcp.alpha.spring.security.domain.qo;

import javax.persistence.*;
import java.util.List;

/**
 * Store User information.
 */
@Entity
@Table(name = "sys_user")
public class UserQo {
    private String userName;
    private String userDescription;
    private String password;
    private List<UserRoleVo> roles;

    @Id
    @Column(name = "user_name")
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Column(name = "user_desc")
    public String getUserDescription() {
        return userDescription;
    }

    public void setUserDescription(String userDescription) {
        this.userDescription = userDescription;
    }

    @Column(name = "password")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @ManyToMany
    @JoinTable(name = "sys_user_role",
            joinColumns = @JoinColumn(name = "user_name", referencedColumnName = "user_name", updatable = false, insertable = false),
            inverseJoinColumns = @JoinColumn(name = "role_code", referencedColumnName = "role_code", updatable = false, insertable = false))
    public List<UserRoleVo> getRoles() {
        return roles;
    }

    public void setRoles(List<UserRoleVo> roles) {
        this.roles = roles;
    }
}
