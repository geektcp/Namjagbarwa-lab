package com.geektcp.alpha.algorithm.arithmetic;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;

/**
 * @author tanghaiyang on 2019/8/8.
 */
public class Lalian implements HashMap.Entry{

    @Override
    public Object getKey() {
        return null;
    }

    @Override
    public Object getValue() {
        return null;
    }

    @Override
    public Object setValue(Object value) {
        return null;
    }

    /**
     * 拉链法求数据集合
     * 把两个集合当成两条拉链，移动指针就是在拉拉链
     */
    @Test
    public void compute() throws Exception {
        List<Integer> list1 = Lists.newArrayList(1, 3, 5, 7, 8, 9);
        List<Integer> list2 = Lists.newArrayList(2, 3, 4, 5, 6, 7);
        List<Integer> list3 = Lists.newArrayList();

        int i = 0;
        int j = 0;
        int loop = 0;
        while (i < list1.size() && j < list2.size()) {
            int ele1 = list1.get(i);
            int ele2 = list2.get(j);
            if (ele1 < ele2) {
                i++;
            } else if (ele1 == ele2) {
                list3.add(ele1);
                i++;
            } else {
                j++;
            }
            System.out.println(i);
            System.out.println(j);
            Thread.sleep(10);
            loop++;
        }

        System.out.println("=====================: " + loop);
        System.out.println(JSON.toJSONString(list3, true));

    }


}
