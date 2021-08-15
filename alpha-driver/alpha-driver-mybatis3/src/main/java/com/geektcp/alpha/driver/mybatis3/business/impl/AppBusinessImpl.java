package com.geektcp.alpha.driver.mybatis3.business.impl;

import com.geektcp.alpha.driver.mybatis3.business.AppBusiness;
import com.geektcp.alpha.driver.mybatis3.model.City;
import com.geektcp.alpha.driver.mybatis3.model.Card;
import com.geektcp.alpha.driver.mybatis3.model.Student;
import com.geektcp.alpha.driver.mybatis3.service.CityService;
import com.geektcp.alpha.driver.mybatis3.service.CardService;
import com.geektcp.alpha.driver.mybatis3.service.StudentService;
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
    public boolean addStudent(Student student, City city, Card card) {
        boolean rsAddCity = cityService.addCity(city);
        if (rsAddCity) {
            String cityName = city.getName();
            city = cityService.queryCityByName(cityName);
            if (city != null) {
                boolean rsAddIdCard = cardService.addIdCard(card);
                if (rsAddIdCard) {
                    String idCardCode = card.getCode();
                    card = cardService.queryIdCardByCode(idCardCode);
                    if (card != null) {
                        student.setCityId(city.getId()).setIdcardId(card.getId());
                        return studentService.addStudent(student);
                    } else
                        log.error("queryIdCardByCode 查询失败，idCardCode={}", idCardCode);
                } else
                    log.error("增加IdCard失败：{}", card);
            } else
                log.error("queryCityByName 查询失败，name={}", cityName);
        } else
            log.error("增加City失败：{}", city);

        return false;
    }
}
