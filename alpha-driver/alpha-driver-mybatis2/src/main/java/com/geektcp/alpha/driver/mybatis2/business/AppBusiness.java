package com.geektcp.alpha.driver.mybatis2.business;

import com.geektcp.alpha.driver.mybatis2.model.po.CityPo;
import com.geektcp.alpha.driver.mybatis2.model.po.CardPo;
import com.geektcp.alpha.driver.mybatis2.model.po.StudentPo;

/**
 * 数据库表关联逻辑处理接口
 *
 * @author tanghaiyang
 * @since 2018-09-01
 */
public interface AppBusiness {

    boolean addStudent(StudentPo studentPo, CityPo cityPo, CardPo cardPo);

}
