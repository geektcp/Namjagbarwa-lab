package com.geektcp.alpha.console.passport.service;


import com.geektcp.alpha.console.common.passport.details.PassportUserDetails;
import com.geektcp.alpha.console.passport.vo.SysRoleVO;
import com.geektcp.alpha.console.passport.vo.SysUserVO;
import com.geektcp.alpha.console.passport.fegin.UserService;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;


@Service("userDetailService")
public class UserDetailServiceImpl implements UserDetailsService {

    @Resource
    private UserService userService;

    @Override
    public PassportUserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        SysUserVO userVo = userService.findUserByUsername(username);
        SysUserVO userVo = new SysUserVO();
        userVo.setLoginname("xialaokou");
        userVo.setPassword("1111111");
        userVo.setUserId(5);
        userVo.setUsername("xialaokou");

        List<SysRoleVO> roleList = new ArrayList<>();
        SysRoleVO vo1 = new SysRoleVO();
        vo1.setRoleCode("01");
        roleList.add(vo1);
        userVo.setRoleVoList(roleList);

        SysRoleVO vo11 = new SysRoleVO();
        vo11.setRoleCode("0101");
        roleList.add(vo11);
        userVo.setRoleVoList(roleList);

        SysRoleVO vo2 = new SysRoleVO();
        vo2.setRoleCode("02");
        roleList.add(vo2);
        userVo.setRoleVoList(roleList);

        SysRoleVO vo201 = new SysRoleVO();
        vo201.setRoleCode("0201");
        roleList.add(vo201);
        userVo.setRoleVoList(roleList);

        SysRoleVO vo3 = new SysRoleVO();
        vo3.setRoleCode("01");
        roleList.add(vo3);
        userVo.setRoleVoList(roleList);

        SysRoleVO vo301 = new SysRoleVO();
        vo301.setRoleCode("0301");
        roleList.add(vo301);
        userVo.setRoleVoList(roleList);

        if (userVo != null) {
            PassportUserDetails details = new PassportUserDetails();
            List<GrantedAuthority> authorityList = new ArrayList<>();
            List<SysRoleVO> roleVoList = userVo.getRoleVoList();
            for (SysRoleVO roleVo : roleVoList) {
                authorityList.add(new SimpleGrantedAuthority(roleVo.getRoleCode()));
            }
            // 初始化userDetails
            details.setUserId(userVo.getUserId());
            details.setUsername(userVo.getLoginname());
            details.setPassword(userVo.getPassword());
            details.setStatus(userVo.getDelFlag());
            details.setRoleList(authorityList);
            return details;
        }
        throw new UsernameNotFoundException("用户名不存在");

//        PassportUserDetails details = new PassportUserDetails();
//        details.setUserId(5);
//        details.setUsername("xialaokou");
//        details.setPassword("123456");
//        details.setStatus(userVo.getDelFlag());
//        details.setRoleList(new ArrayList<>());
//        return details;
    }
}
