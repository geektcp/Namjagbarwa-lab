package com.geektcp.alpha.algorithm.sort;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

/**
 * @author tanghaiyang on 2020/5/18 9:21.
 * 冒泡排序测试用例
 */
@Slf4j
public class BubbleTest {

    @Test
    public void sort() {
        Integer[] arr = ArrayUtil.build();
        log.info("sorted: {}", JSON.toJSONString(arr,true));
        Bubble<Integer> bubble = Bubble.build(Integer.class);

        bubble.sort(arr);
        log.info("sorted: {}", JSON.toJSONString(arr,true));
    }
}
