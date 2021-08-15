package com.geektcp.alpha.spring.security.util;

import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author haiyang on 2020-04-16 16:20
 */
@Slf4j
public class UserTest {



    @Test
    public void ttt()throws Exception{
        String src = EncryptUtils.desEncrypt("111111111");
        log.info("src: {}", src);

        String dst = EncryptUtils.desDecrypt(src);
        log.info("dst: {}", dst);

        Assert.assertTrue(true);
    }

}
