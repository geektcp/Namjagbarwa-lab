package com.geektcp.alpha.driver.jpa.repository;

import com.geektcp.alpha.driver.jpa.domain.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserRepository extends Repository<User, Long> {

    List<User> findByNameAndAddress(String name, String address);

    @Query(value = "from User u where u.name=:name")
    List<User> findByName1(@Param("name") String name);

    @Query(value = "select * from #{#entityName} u where u.name=?1", nativeQuery = true)
    List<User> findByName2(String name);

    List<User> findByName(String name);
}
