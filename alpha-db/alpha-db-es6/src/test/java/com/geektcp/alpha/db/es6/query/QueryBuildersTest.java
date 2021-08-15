package com.geektcp.alpha.db.es6.query;

import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.IdsQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.junit.Test;

import java.util.List;

/**
 * @author tanghaiyang on 2019/6/10.
 */
@Slf4j
public class QueryBuildersTest {


    @Test
    public void test(){
        String type = "test";
        List<String> idsList = Lists.newArrayList("111111111", "sdfsdf222222");
        IdsQueryBuilder queryBuilders = QueryBuilders.idsQuery("test");
        log.info("queryBuilders:{}", queryBuilders.toString());

        BoolQueryBuilder idsBuilder = QueryBuilders.boolQuery();
        IdsQueryBuilder idsQueryBuilder = QueryBuilders.idsQuery(type);
        idsQueryBuilder.addIds(idsList.toArray(new String[]{}));
        idsBuilder.should(idsQueryBuilder);

        log.info("idsBuilder: {}", idsBuilder.toString());

    }
}
