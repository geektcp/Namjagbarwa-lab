package com.geektcp.alpha.agent.constant;

/**
 * @author haiyang.tang on 12.10 010 13:56:51.
 */
public class AgentFilter {

    private AgentFilter() {
    }

    public static final int FILTER_STATUS_5XX = 499;

    public static final String FILTER_PARAMETER_REQUEST_FACADE = "RequestFacade";
    public static final String FILTER_PARAMETER_APPLICATION_HTTP_REQUEST = "ApplicationHttpRequest";

    public static final String FILTER_METHOD_DO_SERVICE = "doService";
    public static final String FILTER_METHOD_PROCESS_REQUEST = "processRequest";

    public static final String FILTER_METHOD_GET_REQUEST_URI = "getRequestURI";
    public static final String FILTER_METHOD_GET_METHOD = "getMethod";
    public static final String FILTER_METHOD_GET_STATUS = "getStatus";

    public static final String FILTER_METHOD_GET_CONTEXT_PATH = "getContextPath";
    public static final String FILTER_METHOD_GET_SERVLET_PATH = "getServletPath";


    public static final String FILTER_METRIC_HTTP_START = "httpStart";
    public static final String FILTER_METRIC_HTTP_METHOD = "httpMethod";
    public static final String FILTER_METRIC_HTTP_PATH = "httpPath";



}
