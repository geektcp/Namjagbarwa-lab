package com.geektcp.alpha.db.es6.bean.mapping;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;

import java.io.IOException;

/**
 * Created by nagle on 2017/12/01.
 */
@Slf4j
public class Template {


    public static XContentBuilder getSettings() {
        XContentBuilder xb = null;
        try {
            xb = XContentFactory.jsonBuilder()
            .startObject()
                .field("index.number_of_shards", 5)      //default 5
                .field("index.number_of_replicas", 1)    //default 1
                .startObject("analysis")
                	.startObject("analyzer")
		                .startObject(Analyzers.IK.code())
		                    .field("type", "custom")
		                    .field("tokenizer", "normalizer")
		                .endObject()
		            .endObject()
		            .startObject("normalizer")
			            .startObject("my_normalizer")
		                    .field("type", "custom")
		                    .array("char_filter")
		                    .array("filter","lowercase", "asciifolding")
		                .endObject()
		            .endObject()
                    .startObject("tokenizer")
                    	.startObject("normalizer")
                    		.field("type", "ngram")
                    		.field("min_gram", 2)
                    		.field("max_gram", 2)
                    		.field("token_chars", "letter, digit")
                    		.endObject()
                    .endObject()
                .endObject()
            .endObject();
        } catch (IOException e) {
            System.err.println(e);
        }
        return xb;
    }
    public static XContentBuilder getTypeMapping() {
        XContentBuilder xb = null;
        try {
            xb = XContentFactory.jsonBuilder()
            .startObject()
                .startObject("_all")
                    .field("enabled", false)
                .endObject()
                    .field("date_detection", true)
                    .array("dynamic_date_formats", "yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd")
                .startArray("dynamic_templates")
                    .startObject()
                        .startObject("strings")
                            .field("match_mapping_type", "string")
                            .startObject("mapping")
                                .field("type", "text")
                                .field("analyzer", Analyzers.IK.code())
                                .startObject("fields")
                                    .startObject("keyword")
                                        .field("type", "keyword")
                                        .field("normalizer", "my_normalizer")
                                    .endObject()
                                .endObject()
                            .endObject()
                        .endObject()
                    .endObject()
                .endArray()
            .endObject();
        } catch (IOException e) {
            log.error(e.getMessage());
        }
        return xb;
    }

    public static void main(String[] args) throws Exception{
        // settings
        String json = Template.getSettings().string();
        System.out.println(JSON.toJSONString(JSON.parse(json), true));

        // type mapping
        json = Template.getTypeMapping().string();
        System.out.println(JSON.toJSONString(JSON.parse(json), true));
    }
}
