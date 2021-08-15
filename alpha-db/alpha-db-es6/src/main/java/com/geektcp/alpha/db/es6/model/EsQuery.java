package com.geektcp.alpha.db.es6.model;

import lombok.Data;

import java.util.*;

/**
 * @author tanghaiyang on 2019/4/30.
 */
@Data
public class EsQuery {
    private String graph;
    private String keyword;
    private int pageNo = 1;
    private int pageSize = 10;
    private Set<String> types;
    private Map<String, Object> schemas;
    private Map<String, Object> option;
    private List<Map<String, Object>> query;
    private Map<String, Object> filter;
    private List<Map<String, Object>> sort;
    private List<Map<String, Object>> aggregation;
    private Set<String> fields;
    private int timeout = 15;

    /* log */
    private boolean debugEnabled;
    /* highlight */
    private boolean highlight = false;
    public static String HIGHLIGHTER_PRE_TAGS = "<em>";
    public static String HIGHLIGHTER_POST_TAGS = "</em>";
    private String highlighterPreTags = HIGHLIGHTER_PRE_TAGS;
    private String highlighterPostTags = HIGHLIGHTER_POST_TAGS;
    private Set<String> excludedHLFields = new HashSet<>(Arrays.asList(""));
}
