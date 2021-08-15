package com.geektcp.alpha.driver.mybatis3.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.geektcp.alpha.driver.mybatis3.model.City;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author tanghaiyang
 * @since 2018-08-31
 */
public interface CityService extends IService<City> {

    List<City> queryCityAll();

    boolean addCity(City city);

    City queryCityByName(String name);

}
