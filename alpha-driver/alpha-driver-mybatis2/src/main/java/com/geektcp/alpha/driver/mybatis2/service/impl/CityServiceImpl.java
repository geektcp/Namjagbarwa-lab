package com.geektcp.alpha.driver.mybatis2.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.geektcp.alpha.driver.mybatis2.model.po.CityPo;
import com.geektcp.alpha.driver.mybatis2.dao.CityDao;
import com.geektcp.alpha.driver.mybatis2.service.CityService;
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
public class CityServiceImpl extends ServiceImpl<CityDao, CityPo> implements CityService {

    @Override
    public List<CityPo> queryCityAll() {
        return this.selectList(null);
    }

    @Override
    public boolean addCity(CityPo cityPo) {
        String name = cityPo.getName();
        if (queryCityByName(name) == null) {
            return this.insert(cityPo);
        }
        // 数据库已经存在
        return true;
    }

    @Override
    public CityPo queryCityByName(String name) {
        Wrapper<CityPo> queryWrapper = new EntityWrapper<>();
        queryWrapper.eq(CityPo.KEY, name);
        List<CityPo> cityPoList = this.selectList(queryWrapper);
        if (cityPoList == null || cityPoList.isEmpty()) {
            return null;
        }
        if (cityPoList.size() > 1) {
            log.error("queryCityByName结果有多个，name={}", name);
        }
        return cityPoList.get(0);
    }

}
