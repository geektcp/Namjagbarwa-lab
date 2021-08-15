package com.geektcp.alpha.db.es6.client;

import com.geektcp.alpha.db.es6.Application;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.RequestLine;
import org.apache.http.util.EntityUtils;
import org.elasticsearch.client.Response;
import org.elasticsearch.client.ResponseListener;
import org.elasticsearch.client.RestClient;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Collections;
import java.util.Map;

/**
 * @author tanghaiyang on 2019/5/8.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
@ActiveProfiles(value = "")
@Slf4j
public class EsClientTest {

    private RestClient restClient;


    @Test
    public void performRequest() throws Exception{
        Response response = restClient.performRequest("GET", "/");
        buildResponse(response);
    }

    @Test
    public void performRequestParams() throws Exception{
        Map<String, String> params = Collections.singletonMap("pretty", "true");
        Response response = restClient.performRequest("GET", "/", params);
        buildResponse(response);
    }

    @Test
    public void performRequestAsync()throws Exception{
        ResponseListener responseListener = new ResponseListener() {
            @Override
            public void onSuccess(Response response) {
                try {
                    buildResponse(response);
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
            @Override
            public void onFailure(Exception exception) {
            }
        };
        Map<String, String> params = Collections.singletonMap("pretty", "true");
        restClient.performRequestAsync("GET", "/", params, responseListener);
    }


    /////////////////////////////////////
    private String buildResponse(Response response) throws Exception{
        RequestLine requestLine = response.getRequestLine();
        HttpHost host = response.getHost();
        int statusCode = response.getStatusLine().getStatusCode();
        Header[] headers = response.getHeaders();
        HttpEntity httpEntity =  response.getEntity();
        String responseBody = EntityUtils.toString(httpEntity);
        log.info("responseBody:\n{}",responseBody);
        return responseBody;
    }

}
