package com.geektcp.alpha.driver.mybatis2.service;

import com.alibaba.fastjson.JSON;
import com.geektcp.alpha.driver.mybatis2.model.qo.PageQo;
import com.geektcp.alpha.driver.mybatis2.model.qo.PdaBatchManageQo;
import com.geektcp.alpha.driver.mybatis2.model.vo.PageResponseDTO;
import com.geektcp.alpha.driver.mybatis2.model.vo.PdaBatchManageVo;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author haiyang on 2020-03-30 13:13
 */
@Slf4j
@SpringBootTest
@RunWith(SpringRunner.class)
public class PdaBatchManageServiceTest {

    @Autowired
    private IPdaBatchManageService pdaBatchManageService;
    @Test
    public void findPage() {
        PdaBatchManageQo pdaBatchManageQo = new PdaBatchManageQo();
        pdaBatchManageQo.setStationSrcShipperId("otju64pi0dPrPNvsFXv");
        PageQo pageQo = new PageQo();
        pageQo.setPageNo(2);
        pageQo.setPageSize(4);
        pdaBatchManageQo.setPageQo(pageQo);
        PageResponseDTO<PdaBatchManageVo> response = pdaBatchManageService.findPage(pdaBatchManageQo);
        log.info("response: {}", JSON.toJSONString(response, true));
        Assert.assertTrue(true);
    }
}
