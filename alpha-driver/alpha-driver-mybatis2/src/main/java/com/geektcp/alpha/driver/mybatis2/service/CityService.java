package com.geektcp.alpha.driver.mybatis2.service;

import com.geektcp.alpha.driver.mybatis2.model.po.CityPo;
import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author tanghaiyang
 * @since 2018-08-31
 */
public interface CityService {

    List<CityPo> queryCityAll();

    boolean addCity(CityPo cityPo);

    CityPo queryCityByName(String name);

}
