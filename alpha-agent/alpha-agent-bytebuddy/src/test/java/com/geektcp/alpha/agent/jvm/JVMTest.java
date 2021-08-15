package com.geektcp.alpha.agent.jvm;

import javax.management.MBeanServer;
import java.lang.management.ManagementFactory;
import java.lang.management.MemoryMXBean;
import java.lang.management.OperatingSystemMXBean;
import java.lang.management.ThreadMXBean;
import java.net.InetAddress;

/**
 * @author haiyang.tang on 12.03 003 16:59:19.
 */
public class JVMTest {
    public static void main(String[] args) {
        OperatingSystemMXBean system = ManagementFactory.getOperatingSystemMXBean();
        System.out.println(system.getName());
        System.out.println(system.getArch());
        System.out.println(system.getAvailableProcessors());
        System.out.println(system.getVersion());
        System.out.println(system.getSystemLoadAverage());
        System.out.println("==============");
        System.out.println(System.getProperty("java.version"));

        System.out.println("totalMemory: " + Runtime.getRuntime().totalMemory() / (1024.0 * 1024));
        System.out.println(Runtime.getRuntime().freeMemory() / (1024.0 * 1024));
        System.out.println(Runtime.getRuntime().maxMemory() / (1024.0 * 1024));
        System.out.println(Runtime.getRuntime().availableProcessors());

        ThreadMXBean tmx = ManagementFactory.getThreadMXBean();
        System.out.println("thread count:" + tmx.getThreadCount());
        System.out.println(tmx.getDaemonThreadCount());
        System.out.println(tmx.getTotalStartedThreadCount());

        MBeanServer mBeanServer = ManagementFactory.getPlatformMBeanServer();

        System.out.println(mBeanServer.getDefaultDomain());
        System.out.println(getLocalIP());

        MemoryMXBean memoryMXBean = ManagementFactory.getMemoryMXBean();
        System.out.println(memoryMXBean.getHeapMemoryUsage().getMax() / (1024.0 * 1024));
        System.out.println(memoryMXBean.getNonHeapMemoryUsage());

    }


    public static String getLocalIP() {
        String ipAddrStr = "";
        try {
            ipAddrStr = InetAddress.getLocalHost().getHostAddress();
        } catch (Exception e) {
            //
        }
        return ipAddrStr;
    }

}
