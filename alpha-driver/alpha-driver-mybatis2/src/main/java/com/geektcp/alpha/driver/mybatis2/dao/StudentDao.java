package com.geektcp.alpha.driver.mybatis2.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.geektcp.alpha.driver.mybatis2.model.po.StudentPo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author tanghaiyang
 * @since 2018-08-31
 */
public interface StudentDao extends BaseMapper<StudentPo> {

    List<StudentPo> selectAll(@Param("page") Page page);

}
