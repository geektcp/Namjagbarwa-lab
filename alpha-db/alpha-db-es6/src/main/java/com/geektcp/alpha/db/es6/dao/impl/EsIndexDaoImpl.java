package com.geektcp.alpha.db.es6.dao.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.geektcp.alpha.common.spring.model.TResponse;
import com.geektcp.alpha.db.es6.bean.Source;
import com.geektcp.alpha.db.es6.bean.StoreURL;
import com.geektcp.alpha.db.es6.bean.mapping.Template;
import com.geektcp.alpha.db.es6.client.EsRestClient;
import com.geektcp.alpha.db.es6.dao.EsIndexDao;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.entity.ContentType;
import org.apache.http.nio.entity.NStringEntity;
import org.apache.http.util.EntityUtils;
import org.elasticsearch.action.DocWriteRequest;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.client.Response;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.rest.RestStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author tanghaiyang on 2019/5/9.
 */
@Slf4j
@Repository
public class EsIndexDaoImpl implements EsIndexDao {

    private static final String PUT = "PUT";
    private static final String POST = "POST";
    private static final String GET = "GET";
    private static final String HEAD = "HEAD";
    private static final String DELETE = "DELETE";

    @Autowired
    private EsRestClient esRestClient;

    @Override
    public void testConnect(StoreURL storeURL) {
        esRestClient.getRestHighLevelClient(storeURL);
    }

    @Override
    public boolean existsIndex(StoreURL storeURL, String index) {
        RestClient client = esRestClient.getClient(storeURL);
        try {
            Response response = client.performRequest(HEAD, index);
            return response.getStatusLine().getReasonPhrase().equals("OK");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean existsType(StoreURL storeURL, String index, String type) {
        try {
            RestClient client = esRestClient.getClient(storeURL);
            Response response = client.performRequest(HEAD, new StringBuilder(index).append("/_mapping/").append
                    (type).toString());
            return response.getStatusLine().getReasonPhrase().equals("OK");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean createIndex(StoreURL storeURL, String index) {
        try {
            RestClient client = esRestClient.getClient(storeURL);
            Map map = new HashMap<>();
            IndexRequest request = new IndexRequest(index).source(map);
            request.opType(DocWriteRequest.OpType.CREATE);
            String source = request.source().utf8ToString();
            HttpEntity entity = new NStringEntity(source, ContentType.APPLICATION_JSON);
            Response response = client.performRequest(PUT, index, Collections.<String, String>emptyMap(), entity);
            log.info("response: \n{}", JSON.toJSONString(response, true));
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean deleteIndex(StoreURL storeURL, String index) {
        try {
            RestClient client = esRestClient.getClient(storeURL);
            ;
            Response rsp = client.performRequest(DELETE, "/" + index + "?&pretty=true");
            if (HttpStatus.SC_OK == rsp.getStatusLine().getStatusCode()) {
                log.info("Delete successful.");
            } else {
                log.error("Delete failed.");
            }
            log.info("Delete response entity is : " + EntityUtils.toString(rsp.getEntity()));
        } catch (Exception e) {
            log.error("Delete failed, exception occurred.", e);
        }
        return false;
    }

    @Override
    public boolean createType(StoreURL storeURL, String index, String type) {
        String wholeIndex = index + "." + type;
        try {
            String url = wholeIndex + "/_mapping/" + type;
            RestClient client = esRestClient.getClient(storeURL);
            IndexRequest request = new IndexRequest(index, type).source(Template.getTypeMapping());
            request.opType(DocWriteRequest.OpType.CREATE);
            String source = request.source().utf8ToString();
            log.info("source :\n{}", JSON.toJSONString(JSONObject.parseObject(source), true));
            HttpEntity entity = new NStringEntity(source, ContentType.APPLICATION_JSON);
            Response response = client.performRequest(PUT, url, Collections.<String, String>emptyMap(), entity);

            if (RestStatus.OK.getStatus() == response.getStatusLine().getStatusCode()) {
                log.info("Success to create type[{}/{}]", index, type);
            } else {
                return false;
            }
            return true;
        } catch (Exception e) {
            log.error("Failed to create type[{}/{}]\n", e, 1, type);
        }
        return false;
    }

    @Override
    public TResponse bulkUpsert(StoreURL storeURL, String index, String type, List<Source> sourceList) {
        TResponse cudTResponse = new TResponse();
        if (sourceList == null || sourceList.isEmpty()) {
            log.error("It cannot upsert index with a EMPTY Source set:" + sourceList);
            cudTResponse.setMessage("sourceList is null or empty");
            return cudTResponse;
        }
        try {
            RestHighLevelClient highLevelClient = esRestClient.getRestHighLevelClient(storeURL);
            BulkRequest bulkRequest = new BulkRequest();
            for (Source source : sourceList) {
                bulkRequest.add(new IndexRequest(index, type, source.getId()).source(source.getSource()));
            }
            BulkResponse bulkResponse = highLevelClient.bulk(bulkRequest);
            if (bulkResponse.hasFailures()) {
                cudTResponse.setSuccess(false);
                log.error("upsert failed {}|{}|{}|{3} ", index, type, bulkResponse.buildFailureMessage(),
                        sourceList.size());
            } else {
                cudTResponse.setSuccess(true);
                log.info("Success to bulk upsert [{}] records with type[{}/{}]", index, type, sourceList.size());
            }
        } catch (Exception e) {
            cudTResponse.setSuccess(false);
            log.error("Failed to bulk upsert with type[{}/{}]", e, index, type, JSON.toJSONString(sourceList));
        }
        return cudTResponse;
    }

    @Override
    public TResponse delete(StoreURL storeURL, String index, String type, List<Source> sources) {
        TResponse cudResponse = new TResponse();
        if (sources == null || sources.isEmpty()) {
            log.error("It cannot delete index with a EMPTY Source set:" + sources);
            cudResponse.setSuccess(false);
            return cudResponse;
        }
        try {
            RestHighLevelClient highLevelClient = esRestClient.getRestHighLevelClient(storeURL);
            BulkRequest bulkRequest = new BulkRequest();
            for (Source source : sources) {
                DeleteRequest dr = new DeleteRequest(index, type, source.getId());
                bulkRequest.add(dr);
            }
            BulkResponse bulkResponse = highLevelClient.bulk(bulkRequest);
            if (bulkResponse.hasFailures()) {
                log.error("One or more indexes delete failed with type[{}/{}] due to:\n{}\n{3}",
                        index, type, bulkResponse.buildFailureMessage(), JSON.toJSONString(sources, true));
            } else {
                cudResponse.setSuccess(true);
                log.info("Success to bulk delete [{}] records for type[{}/{}].", sources.size(), index, type);
            }
        } catch (Exception e) {
            cudResponse.setSuccess(false);
            log.error("Failed to bulk delete [{}] records with type[{}/{}],data:\n{3}", e,
                    sources.size(), index, type, JSON.toJSONString(sources, true));
        }
        return cudResponse;
    }
}
