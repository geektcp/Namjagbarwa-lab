package com.geektcp.alpha.driver.jpa.service.impl;

import com.geektcp.alpha.common.spring.jpa.JpaBase;
import com.geektcp.alpha.driver.jpa.domain.User;
import com.geektcp.alpha.driver.jpa.model.po.QSysResourcePo;
import com.geektcp.alpha.driver.jpa.model.po.QSysRoleResourcePo;
import com.geektcp.alpha.driver.jpa.model.vo.SysResourceVo;
import com.geektcp.alpha.driver.jpa.repository.UserJpaRepository;
import com.geektcp.alpha.driver.jpa.repository.UserRepository;
import com.geektcp.alpha.driver.jpa.service.IUserService;
import com.querydsl.core.Tuple;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;

@Slf4j
@Service
@Transactional
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class UserServiceImpl extends JpaBase implements IUserService {

    private UserJpaRepository userJpaRepository;

    private UserRepository userRepository;

    public List<User> findAll() {
        return userJpaRepository.findAll();
    }

    public List<User> findByName(String name) {
//        List<User> userList1 = userRepository.findByName1(name);
//        List<User> userList2 = userRepository.findByName2(name);
//        List<User> userList3 = userRepository.findByNameAndAddress(name, "3");
//        System.out.println("userList1:" + userList1);
//        System.out.println("userList2:" + userList2);
//        System.out.println("userList3:" + userList3);
        return userRepository.findByName(name);
    }

    public void saveUser(User book) {
        userJpaRepository.save(book);
    }

    @Cacheable("users")
    public User findOne(long id) {
        System.out.println("Cached Pages");
        return userJpaRepository.findOne(id);
    }

    public void delete(long id) {
        userJpaRepository.delete(id);
    }


    public void test(Long roleId) {
        QSysResourcePo resourcePo = QSysResourcePo.sysResourcePo;
        QSysRoleResourcePo roleResourcePo = QSysRoleResourcePo.sysRoleResourcePo;
        List<Tuple> tupleList = jpa.select(resourcePo, roleResourcePo)
                .from(resourcePo)
                .leftJoin(roleResourcePo).on(resourcePo.id.eq(roleResourcePo.resourceId))
                .where(roleResourcePo.roleId.eq(roleId))
                .orderBy(resourcePo.parentId.asc())
                .fetch();
        List<SysResourceVo> list = new LinkedList<>();
        for (Tuple row : tupleList) {
            list.add(new SysResourceVo(row.get(resourcePo)));
        }
    }
}
