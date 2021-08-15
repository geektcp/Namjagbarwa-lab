package com.geektcp.alpha.db.es6.client;

import com.geektcp.alpha.db.es6.bean.StoreURL;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.Header;
import org.apache.http.HttpHost;
import org.apache.http.message.BasicHeader;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.stereotype.Repository;

import javax.annotation.PreDestroy;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author tanghaiyang on 2018/11/5.
 */
@Slf4j
@Repository("esRestClient")
public class EsRestClient {
    private static final int DEFAULT_HTTP_PORT = 9200;

    private static final Map<StoreURL, RestClient> POOL = new ConcurrentHashMap<>();
    private static final Map<StoreURL, RestHighLevelClient> HIGH_POOL = new ConcurrentHashMap<>();

    @PreDestroy
    public void cleanup() {
        POOL.values().forEach(restClient -> {
            try {
                restClient.close();
            } catch (IOException e) {
                log.warn("close client got exception ", e);
            }
        });
    }

    public RestClient getClient(String url) {
        StoreURL storeURL = new StoreURL();
        storeURL.setUrl(url);
        return getClient(storeURL);
    }

    public RestClient getClient(StoreURL storeURL) {
        try {
            RestClient client = POOL.get(storeURL);
            if (client == null) {
                client = getRestClientBuilder(storeURL).build();
                POOL.put(storeURL, client);
            }
            return client;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public RestHighLevelClient getRestHighLevelClient(StoreURL storeURL) {
        try {
            RestHighLevelClient client = HIGH_POOL.get(storeURL);
            if (client == null) {
                client = getRestHighLevelClientFI(storeURL);
                HIGH_POOL.put(storeURL, client);
            }
            return client;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    ///////////////////////
    // private functions
    ///////////////////////
    private RestHighLevelClient getRestHighLevelClientFI(StoreURL storeURL) {
        return new RestHighLevelClient(getRestClientBuilder(storeURL));
    }

    private RestClient create(String url) {
        StoreURL storeURL = new StoreURL();
        storeURL.setUrl(url);
        return create(storeURL);
    }

    private RestClient create(StoreURL storeURL) {
        RestClient restClient = null;
        try {
            List<HttpHost> httpHosts = buildHost(storeURL);
            restClient = RestClient.builder(httpHosts.toArray(new HttpHost[]{})).build();
            log.info("success to create elasticsearch rest client url[{}]", storeURL.getUrl());
        } catch (Exception e) {
            log.error("failed to create elasticsearch rest client url[{}].\n", e, storeURL.getUrl());
        }
        return restClient;
    }

    private RestClientBuilder getRestClientBuilder(StoreURL storeURL) {
        RestClientBuilder restClientBuilder = null;
        try {
            Header[] defaultHeaders = new Header[]{new BasicHeader("Accept", "application/json"),
                    new BasicHeader("Content-type", "application/json")};
            List<HttpHost> hostList = buildHostNew(storeURL);
            restClientBuilder = RestClient.builder(hostList.toArray(new HttpHost[]{}))
                    .setDefaultHeaders(defaultHeaders);
            log.info("success to create elasticsearch rest client url[{}]", storeURL.getUrl());
        } catch (Exception e) {
            log.error("failed to create elasticsearch rest client url[{}].\n", e, storeURL.getUrl());
        }
        return restClientBuilder;
    }

    /**
     * 192.168.1.50:9200,192.168.1.51:9200,192.168.1.52:9200
     *
     * @param storeURL
     * @return
     */
    public static List<HttpHost> buildHostNew(StoreURL storeURL) {
        String esServerHost = storeURL.getUrl();
        List<HttpHost> hostList = new ArrayList<>();
        String[] hostArray = esServerHost.split(",");

        for (String host : hostArray) {
            String[] ipPort = host.split(":");
            HttpHost hostNew = new HttpHost(ipPort[0], Integer.valueOf(ipPort[1]), "http");
            hostList.add(hostNew);
        }
        return hostList;
    }

    /**
     *
     * 192.168.1.50,192.168.1.51,192.168.1.52:9200
     * elasticsearch://192.168.1.50,192.168.1.51,192.168.1.52:9300
     * elasticsearch:http//192.168.1.50,192.168.1.51,192.168.1.52:9200
     *
     * @param storeURL
     * @return
     */
    private List<HttpHost> buildHost(StoreURL storeURL) {
        String url = storeURL.getUrl();
        if (StringUtils.contains(url, ":http//")) {
            url = url.split(":http//")[1];
        }
        if (StringUtils.contains(url, "://")) {
            url = url.split("://")[1];
            url = getHttpUrlByTcp(url);
        }
        String[] hosts = StringUtils.substringBefore(url, ":").split(",");
        int port = Integer.parseInt(StringUtils.substringAfter(url, ":"));

        // rest client builder
        List<HttpHost> httpHosts = new ArrayList<>();
        for (String host : hosts) {
            httpHosts.add(new HttpHost(host, DEFAULT_HTTP_PORT));
        }
        return httpHosts;
    }

    private String getHttpUrlByTcp(String tcpUrl) {
        tcpUrl = StringUtils.substringBefore(tcpUrl, ":");
        tcpUrl += ":" + DEFAULT_HTTP_PORT;
        return tcpUrl;
    }

}