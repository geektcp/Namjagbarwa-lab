package com.geektcp.alpha.db.es6.builder;

import org.elasticsearch.search.aggregations.AbstractAggregationBuilder;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * @author tanghaiyang on 2019/5/6.
 */
public class AggBuilder {

    public static List<AbstractAggregationBuilder> get(List<Map<String, Object>> aggregations) {
        if (aggregations == null){
            return Collections.emptyList();
        }
        List<AbstractAggregationBuilder> builders = new ArrayList<>();
        for (Map<String, Object> aggregation : aggregations) {
            builders.add(createAggregation(aggregation));
        }
        return builders;
    }

    private static AbstractAggregationBuilder createAggregation(Map<String, Object> aggregation) {
        return null;
    }



}
