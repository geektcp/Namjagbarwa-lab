package com.geektcp.alpha.sys.auth.dao;

import com.geektcp.alpha.common.spring.jpa.JpaRepo;
import com.geektcp.alpha.sys.auth.model.po.SysUserRolePo;
import org.springframework.stereotype.Repository;

/**
 * @author tanghaiyang on 2018/1/4.
 */
@Repository
public interface SysUserRoleDao extends JpaRepo<SysUserRolePo> {
}
