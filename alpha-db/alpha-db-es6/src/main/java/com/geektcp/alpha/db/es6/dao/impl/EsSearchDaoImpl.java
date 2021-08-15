package com.geektcp.alpha.db.es6.dao.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.geektcp.alpha.db.es6.bean.StoreURL;
import com.geektcp.alpha.db.es6.client.EsRestClient;
import com.geektcp.alpha.db.es6.constant.HttpMethod;
import com.geektcp.alpha.db.es6.dao.EsSearchDao;
import com.geektcp.alpha.db.es6.model.EsQuery;
import com.geektcp.alpha.db.es6.model.EsQueryResult;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpEntity;
import org.apache.http.entity.ContentType;
import org.apache.http.nio.entity.NStringEntity;
import org.apache.http.util.EntityUtils;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.client.Response;
import org.elasticsearch.client.RestClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/**
 * @author tanghaiyang on 2019/5/7.
 */
@Slf4j
@Repository
public class EsSearchDaoImpl implements EsSearchDao {

    @Autowired
    private EsRestClient esRestClient;

    @Value("${es.async.traverse.pool.size:8}")
    private Integer poolSize;

    private ExecutorService asyncQueryExecutorService;

    @PostConstruct
    public void postConstruct() {
        asyncQueryExecutorService = Executors.newFixedThreadPool(poolSize == null ? 10 : poolSize);
    }

    private RestClient getClient(StoreURL storeURL) {
        return esRestClient.getClient(storeURL);
    }

    @Override
    public EsQueryResult search(StoreURL storeURL, EsQuery esQuery) {
        return null;
    }

    @Override
    public EsQueryResult searchByIds(StoreURL storeURL, EsQuery esQuery) {
        if (esQuery == null) {
            log.error("It cannot search with a empty EsQuery.");
            return new EsQueryResult();
        }
        String queryDSL = "";
        try {
//            wrapEsQuery(esQuery);
            Integer timeout = esQuery.getTimeout();
            Future<EsQueryResult> future = asyncQueryExecutorService.submit(() -> rawSearch(storeURL, esQuery));
            return future.get(timeout, TimeUnit.SECONDS);
        } catch (Exception e) {
            log.error("[{0}/{1}] search error, while query DSL:\n{2}\n{3}", e,
                    esQuery.getGraph(), esQuery.getTypes(), queryDSL, e.getMessage());
            return null;
        }
    }

    @Override
    public EsQueryResult searchByFields(StoreURL storeURL, EsQuery esQuery) {
        return null;
    }

    @Override
    public List<EsQueryResult> multiSearch(StoreURL storeURL, List<EsQuery> list) {
        return null;
    }

    @Override
    public EsQueryResult searchByDSL(StoreURL storeURL, String queryDSL) {
        try {
            RestClient restClient = this.getClient(storeURL);
            String url = storeURL.getUrl();
            HttpEntity entity = new NStringEntity(queryDSL, ContentType.APPLICATION_JSON);
            Response response = restClient.performRequest(HttpMethod.POST, "/es6_test16/_search", Collections.emptyMap(), entity);
            log.info("response:\n{}", JSON.toJSONString(JSONObject.parseObject(EntityUtils.toString(response.getEntity())),true));
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    //////////////////////////////////
    private EsQueryResult rawSearch(StoreURL storeURL, EsQuery esQuery){
        EsQueryResult esQueryResult = new EsQueryResult();
        try {
            RestClient restClient = this.getClient(storeURL);
            String url = storeURL.getUrl();
            String queryDSL = "{}";
            HttpEntity entity = new NStringEntity(queryDSL, ContentType.APPLICATION_JSON);
            Response response = restClient.performRequest(HttpMethod.POST, "/es6_test16/_search", Collections.emptyMap(), entity);
            JSONObject result = JSONObject.parseObject(EntityUtils.toString(response.getEntity()));
            List<Map<String,Object>> rows = (List<Map<String,Object>>)result.getJSONObject("hits").get("hits");
            esQueryResult.setRows(rows);
            esQueryResult.setTotal(rows.size());
            log.info("response:\n{}", JSON.toJSONString(esQueryResult,true));
        }catch (Exception e){
            e.printStackTrace();
        }
        return esQueryResult;
    }

    private  void wrapEsQuery(EsQuery esQuery){
        esQuery.setPageNo(0);
        int total = 0;
        Map<String, Object> schemas = esQuery.getSchemas();
        for(String type: schemas.keySet()){
            List<String> idsList = (List<String>)schemas.get(type);
            total += idsList.size();
        }
        esQuery.setPageNo(0);
        esQuery.setPageSize(total);
    }

    private SearchRequestBuilder createSearchBuilder(StoreURL storeURL, EsQuery esQuery) {
        RestClient restClient = this.getClient(storeURL);



        return null;
//        restClient.performRequest("POST","");
//        SearchRequestBuilder searchBuilder =
//                .prepareSearch(esQuery.getGraph())
//                .setTypes(esQuery.getTypes().toArray(new String[]{}))
//                .setQuery(RootBuilder.get(esQuery))
//                .setFetchSource(true)
//                .setFrom(esQuery.getPageNo())
//                .setSize(esQuery.getPageSize());
//        return searchBuilder;
    }

    private SearchRequestBuilder createIdsBuilder(StoreURL storeURL, EsQuery esQuery) {
//        SearchRequestBuilder searchRequestBuilder = this.getClient(storeURL)
//                .prepareSearch(esQuery.getGraph())
////                .setTypes(esQuery.getTypes().toArray(new String[]{}))
//                .setQuery(RootBuilder.getIds(esQuery))
//                .setFetchSource(true)
//                .setFrom(esQuery.getPageNo())
//                .setSize(esQuery.getPageSize());
//        return searchRequestBuilder;

        return null;
    }


}
