package com.geektcp.alpha.db.es6.builder;

import com.geektcp.alpha.db.es6.model.EsQuery;
import com.geektcp.alpha.db.es6.query.QType;
import org.apache.commons.lang3.StringUtils;
import org.elasticsearch.index.query.*;

import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * @author tanghaiyang on 2019/5/6.
 */
public class KeywordBuilder {

    public static QueryBuilder get(EsQuery esQuery) {
        BoolQueryBuilder keywordBuilder = QueryBuilders.boolQuery();
        String keyword = esQuery.getKeyword();
        if (StringUtils.isBlank(keyword)) {
            return keywordBuilder;
        }
        List<Map<String, Object>> query = esQuery.getQuery();
        for(Map<String, Object> schemaQuery: query) {
            float boost = Float.valueOf(schemaQuery.get("boost").toString()) ;
            String field = schemaQuery.get("field").toString();
            String operator = schemaQuery.get("operator").toString();
            MultiMatchQueryBuilder multiMatchQuery = QueryBuilders.multiMatchQuery(keyword);
            multiMatchQuery.boost(boost).tieBreaker(0.3F);
            multiMatchQuery.field(field, boost);
            keywordBuilder.should(multiMatchQuery);
            QueryBuilder queryBuilder = null;
            if (operator.equals(QType.MATCH.name())) {
                queryBuilder = QueryBuilders
                        .matchPhraseQuery(field, keyword)
                        .slop(3)
                        .boost(boost);
            } else if (operator.equals(QType.EXISTS.name())) {
                queryBuilder = QueryBuilders
                        .existsQuery(field)
                        .boost(boost);
            }
            if(Objects.nonNull(queryBuilder)) keywordBuilder.should(queryBuilder);
            String pattern = "*" + keyword + "*";
            WildcardQueryBuilder wildcardQueryBuilder = QueryBuilders.wildcardQuery(field, pattern);
            keywordBuilder.should(wildcardQueryBuilder);
        }
        return keywordBuilder;
    }
}
