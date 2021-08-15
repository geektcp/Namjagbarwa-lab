package com.geektcp.alpha.db.es6.builder;

import com.geektcp.alpha.db.es6.model.EsQuery;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.IdsQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;

import java.util.List;
import java.util.Map;

/**
 * @author tanghaiyang on 2019/5/6.
 */
public class IdsBuilder {

    public static QueryBuilder get(EsQuery esQuery) {
        BoolQueryBuilder idsBuilder = QueryBuilders.boolQuery();
        Map<String, Object> schemas = esQuery.getSchemas();
        for(String type: schemas.keySet()){
            List<String> idsList = (List<String>)schemas.get(type);
            IdsQueryBuilder idsQueryBuilder = QueryBuilders.idsQuery(type);
            idsQueryBuilder.addIds(idsList.toArray(new String[]{}));
            idsBuilder.should(idsQueryBuilder);
        }
        return idsBuilder;
    }
}
