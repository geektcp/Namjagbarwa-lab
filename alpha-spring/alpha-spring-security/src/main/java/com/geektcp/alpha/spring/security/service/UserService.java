package com.geektcp.alpha.spring.security.service;

import com.geektcp.alpha.spring.security.domain.qo.UserQo;
import com.geektcp.alpha.spring.security.repository.UserRepository;
import com.geektcp.alpha.spring.security.domain.vo.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    private UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @Transactional
    public UserVo getUserByUserName(String userName){
        UserVo userVo = new UserVo();
        UserQo userQo = userRepository.findByUserName(userName);
        userVo.setUserName(userQo.getUserName());
        userVo.setUserDesc(userQo.getUserDescription());
        List<String> roleCodes = new ArrayList<>();
        userQo.getRoles().forEach(role -> roleCodes.add(role.getRoleCode()));
        userVo.setRoleCodes(roleCodes);
        return userVo;
    }

    @Transactional
    public UserDetails getUserDetailByUserName(String username){
        UserQo userQo = this.userRepository.findByUserName(username);
        if(userQo == null){
            throw new UsernameNotFoundException("user + " + username + "not found.");
        }
        List<String> roleList = this.userRepository.queryUserOwnedRoleCodes(username);
        List<GrantedAuthority> authorities = new ArrayList<>();
        roleList.forEach(role ->
                authorities.add( new SimpleGrantedAuthority(role) )
        );
        return new User(username, userQo.getPassword(), authorities);
    }

}
