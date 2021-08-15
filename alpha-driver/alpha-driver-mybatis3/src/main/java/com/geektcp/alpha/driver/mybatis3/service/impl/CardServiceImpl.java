package com.geektcp.alpha.driver.mybatis3.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.geektcp.alpha.driver.mybatis3.model.Card;
import com.geektcp.alpha.driver.mybatis3.dao.CardDao;
import com.geektcp.alpha.driver.mybatis3.service.CardService;
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
public class CardServiceImpl extends ServiceImpl<CardDao, Card> implements CardService {

    @Override
    public boolean addIdCard(Card card) {
        if (queryIdCardByCode(card.getCode()) == null)
            return save(card);
        return true;
    }

    @Override
    public Card queryIdCardByCode(String code) {
        QueryWrapper<Card> queryWrapper =
                new QueryWrapper<Card>()
                        .eq(Card.CODE, code);
        List<Card> idCardList = list(queryWrapper);

        if (idCardList == null || idCardList.isEmpty())
            return null;

        if (idCardList.size() > 1)
            log.error("queryIdCardByCode有多个返回结果，code={}", code);

        return idCardList.get(0);
    }
}
