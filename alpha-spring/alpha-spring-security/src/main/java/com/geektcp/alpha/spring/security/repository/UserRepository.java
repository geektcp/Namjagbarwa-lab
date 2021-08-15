package com.geektcp.alpha.spring.security.repository;

import com.geektcp.alpha.spring.security.domain.qo.UserQo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by tanghaiyang
 * 21:51 2018/9/2
 */
@Repository
public interface UserRepository extends JpaRepository<UserQo, String> {

    UserQo findByUserName(String username);

    @Query(value = "select r.roleCode from UserQo u inner join u.roles as r where u.userName = :username")
    List<String> queryUserOwnedRoleCodes(@Param(value = "username") String username);
}
