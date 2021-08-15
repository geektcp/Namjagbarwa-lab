package com.geektcp.alpha.agent.constant;

/**
 * @author haiyang.tang on 12.02 002 17:00:46.
 */
public class AgentMetrics {

    private AgentMetrics() {
    }

    public static final String CASS_SYSTEM_AGENT_VERSION = "cass_system_agent_version";
    public static final String CASS_SYSTEM_CORES = "cass_system_cores";
    public static final String SYSTEM_OS = "os";
    public static final String SYSTEM_ARCH = "arch";
    public static final String SYSTEM_JDK_VERSION = "jdk_version";
    public static final String SYSTEM_IP = "ip";

    public static final String CASS_JVM_MAX_MEMORY = "cass_jvm_max_memory";
    public static final String CASS_JVM_COMMITTED_MEMORY = "cass_jvm_committed_memory";
    public static final String CASS_JVM_USED_MEMORY = "cass_jvm_used_memory";
    public static final String CASS_JVM_MAX_MEMORY_NON_HEAP = "cass_jvm_max_memory_non_heap";
    public static final String CASS_JVM_COMMITTED_MEMORY_NON_HEAP = "cass_jvm_committed_memory_non_heap";
    public static final String CASS_JVM_USED_MEMORY_NON_HEAP = "cass_jvm_used_memory_non_heap";
    public static final String CASS_JVM_THREAD_COUNT = "cass_jvm_thread_count";
    public static final String CASS_JVM_DAEMON_THREAD_COUNT = "cass_jvm_daemon_thread_count";
    public static final String CASS_JVM_TOTAL_STARTED_THREAD_COUNT = "cass_jvm_total_started_thread_count";

    public static final String CASS_REQUEST_COST_TOTAL_MILLISECONDS = "cass_request_cost_total_milliseconds";
    public static final String CASS_REQUEST_COUNT_TOTAL = "cass_request_count_total";
    public static final String CASS_REQUEST_ERR_COUNT_TOTAL = "cass_request_count_err_total";

    public static final String CASS_REQUEST_COST_MILLISECONDS = "cass_request_cost_milliseconds";
    public static final String CASS_REQUEST_AVERAGE_COST_MILLISECONDS = "cass_request_average_cost_milliseconds";

    public static final String CASS_REQUEST_COUNT = "cass_request_count";
    public static final String CASS_REQUEST_ERR_COUNT = "cass_request_err_count";

    public static final String CASS_DB_POOL_COUNT_TOTAL = "cass_db_pool_count_total";

}
