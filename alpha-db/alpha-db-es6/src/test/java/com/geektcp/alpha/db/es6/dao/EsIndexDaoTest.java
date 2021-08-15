package com.geektcp.alpha.db.es6.dao;

import com.alibaba.fastjson.JSON;
import com.geektcp.alpha.common.spring.model.TResponse;
import com.geektcp.alpha.db.es6.bean.Source;
import com.geektcp.alpha.db.es6.bean.StoreURL;
import com.geektcp.alpha.db.es6.util.DataUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author tanghaiyang on 2019/5/9.
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles(profiles = "")
public class EsIndexDaoTest {

    @Autowired
    private EsIndexDao esIndexDao;

    private StoreURL storeURL;
    private static final String index = "es6_test16";
    private static final String type = "company";

    @Before
    public void init(){
        storeURL = new StoreURL();
        storeURL.setUrl("192.168.1.101:9200");
    }

    @Test
    public void testConnect(){
        try {
            esIndexDao.testConnect(storeURL);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Test
    public void existsIndex(){
        boolean ret = esIndexDao.existsIndex(storeURL, index);
        log.info("existsIndex: {}", ret);
        Assert.assertTrue(true);
    }

    @Test
    public void existsType(){
        boolean ret = esIndexDao.existsType(storeURL, index,type);
        log.info("existsType: {}", ret);
        Assert.assertTrue(true);
    }

    @Test
    public void createIndex(){
        boolean ret = esIndexDao.createIndex(storeURL,index);
        log.info("createIndex: {}", ret);
        Assert.assertTrue(true);
    }

    @Test
    public void deleteIndex(){
        boolean ret = esIndexDao.deleteIndex(storeURL,index);
        log.info("deleteIndex: {}", ret);
        Assert.assertTrue(true);
    }

    @Test
    public void createType(){
        boolean ret = esIndexDao.createType(storeURL,index,type);
        log.info("createType: {}", ret);
        Assert.assertTrue(true);
    }

    @Test
    public void bulkUpsert(){
        List<Source> sourceList = new ArrayList<>();
        Source source = new Source();
        source.setId("200");
        Map<String,Object> map = new HashMap<>();
        map.put("name","sdsdf");
        map.put("reg_address", "shenzhen");
        source.setSource(map);
        sourceList.add(source);
        log.info("sourceList: {}", JSON.toJSONString(sourceList,true));
        TResponse ret = esIndexDao.bulkUpsert(storeURL,index, type,sourceList);
        log.info("bulkUpsert:\n{}", JSON.toJSONString(ret,true));
        Assert.assertTrue(true);
    }

    @Test
    public void bulkInit(){
        List<Source> sourceList = new ArrayList<>();
        for (int i=1;i<=100;i++) {
            Source source = new Source();
            source.setId(String.valueOf(i));
            Map<String, Object> map = new HashMap<>();
            map.put("name", DataUtil.getRandomString(4));
            map.put("age", DataUtil.getRandomInt(2));
            map.put("reg_address", DataUtil.getRandomString(10));
            source.setSource(map);
            sourceList.add(source);
        }
        log.info("sourceList: {}", JSON.toJSONString(sourceList,true));
        TResponse ret = esIndexDao.bulkUpsert(storeURL, index, type, sourceList);
        log.info("bulkUpsert:\n{}", JSON.toJSONString(ret,true));
        Assert.assertTrue(true);
    }

    @Test
    public void delete(){
        List<Source> sourceList = new ArrayList<>();
        Source source = new Source();
        source.setId("100");
        Map<String,Object> map = new HashMap<>();
        map.put("name","sdsdf");
        map.put("age", 13);
        source.setSource(map);
        sourceList.add(source);
        TResponse ret =  esIndexDao.delete(storeURL, index, type, sourceList);
        log.info("delete:\n{}", JSON.toJSONString(ret,true));
        Assert.assertTrue(true);
    }

}
