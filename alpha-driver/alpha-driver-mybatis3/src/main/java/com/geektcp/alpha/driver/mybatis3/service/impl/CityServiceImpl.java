package com.geektcp.alpha.driver.mybatis3.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.geektcp.alpha.driver.mybatis3.model.City;
import com.geektcp.alpha.driver.mybatis3.dao.CityDao;
import com.geektcp.alpha.driver.mybatis3.service.CityService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author tanghaiyang
 * @since 2018-08-31
 */
@Service
@Slf4j
public class CityServiceImpl extends ServiceImpl<CityDao, City> implements CityService {

    @Override
    public List<City> queryCityAll() {
        return list(null);
    }

    @Override
    public boolean addCity(City city) {
        String name = city.getName();

        if (queryCityByName(name) == null)
            return save(city);

        // 数据库已经存在
        return true;
    }

    @Override
    public City queryCityByName(String name) {
        QueryWrapper<City> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(City.KEY, name);

        List<City> cityList = list(queryWrapper);

        if (cityList == null || cityList.isEmpty())
            return null;

        if (cityList.size() > 1)
            log.error("queryCityByName结果有多个，name={}", name);

        return cityList.get(0);
    }
}
