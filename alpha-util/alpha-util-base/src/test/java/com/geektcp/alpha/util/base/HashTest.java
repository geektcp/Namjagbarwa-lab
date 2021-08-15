package com.geektcp.alpha.util.base;

import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author tanghaiyang on 2020/5/17 21:02.
 * String的hashcode计算是通过遍历每个char，
 * 每个char计算一次，每次计算获取当期char的ASCII码，前面的遍历计算结果乘以31，两者相加。
 * 那么结果相当于反复叠加。
 * 假设字符有n个，那么第一个字符对应ASCII值为a，a*31*n;
 * 第二个b*31*(n-1)
 * 直到最后一个
 */
@Slf4j
public class HashTest {

    @Test
    public void hashCodeTest() {
        String str1 = "aA";
        log.info("{}",str1.hashCode());
        String str2 = "Aa";
        log.info("{}",str1.hashCode());
        Assert.assertTrue(true);
    }
}
