package com.geektcp.alpha.tool.upload;

import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author haiyang on 2020-03-28 13:37
 */
@Slf4j
public class DownLoadTest {

    @Test
    public void downLoad() {
        String resourcePath =  this.getClass().getResource("/").getPath();
        log.info("resourcePath: {}", resourcePath);
        Assert.assertTrue(true);
    }
}
