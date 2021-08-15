package com.geektcp.alpha.agent.builder;

import java.lang.management.*;
import java.net.InetAddress;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicLong;

import static com.geektcp.alpha.agent.constant.AgentMetrics.*;
import static com.geektcp.alpha.agent.util.LogUtil.log;

/**
 * @author haiyang.tang on 12.02 002 18:00:19.
 */
public class AgentCacheBuilder {

    private static Random random = new Random(1000);

    private static ConcurrentMap<String, AtomicLong> thyCache = new ConcurrentHashMap<>(10000);

    private AgentCacheBuilder() {
    }

    private static AgentCacheBuilder singleton = null;

    public static AgentCacheBuilder getInstance() {
        if (singleton == null) {
            singleton = new AgentCacheBuilder();
        }
        return singleton;
    }

    public static AtomicLong get(String key) {
        return thyCache.getOrDefault(key, new AtomicLong(0));
    }

    public static void put(String key, AtomicLong value) {
        thyCache.put(key, value);
    }

    public static void incrementAndGet(String key, long delta) {
        AtomicLong value = get(key);
        value.addAndGet(delta);
        put(key, value);
    }

    public static void init() {
//        log("clear and init cache!");
        thyCache.clear();
        AgentCacheBuilder.put(CASS_SYSTEM_AGENT_VERSION, new AtomicLong(1));

        AgentCacheBuilder.put(CASS_REQUEST_COUNT_TOTAL, new AtomicLong(1));
        AgentCacheBuilder.put(CASS_REQUEST_ERR_COUNT_TOTAL, new AtomicLong(0));
        AgentCacheBuilder.put(CASS_REQUEST_COST_TOTAL_MILLISECONDS, new AtomicLong(0));

//        AgentCacheBuilder.put(CASS_REQUEST_COUNT_TOTAL, new AtomicLong(random.nextInt(500)));
//        AgentCacheBuilder.put(CASS_REQUEST_ERR_COUNT_TOTAL, new AtomicLong(random.nextInt(100)));
//        AgentCacheBuilder.put(CASS_REQUEST_COST_TOTAL_MILLISECONDS, new AtomicLong(random.nextInt(10000)));
//        AgentCacheBuilder.put(CASS_REQUEST_AVERAGE_COST_MILLISECONDS, new AtomicLong(random.nextInt(1000)));
//        AgentCacheBuilder.put(CASS_DB_POOL_COUNT_TOTAL, new AtomicLong(random.nextInt(200)));
    }

    public static void incrementAndGet(String key) {
        AtomicLong value = get(key);
        value.incrementAndGet();
        put(key, value);
    }

    public static List<String> listCache() {
        List<String> list = new ArrayList<>();
        Set<String> keys = thyCache.keySet();
        try {
            for (String key : keys) {
                if (Objects.isNull(thyCache.getOrDefault(key, new AtomicLong(0)))) {
                    continue;
                }
                list.add(key + " " + thyCache.getOrDefault(key, new AtomicLong(0)));
            }
            long cost = thyCache.getOrDefault(CASS_REQUEST_COST_TOTAL_MILLISECONDS, new AtomicLong(0)).get();
            long count = thyCache.getOrDefault(CASS_REQUEST_COUNT_TOTAL, new AtomicLong(0)).get();
            if (count > 0) {
                long average = cost / count;
                list.add(CASS_REQUEST_AVERAGE_COST_MILLISECONDS + " " + average);
            }
        } catch (Exception e) {
            log("listCache: " + e.getMessage());
        }
        return list;
    }

    public static List<String> listSystem() {
        List<String> list = new ArrayList<>();
        try {
            OperatingSystemMXBean system = ManagementFactory.getOperatingSystemMXBean();
            StringBuilder systemMetric = new StringBuilder();
            systemMetric.append(CASS_SYSTEM_CORES).append("{")
                    .append(SYSTEM_OS).append(buildValue(system.getName())).append(",")
                    .append(SYSTEM_ARCH).append(buildValue(system.getArch())).append(",")
                    .append(SYSTEM_JDK_VERSION).append(buildValue(System.getProperty("java.version"))).append(",")
                    .append(SYSTEM_IP).append(buildValue(getLocalIP()))
                    .append("}").append(" ").append(system.getAvailableProcessors());
            list.add(systemMetric.toString());

            MemoryMXBean memoryMXBean = ManagementFactory.getMemoryMXBean();
            MemoryUsage heapMemoryUsage = memoryMXBean.getHeapMemoryUsage();
            list.add(buildMetric(CASS_JVM_MAX_MEMORY, heapMemoryUsage.getMax()));
            list.add(buildMetric(CASS_JVM_COMMITTED_MEMORY, heapMemoryUsage.getCommitted()));
            list.add(buildMetric(CASS_JVM_USED_MEMORY, heapMemoryUsage.getUsed()));

            MemoryUsage nonHeapMemoryUsage = memoryMXBean.getNonHeapMemoryUsage();
            list.add(buildMetric(CASS_JVM_MAX_MEMORY_NON_HEAP, nonHeapMemoryUsage.getMax()));
            list.add(buildMetric(CASS_JVM_COMMITTED_MEMORY_NON_HEAP, nonHeapMemoryUsage.getCommitted()));
            list.add(buildMetric(CASS_JVM_USED_MEMORY_NON_HEAP, nonHeapMemoryUsage.getUsed()));

            ThreadMXBean tmx = ManagementFactory.getThreadMXBean();
            list.add(buildMetric(CASS_JVM_THREAD_COUNT, tmx.getThreadCount()));
            list.add(buildMetric(CASS_JVM_DAEMON_THREAD_COUNT, tmx.getThreadCount()));
            list.add(buildMetric(CASS_JVM_TOTAL_STARTED_THREAD_COUNT, tmx.getThreadCount()));
        } catch (Exception e) {
            log(e.getMessage());
        }
        return list;
    }

    private static String getLocalIP() {
        String ip = "";
        try {
            ip = InetAddress.getLocalHost().getHostAddress();
        } catch (Exception e) {
            log(e.getMessage());
        }
        return ip;
    }

    private static String buildMetric(String key, Object value) {
        return key + " " + value;
    }

    private static String buildValue(String value) {
        return "=\"" + value + "\"";
    }

}
