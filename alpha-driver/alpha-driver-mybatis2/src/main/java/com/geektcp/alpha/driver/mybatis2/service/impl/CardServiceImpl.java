package com.geektcp.alpha.driver.mybatis2.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.geektcp.alpha.driver.mybatis2.model.po.CardPo;
import com.geektcp.alpha.driver.mybatis2.dao.CardDao;
import com.geektcp.alpha.driver.mybatis2.service.CardService;
import com.google.common.collect.Lists;
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
public class CardServiceImpl extends ServiceImpl<CardDao, CardPo> implements CardService {

    @Override
    public boolean addIdCard(CardPo cardPo) {
        if (queryIdCardByCode(cardPo.getCode()) == null)
            return this.insert(cardPo);
        return true;
    }

    @Override
    public boolean insert() {
        CardPo po = new CardPo();
        po.setCode("111111");
        return this.insert(po);
    }

    @Override
    public boolean put() {
        CardPo po = new CardPo();
        po.setCode("22222");

        throw new RuntimeException();
//        return this.insert(po);
    }

    @Override
    public CardPo queryIdCardByCode(String code) {
        Wrapper<CardPo> queryWrapper = new EntityWrapper<>();
        queryWrapper.eq(CardPo.CODE, code);
        List<CardPo> idCardPoList = this.selectList(queryWrapper);

        if (idCardPoList == null || idCardPoList.isEmpty())
            return null;

        if (idCardPoList.size() > 1)
            log.error("queryIdCardByCode有多个返回结果，code={}", code);

        return idCardPoList.get(0);
    }

    @Override
    public List<CardPo> findPage(String code) {
        Wrapper<CardPo> queryWrapper = new EntityWrapper<>();
        queryWrapper.eq(CardPo.CODE, code);
        List<CardPo> idCardPoList = this.selectList(queryWrapper);

        if (idCardPoList == null || idCardPoList.isEmpty())
            return Lists.newArrayList();

        if (idCardPoList.size() > 1)
            log.error("queryIdCardByCode有多个返回结果，code={}", code);

        return idCardPoList;
    }
}
