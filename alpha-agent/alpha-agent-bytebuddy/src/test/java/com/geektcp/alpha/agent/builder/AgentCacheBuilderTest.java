package com.geektcp.alpha.agent.builder;

import java.lang.management.ManagementFactory;
import java.lang.management.MemoryPoolMXBean;
import java.util.List;

/**
 * @author haiyang.tang on 12.09 009 20:25:22.
 */
public class AgentCacheBuilderTest {

    public static void main(String[] args) {
        List<MemoryPoolMXBean> list =  ManagementFactory.getMemoryPoolMXBeans();
        System.out.println(list);
        for(MemoryPoolMXBean bean : list){
            System.out.println(bean.getName());
        }

    }
}
