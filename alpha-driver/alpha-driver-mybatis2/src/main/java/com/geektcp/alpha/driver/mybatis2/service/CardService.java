package com.geektcp.alpha.driver.mybatis2.service;

import com.geektcp.alpha.driver.mybatis2.model.po.CardPo;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author tanghaiyang
 * @since 2018-08-31
 */
public interface CardService {

    boolean addIdCard(CardPo cardPo);

    boolean insert();

    boolean put();

    CardPo queryIdCardByCode(String code);

    List<CardPo> findPage(String code);
}
