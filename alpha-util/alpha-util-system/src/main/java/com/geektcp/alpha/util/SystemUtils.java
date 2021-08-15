package com.geektcp.alpha.util;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.net.InetAddress;

/**
 * @author tanghaiyang on 2019/9/18.
 */
@Slf4j
public class SystemUtils {


    @Test
    public void getInetAddress() throws Exception{
        InetAddress inetAddress = InetAddress.getLocalHost();
        System.out.println(inetAddress.getHostAddress());
    }

    @Test
    public void getEnv(){
        int cores = Runtime.getRuntime().availableProcessors();
        log.info("cores: " + cores);

        long totalMemorySize = Runtime.getRuntime().totalMemory();
        log.info("totalMemorySize: " + totalMemorySize);

        long freeMemorySize = Runtime.getRuntime().freeMemory();
        log.info("freeMemorySize: " + freeMemorySize);

    }

    /**
     * System.gc()等价于Runtime.getRuntime().gc()
     */
    @Test
    public void executeGc(){
        Runtime.getRuntime().gc();
        System.gc();
    }
}
