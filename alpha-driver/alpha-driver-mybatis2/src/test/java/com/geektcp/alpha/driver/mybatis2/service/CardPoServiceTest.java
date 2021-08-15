package com.geektcp.alpha.driver.mybatis2.service;

import com.alibaba.fastjson.JSON;
import com.geektcp.alpha.driver.mybatis2.model.po.CardPo;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author haiyang on 3/27/20 3:19 PM.
 */
@Slf4j
@SpringBootTest
@RunWith(SpringRunner.class)
public class CardPoServiceTest {

    @Autowired
    private CardService cardService;

    @Test
    public void method() {
        Assert.assertTrue(true);
    }

    @Test
    public void name() {
        CardPo response = cardService.queryIdCardByCode("ghfhfkikkkkk");
        log.info("response: {}", JSON.toJSONString(response,true));
        Assert.assertTrue(true);
    }
}
