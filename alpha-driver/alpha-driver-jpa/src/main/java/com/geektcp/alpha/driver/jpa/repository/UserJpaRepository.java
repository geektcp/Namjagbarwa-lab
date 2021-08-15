package com.geektcp.alpha.driver.jpa.repository;

import com.geektcp.alpha.driver.jpa.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserJpaRepository extends JpaRepository<User, Long> {

}
