package com.geektcp.alpha.db.es6.builder;

import com.geektcp.alpha.db.es6.constant.GOperator;
import com.geektcp.alpha.db.es6.constant.LogicOperator;
import com.geektcp.alpha.db.es6.model.EsQuery;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;

import java.util.List;
import java.util.Map;
import java.util.Objects;

import static com.geektcp.alpha.db.es6.constant.EsKeys.*;


/**
 * @author tanghaiyang on 2019/5/6.
 */
public class FilterBuilder {

    public static QueryBuilder get(EsQuery esQuery) {
        Map<String, Object> filter = esQuery.getFilter();
        return buildFilterTree(filter);
    }

    private static BoolQueryBuilder buildFilterTree(Map<String, Object> filter) {
        BoolQueryBuilder filterBuilder = QueryBuilders.boolQuery();
        if(Objects.nonNull(filter) && filter.containsKey(ES_LOGIC_OPERATOR) && filter.containsKey(ES_RULES)) {
            String logicOperatorStr = filter.get(ES_LOGIC_OPERATOR).toString();
            List<Map<String, Object>> ruleList = (List<Map<String, Object>>) filter.get(ES_RULES);
            for (Map<String, Object> rule : ruleList) {
                BoolQueryBuilder ruleBuilder = buildRule(rule);
                if (Objects.isNull(ruleBuilder)) return null;
                LogicOperator logicOperator = LogicOperator.byValue(logicOperatorStr.toUpperCase());
                if (Objects.isNull(logicOperator)) return null;
                switch (logicOperator) {
                    case AND:
                        filterBuilder.must(ruleBuilder);
                        break;
                    case OR:
                        filterBuilder.should(ruleBuilder);
                }
            }
        }
        return filterBuilder;
    }

    private static BoolQueryBuilder buildRule(Map<String, Object> rule) {
        if (Objects.nonNull(rule) && rule.containsKey(ES_LOGIC_OPERATOR) && rule.containsKey(ES_RULES)) {
            return buildFilterTree(rule);
        } else if (rule.containsKey(ES_OPERATOR)) {
            String operator = rule.get(ES_OPERATOR).toString();
            String field = rule.get(ES_FIELD).toString();
            Object value = rule.get(ES_VALUE);
            BoolQueryBuilder filterBuilder = QueryBuilders.boolQuery();
            QueryBuilder queryBuilder = QueryBuilders.boolQuery();
            filterBuilder.must(queryBuilder);

            GOperator gOperator = GOperator.byValue(operator.toUpperCase());
            if (Objects.isNull(gOperator)) return filterBuilder;
            QueryBuilder childQueryBuilder;
            switch (gOperator) {
                case EQ:
                    childQueryBuilder = QueryBuilders.termQuery(wrapField(field),value);
                    filterBuilder.must(childQueryBuilder);
                    break;
                case NOT_EQ:
                    childQueryBuilder = QueryBuilders.termQuery(wrapField(field),value);
                    filterBuilder.mustNot(childQueryBuilder);
                    break;
                case GT:
                    childQueryBuilder = QueryBuilders.rangeQuery(field).gt(value);
                    filterBuilder.must(childQueryBuilder);
                    break;
                case GTE:
                    childQueryBuilder = QueryBuilders.rangeQuery(field).gte(value);
                    filterBuilder.must(childQueryBuilder);
                    break;
                case LT:
                    childQueryBuilder = QueryBuilders.rangeQuery(field).lt(value);
                    filterBuilder.must(childQueryBuilder);
                    break;
                case LTE:
                    childQueryBuilder = QueryBuilders.rangeQuery(field).lte(value);
                    filterBuilder.must(childQueryBuilder);
                    break;
                case IN:
                    List<String> termsObject = (List<String>)value;
                    childQueryBuilder = QueryBuilders.termsQuery(wrapField(field), termsObject);
                    filterBuilder.must(childQueryBuilder);
                    break;
                case NOT_IN:
                    childQueryBuilder = QueryBuilders.termsQuery(wrapField(field),value);
                    filterBuilder.mustNot(childQueryBuilder);
                    break;
                case IS_NULL:
                    break;
                case IS_NOT_NULL:
                    break;
                case RANGE:
                    Map<String,Object> rangeObject = (Map<String,Object>)value;
                    childQueryBuilder = QueryBuilders.rangeQuery(field)
                            .from(rangeObject.get(ES_FROM))
                            .includeLower((boolean)rangeObject.get(ES_INCLUDE_LOWER))
                            .to(rangeObject.get(ES_TO))
                            .includeUpper((boolean)rangeObject.get(ES_INCLUDE_UPPER));
                    filterBuilder.must(childQueryBuilder);
                    break;
                case NOT_RANGE:
                    Map<String,Object> notRangeObject = (Map<String,Object>)value;
                    childQueryBuilder = QueryBuilders.rangeQuery(field)
                            .from(notRangeObject.get(ES_FROM))
                            .includeLower((boolean)notRangeObject.get(ES_INCLUDE_LOWER))
                            .to(notRangeObject.get("to"))
                            .includeUpper((boolean)notRangeObject.get(ES_INCLUDE_UPPER));
                    filterBuilder.mustNot(childQueryBuilder);
                    break;
                case MATCH:
                    childQueryBuilder = QueryBuilders.matchQuery(value.toString(),field);
                    filterBuilder.must(childQueryBuilder);
                    break;
            }
            return filterBuilder;
        } else {
            return null;
        }
    }

    private static String wrapField(String field){
        return field + ES_DOTA_KEYWORD;
    }

}
