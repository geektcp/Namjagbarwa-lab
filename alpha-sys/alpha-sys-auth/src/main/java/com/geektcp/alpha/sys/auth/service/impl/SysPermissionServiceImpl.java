package com.geektcp.alpha.sys.auth.service.impl;


import alpha.common.base.exception.UnexpectedStatusException;
import com.geektcp.alpha.common.spring.jpa.JpaBase;
import com.geektcp.alpha.sys.auth.constant.AuthStatus;
import com.geektcp.alpha.sys.auth.model.po.*;
import com.geektcp.alpha.sys.auth.model.vo.SysPermissionVo;
import com.geektcp.alpha.sys.auth.service.SysPermissionService;
import com.querydsl.core.QueryResults;
import com.querydsl.core.Tuple;
import com.querydsl.jpa.impl.JPAQuery;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author tanghaiyang on 2019/3/4.
 */
@Service
public class SysPermissionServiceImpl extends JpaBase implements SysPermissionService {

    @Override
    public SysPermissionVo findPermissions(Long userId){
        try{
            QSysUserPo qSysUserPo = QSysUserPo.sysUserPo;
            QSysUserRolePo qSysUserRolePo = QSysUserRolePo.sysUserRolePo;
            QSysRolePo qSysRolePo = QSysRolePo.sysRolePo;
            QSysRoleResourcePo roleRoleResourcePo = QSysRoleResourcePo.sysRoleResourcePo;
            QSysResourcePo resourcePo = QSysResourcePo.sysResourcePo;
            JPAQuery<Tuple> jpaQuery = jpa.select(
                            qSysUserRolePo.roleId,resourcePo.url,
                            qSysUserPo.id, qSysUserPo.userNo,
                            qSysRolePo.name,roleRoleResourcePo.resourceId)
                    .from(qSysUserPo)
                    .leftJoin(qSysUserRolePo).on(qSysUserPo.id.eq(qSysUserRolePo.userId))
                    .leftJoin(qSysRolePo).on(qSysRolePo.id.eq(qSysUserRolePo.roleId))
                    .leftJoin(roleRoleResourcePo).on(roleRoleResourcePo.roleId.eq(qSysUserRolePo.roleId))
                    .leftJoin(resourcePo).on(roleRoleResourcePo.resourceId.eq(resourcePo.id))
                    .where(QSysUserPo.sysUserPo.id.eq(userId));
            QueryResults<Tuple> jpaResults = jpaQuery.fetchResults();
            List<Tuple> results = jpaResults.getResults();
            Set<Long> roleIds = new HashSet<>();
            Set<String> permissions = new HashSet<>();
            results.forEach(tuple -> {
                Long roleId = tuple.get(0, Long.class);
                String url = tuple.get(1, String.class);
                roleIds.add(roleId);
                permissions.add(url);
            });
            SysPermissionVo sysPermissionVo = new SysPermissionVo();
            sysPermissionVo.setId(userId);
            sysPermissionVo.setRoleIds(roleIds);
            sysPermissionVo.setPermissions(permissions);
            return sysPermissionVo;
        }catch (Exception e){
            throw new UnexpectedStatusException(AuthStatus.USER_PERMISSION_ERROR, e);
        }
    }


}
