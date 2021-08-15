package com.geektcp.alpha.db.es6.builder;

import com.geektcp.alpha.db.es6.model.EsQuery;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;

/**
 * Created by nagle on 2017/12/26.
 */
public class RootBuilder {


    public static BoolQueryBuilder get(EsQuery esQuery){
        BoolQueryBuilder rootBuilder = QueryBuilders.boolQuery();

        // keyword
        addMustClause(KeywordBuilder.get(esQuery), rootBuilder);
        // filter
        addMustClause(FilterBuilder.get(esQuery), rootBuilder);
        // aggregation
        return rootBuilder;
    }

    public static BoolQueryBuilder getIds(EsQuery esQuery){
        BoolQueryBuilder rootBuilder = QueryBuilders.boolQuery();
        addMustClause(IdsBuilder.get(esQuery), rootBuilder);
        return rootBuilder;
    }

    ///////////////////////
    // private functions
    ///////////////////////
    private static void addMustClause(QueryBuilder clause, BoolQueryBuilder rootBuilder){
        if (clause instanceof BoolQueryBuilder){
            if (((BoolQueryBuilder) clause).hasClauses()){
                rootBuilder.must(clause);
            }
        } else {
            rootBuilder.must(clause);
        }
    }

}
