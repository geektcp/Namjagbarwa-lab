package com.geektcp.alpha.driver.mybatis3.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.geektcp.alpha.driver.mybatis3.model.Student;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author tanghaiyang
 * @since 2018-08-31
 */
public interface StudentDao extends BaseMapper<Student> {

    List<Student> selectAll();

}
