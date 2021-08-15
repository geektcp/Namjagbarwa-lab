package com.geektcp.alpha.spring.shiro.service.impl;

import com.geektcp.alpha.spring.shiro.entity.user.UserRole;
import com.geektcp.alpha.spring.shiro.mapper.UserRoleMapper;
import com.geektcp.alpha.spring.shiro.service.IUserRoleService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author tanghaiyang
 */
@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class UserRoleServiceImpl extends ServiceImpl<UserRoleMapper, UserRole> implements IUserRoleService {

    @Override
    @Transactional
    public void deleteUserRolesByRoleId(List<String> roleIds) {
        this.baseMapper.delete(new QueryWrapper<UserRole>().lambda().in(UserRole::getRoleId, roleIds));
    }

    @Override
    @Transactional
    public void deleteUserRolesByUserId(List<String> userIds) {
        this.baseMapper.delete(new QueryWrapper<UserRole>().lambda().in(UserRole::getUserId, userIds));
    }
}
