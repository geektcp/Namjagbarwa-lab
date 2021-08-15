package com.geektcp.alpha.driver.mybatis2.service;

import com.geektcp.alpha.driver.mybatis2.model.qo.PageQo;
import com.geektcp.alpha.driver.mybatis2.model.vo.PageResponse;
import com.geektcp.alpha.driver.mybatis2.model.qo.UserQo;
import com.geektcp.alpha.driver.mybatis2.model.vo.UserVo;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
/**
 * @author haiyang on 2020-03-27 16:27
 */
@Slf4j
@SpringBootTest
@RunWith(SpringRunner.class)
public class UserServiceTest {

    @Autowired
    private UserService userService;

    @Test
    public void method() {
        Assert.assertTrue(true);
    }

    @Test
    public void findPage() {
        UserQo qo = new UserQo();
        PageQo pageQo = new PageQo(2,3);
        qo.setPageQo(pageQo);
        PageResponse<UserVo> response = userService.findPage(qo);
//        log.info("response: {}", JSON.toJSONString(response,true));
        Assert.assertTrue(true);
    }


}
