package com.geektcp.alpha.db.es6.dao;

import com.alibaba.fastjson.JSON;
import com.geektcp.alpha.db.es6.bean.StoreURL;
import com.geektcp.alpha.db.es6.model.EsQuery;
import com.geektcp.alpha.db.es6.model.EsQueryResult;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.IdsQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * @author tanghaiyang on 2019/6/6.
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles(profiles = "")
public class EsSearchDaoTest {

    @Autowired
    private EsSearchDao esSearchDao;

    private StoreURL storeURL;
    private static final String index = "es6_test16";
    private static final String type = "company";

    private static String dsl = "company";

    @Before
    public void init(){
        storeURL = new StoreURL();
        storeURL.setUrl("192.168.1.101:9200");
    }

    @Before
    public void buildDSL(){
        String type = "test";
        List<String> idsList = Lists.newArrayList("111111111", "sdfsdf222222");
        IdsQueryBuilder queryBuilders = QueryBuilders.idsQuery("test");
        log.info("queryBuilders:{}", queryBuilders.toString());

        BoolQueryBuilder idsBuilder = QueryBuilders.boolQuery();
        IdsQueryBuilder idsQueryBuilder = QueryBuilders.idsQuery(type);
        idsQueryBuilder.addIds(idsList.toArray(new String[]{}));
        idsBuilder.should(idsQueryBuilder);
        dsl = idsBuilder.toString();
        log.info("idsBuilder: {}", dsl);
    }

    @Test
    public void searchByIds(){
        EsQuery esQuery = new EsQuery();
        EsQueryResult esQueryResult = esSearchDao.searchByIds(storeURL, esQuery);
        log.info("esQueryResult:\n{}", JSON.toJSONString(esQueryResult,true));
    }

    @Test
    public void searchByDSL(){
        EsQueryResult esQueryResult = esSearchDao.searchByDSL(storeURL, dsl);
        log.info("esQueryResult:\n{}", JSON.toJSONString(esQueryResult,true));
    }
}
