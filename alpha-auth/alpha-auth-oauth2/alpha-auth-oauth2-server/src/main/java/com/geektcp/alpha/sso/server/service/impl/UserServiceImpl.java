package com.geektcp.alpha.sso.server.service.impl;

import com.geektcp.alpha.sso.server.entity.SysUser;
import com.geektcp.alpha.sso.server.repository.SysUserRepository;
import com.geektcp.alpha.sso.server.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author tanghaiyang

 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private SysUserRepository sysUserRepository;

    @Override
    public SysUser getByUsername(String username) {
        return sysUserRepository.findByUsername(username);
    }
}
