package com.geektcp.alpha.sys.auth.service.impl;


import alpha.common.base.exception.UnexpectedStatusException;
import com.geektcp.alpha.common.spring.jpa.JpaBase;
import com.geektcp.alpha.common.spring.model.BaseTreeNodeVo;
import com.geektcp.alpha.common.spring.model.TResponse;
import com.geektcp.alpha.common.spring.model.VoTreeBuilder;
import com.geektcp.alpha.sys.auth.constant.AuthStatus;
import com.geektcp.alpha.sys.auth.dao.SysRoleResourceDao;
import com.geektcp.alpha.sys.auth.model.po.QSysResourcePo;
import com.geektcp.alpha.sys.auth.model.po.QSysRoleResourcePo;
import com.geektcp.alpha.sys.auth.model.po.SysResourcePo;
import com.geektcp.alpha.sys.auth.model.po.SysRoleResourcePo;
import com.geektcp.alpha.sys.auth.model.uo.SysRoleResourceUo;
import com.geektcp.alpha.sys.auth.model.vo.SysResourceVo;
import com.geektcp.alpha.sys.auth.service.SysRoleService;
import com.querydsl.core.Tuple;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;


/**
 * @author tanghaiyang on 2019/2/26.
 */
@Service
public class SysRoleServiceImpl extends JpaBase implements SysRoleService {

    @Autowired
    private SysRoleResourceDao sysRoleResourceDao;

    @Override
    public TResponse grantResources(SysRoleResourceUo uo) {
        Long roleId = uo.getRoleId();
        if(Objects.isNull(roleId)){
            throw new UnexpectedStatusException(AuthStatus.ROLE_RESOURCE_SAVE_ERROR);
        }
        try {
            Set<SysRoleResourcePo> list = new HashSet<>();
            Set<Long> listResourceIds = uo.getResourceIds();
            Set<SysResourcePo> listResources = findResources(roleId);
            Set<Long> resultResourceIds = new HashSet<>();
            listResources.forEach( po-> { resultResourceIds.add(po.getId()); });
            listResourceIds.forEach(resourceId -> {
                if(!resultResourceIds.contains(resourceId)) {
                    SysRoleResourcePo po = new SysRoleResourcePo();
                    po.setRoleId(roleId);
                    po.setResourceId(resourceId);
                    list.add(po);
                }
            });
            sysRoleResourceDao.save(list);
        } catch (Exception e) {
            throw new UnexpectedStatusException(AuthStatus.ROLE_RESOURCE_SAVE_ERROR, e);
        }
        return TResponse.success();
    }

    @Override
    @SuppressWarnings("all")
    public TResponse findAllResources(Long roleId){
        try {
            Set<SysResourcePo> listResources = findResources(roleId);
            List<SysResourceVo> result = new LinkedList<>();
            for (SysResourcePo row : listResources) {
                result.add(new SysResourceVo(row));
            }
            List<BaseTreeNodeVo> treeList = VoTreeBuilder.createTreeList(result, SysResourceVo.class);
            return TResponse.success(treeList);
        }catch (Exception e){
            throw new UnexpectedStatusException(AuthStatus.ROLE_RESOURCE_TREE_ERROR, e);
        }
    }

    private Set<SysResourcePo> findResources(Long roleId){
        try {
            QSysResourcePo resourcePo = QSysResourcePo.sysResourcePo;
            QSysRoleResourcePo roleResourcePo = QSysRoleResourcePo.sysRoleResourcePo;
            List<Tuple> tupleList = jpa.select(resourcePo, roleResourcePo)
                    .from(resourcePo)
                    .leftJoin(roleResourcePo).on(resourcePo.id.eq(roleResourcePo.resourceId))
                    .where(roleResourcePo.roleId.eq(roleId))
                    .orderBy(resourcePo.parentId.asc())
                    .fetch();
            Set<SysResourcePo> result = new HashSet<>();
            for (Tuple row : tupleList) {
                result.add(row.get(resourcePo));
            }
            return result;
        }catch (Exception e){
            throw new UnexpectedStatusException(AuthStatus.ROLE_RESOURCE_FIND_ERROR, e);
        }
    }

}
