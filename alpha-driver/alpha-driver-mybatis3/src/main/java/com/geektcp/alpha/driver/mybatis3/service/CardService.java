package com.geektcp.alpha.driver.mybatis3.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.geektcp.alpha.driver.mybatis3.model.Card;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author tanghaiyang
 * @since 2018-08-31
 */
public interface CardService extends IService<Card> {

    boolean addIdCard(Card card);

    Card queryIdCardByCode(String code);

}
