package com.geektcp.alpha.db.arango;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.arangodb.ArangoCursor;
import com.arangodb.ArangoDB;
import com.arangodb.ArangoDatabase;
import com.arangodb.velocypack.VPackSlice;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author tanghaiyang on 2019/4/17.
 */
@Slf4j
public class SearchServiceTest {

    private static ArangoDB arangoDB;
    private String host = "192.168.1.176";

    @Before
    public void buildClient(){
        arangoDB = new ArangoDB.Builder()
                .host(host,8529)
                .user("root")
                .password("123456")
                .build();
    }

    @Test
    public void execute(){
        List<Map<String, Object>> list = new ArrayList<>();
        ArangoDatabase arangoDatabase = arangoDB.db("test");
        String gql = "WITH Person, Company\n" +
                "FOR v,e IN \n" +
                "any SHORTEST_PATH 'Company/7908891a00d02b29354c4dd5147de439' TO 'Company/36d37c063ee31a5aebcc3667af028715'\n" +
                "te_invest, te_transfer, te_guarantee, te_officer\n" +
                "RETURN {node:v, edge:e}";
        ArangoCursor<VPackSlice> cursor = arangoDatabase.query(gql, null, null, VPackSlice.class);
        cursor.forEachRemaining(vPackSlice -> {
            String json = vPackSlice.toString();
            log.info("==============:{}", json);
            try {
                List<Map<String, Object>> res = JSON.parseObject(json, new TypeReference<List<Map<String, Object>>>() {
                });
                if (!CollectionUtils.isEmpty(res)) list.addAll(res);
            } catch (Exception e) {
                try {
                    Map<String, Object> res = JSON.parseObject(json, new TypeReference<Map<String, Object>>() {
                    });
                    if (!CollectionUtils.isEmpty(res)) list.add(res);
                } catch (Exception ignore) {
                    log.error("fail to parse json: \n{}", json);
                }
            }
        });
        log.info("Arangodb search done. Size: {}", JSON.toJSONString(list, SerializerFeature.PrettyFormat));
    }


//    @Test
//    public void execute2(){
//        List<Map<String, Object>> list = new ArrayList<>();
//        ArangoDatabase arangoDatabase = arangoDB.db("test");
//        String gql = "WITH Person, Company\n" +
//                "FOR v,e IN \n" +
//                "any SHORTEST_PATH 'Company/7908891a00d02b29354c4dd5147de439' TO 'Company/36d37c063ee31a5aebcc3667af028715'\n" +
//                "te_invest, te_transfer, te_guarantee, te_officer\n" +
//                "RETURN {node:v, edge:e}";
//        ArangoCursor<String> cursor = arangoDatabase.query(gql, null, null, String.class);
//        cursor.forEachRemaining(vPackSlice -> {
//            String json = vPackSlice.toString();
//            log.info("==============:{}", json);
//            try {
//                List<Map<String, Object>> res = JSON.parseObject(json, new TypeReference<List<Map<String, Object>>>() {
//                });
//                if (!CollectionUtils.isEmpty(res)) list.addAll(res);
//            } catch (Exception e) {
//                try {
//                    Map<String, Object> res = JSON.parseObject(json, new TypeReference<Map<String, Object>>() {
//                    });
//                    if (!CollectionUtils.isEmpty(res)) list.add(res);
//                } catch (Exception ignore) {
//                    log.error("fail to parse json: \n{}", json);
//                }
//            }
//        });
//        log.info("Arangodb search done. Size: {}", JSON.toJSONString(list, SerializerFeature.PrettyFormat));
//    }
}
