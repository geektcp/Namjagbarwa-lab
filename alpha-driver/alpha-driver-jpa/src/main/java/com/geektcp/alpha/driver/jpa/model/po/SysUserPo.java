package com.geektcp.alpha.driver.jpa.model.po;

import com.geektcp.alpha.common.spring.model.BasePo;
import com.geektcp.alpha.driver.jpa.model.suo.SysUserSuo;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
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
@NoArgsConstructor
@Entity
@Table(name = "sys_user")
public class SysUserPo extends BasePo {

    @Column(name = "user_no")
    private String userNo;

    @Column(name = "password")
    private String password;

    @Column(name = "name")
    private String name;

    @Column(name = "phone")
    private String phone;

    @Column(name = "email")
    private String email;

    @Column(name = "status")
    private int status;

    public void update(SysUserSuo suo, boolean addOrUpdate) {
        if (addOrUpdate) {
            this.userNo = suo.getUserNo();
            this.name = suo.getName();
            this.phone = suo.getPhone();
            this.email = suo.getEmail();
            this.status = suo.getStatus();
//            this.password = AuthUtils.getEncryptedPwd(suo.getPassword());
        } else {
            this.id = suo.getId();
            this.userNo = suo.getUserNo();
            this.name = suo.getName();
            this.phone = suo.getPhone();
            this.email = suo.getEmail();
        }
    }

}
