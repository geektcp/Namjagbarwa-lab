package com.geektcp.alpha.driver.mybatis2.business.impl;

import com.geektcp.alpha.driver.mybatis2.business.AppBusiness;
import com.geektcp.alpha.driver.mybatis2.model.po.StudentPo;
import com.geektcp.alpha.driver.mybatis2.model.po.CityPo;
import com.geektcp.alpha.driver.mybatis2.model.po.CardPo;
import com.geektcp.alpha.driver.mybatis2.service.CityService;
import com.geektcp.alpha.driver.mybatis2.service.CardService;
import com.geektcp.alpha.driver.mybatis2.service.StudentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * 数据库表关联逻辑处理接口实现
 *
 * @author tanghaiyang
 */
@Service
@Transactional
@Slf4j
public class AppBusinessImpl implements AppBusiness {

    @Resource
    private CityService cityService;

    @Resource
    private CardService cardService;

    @Resource
    private StudentService studentService;

    @Override
    public boolean addStudent(StudentPo studentPo, CityPo cityPo, CardPo cardPo) {
        boolean rsAddCity = cityService.addCity(cityPo);
        if (rsAddCity) {
            String cityName = cityPo.getName();
            cityPo = cityService.queryCityByName(cityName);
            if (cityPo != null) {
                boolean rsAddIdCard = cardService.addIdCard(cardPo);
                if (rsAddIdCard) {
                    String idCardCode = cardPo.getCode();
                    cardPo = cardService.queryIdCardByCode(idCardCode);
                    if (cardPo != null) {
                        studentPo.setCityId(cityPo.getId()).setIdcardId(cardPo.getId());
                        return studentService.addStudent(studentPo);
                    } else
                        log.error("queryIdCardByCode 查询失败，idCardCode={}", idCardCode);
                } else
                    log.error("增加IdCard失败：{}", cardPo);
            } else
                log.error("queryCityByName 查询失败，name={}", cityName);
        } else
            log.error("增加City失败：{}", cityPo);

        return false;
    }
}
