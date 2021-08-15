package com.geektcp.alpha.db.arango;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

/**
 * @author tanghaiyang on 2019/4/15.
 */
@Slf4j
public class HttpImportTest {

    private static RestTemplate restTemplate = new RestTemplate();
    private static String urlDatabase = "http://alpha-server:8529/_db/_system/_api/database";
    private static String urlCollection = "http://alpha-server:8529/_db/test4/_api/collection";

    @Test
    public void createDatabase() {
        JSONObject payload = new JSONObject();
        payload.put("name", "test4");
        ResponseEntity responseEntity = restTemplate.postForEntity(urlDatabase, payload, JSONObject.class);
        JSONObject response = (JSONObject)responseEntity.getBody();
        log.info("response: {}", response);

    }

    @Test
    public void createCollection(){
        JSONObject payload = new JSONObject();
        payload.put("name", "collection1");
        ResponseEntity responseEntity = restTemplate.postForEntity(urlDatabase, payload, JSONObject.class);
        JSONObject response = (JSONObject)responseEntity.getBody();
        log.info("response: {}", response);

    }

}
