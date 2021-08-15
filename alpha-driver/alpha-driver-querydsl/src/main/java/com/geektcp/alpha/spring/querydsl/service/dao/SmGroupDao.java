package com.geektcp.alpha.spring.querydsl.service.dao;

import com.geektcp.alpha.common.spring.jpa.JpaRepo;
import com.geektcp.alpha.spring.querydsl.service.model.po.SmGroupPo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by HaiyangWork on 2018/12/24.
 */
@Repository
public interface SmGroupDao extends JpaRepo<SmGroupPo> {

    List<SmGroupPo> findByName(String name);

    List<SmGroupPo> findAll();

    Page<SmGroupPo> findAll(Pageable pageable);

}
