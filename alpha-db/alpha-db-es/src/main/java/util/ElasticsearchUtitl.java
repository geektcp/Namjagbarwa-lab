package util;

/* Created by Haiyang on 2017/9/2. */


import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpRequestRetryHandler;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpEntityEnclosingRequestBase;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpRequestRetryHandler;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

@Slf4j
public class ElasticsearchUtitl {
//    private static String url_pre = "http://api.geektcp.com:19201";
    private static String url_pre = "http://98.11.13.152:19200";
    private static String index = "enterprise_data_gov_lanzhou";

    public void EleasearchUtitl(){
        init();
    }

    public static void init(){
//        url_pre = Config.get("es.host");
//        index = Config.get("es.index");
    }

    public static String createIndex(String index) throws Exception {
//        curl -XPUT 'http://localhost:9200/kk_labels/'
        String url = url_pre + "/" + index + "/";
        String ret = HttpUtil.doPost(url, "");
        log.info("createIndex: " + index );
        return ret;
    }

    public static String queryString(String type, String keyword){
        JSONObject esPara = new JSONObject();
        JSONObject query = new JSONObject();
        JSONObject condition =  new JSONObject();

        condition.put("query", keyword);
        query.put("query_string", condition);
        esPara.put("query", query);

        String ret = searchES(type, esPara);
        return ret;
    }

    public static String querySingle(String type, String key, String value){
        JSONObject esPara = new JSONObject();
        JSONObject query = new JSONObject();
        JSONObject match =  new JSONObject();
        match.put(key, value);

        esPara.put("match", match);

        String ret = searchES(type, esPara);
        return ret;
    }

    public static String queryTerm(String type, String key, String value){
//        {
//            "query": {
//            "term": {
//                "unified_social_credit_code": "91621222063918089J"
//            }
//        },
//            "_source": ["company",
//                "unified_social_credit_code",
//                "enterprise_type"]
//        }

        JSONObject esPara = new JSONObject();
        JSONObject query = new JSONObject();
        JSONObject term =  new JSONObject();
        JSONArray _source =  new JSONArray();

        term.put(key, value);
        query.put("term", term);

        _source.add("company");
        _source.add("unified_social_credit_code");

        esPara.put("query", query);
        esPara.put("_source", _source);

        String ret = searchES(type, esPara);
        return ret;
    }

    public static String queryBool(String value1, String value2){
//        {
//            "query": {
//            "bool": {
//                "must": [
//                {"term": {"unified_social_credit_code": "91621222063918089J" }},
//                {"term": {"company.raw": "甘肃天元药业有限公司文县分公司" }}
//                ]
//            }
//        },
//            "_source": ["company", "unified_social_credit_code"]
//        }

        JSONObject esPara = new JSONObject();
        JSONObject query = new JSONObject();
        JSONObject bool = new JSONObject();
        JSONObject must = new JSONObject();
        JSONObject term1 =  new JSONObject();
        JSONObject term2 =  new JSONObject();
        JSONArray _source =  new JSONArray();

        term1.put("unified_social_credit_code", value1);
        term2.put("company.raw", value2);
        query.put("bool", bool);

        bool.put("must", must);
        must.put("term", term1);
        must.put("term", term2);

        _source.add("company");
        _source.add("unified_social_credit_code");

        esPara.put("query", query);
        esPara.put("_source", _source);

        String ret = searchES("enterprise_data_gov", esPara);
        return ret;
    }

    public static String queryMulti(String type, String key, String value){
        JSONObject esPara = new JSONObject();
        JSONObject query = new JSONObject();
        JSONObject multi_match =  new JSONObject();

        JSONArray jsonArray = new JSONArray();
        jsonArray.add("k1");
        jsonArray.add("k2");
        multi_match.put("query", value);
        multi_match.put("fields", jsonArray);
        multi_match.put("fuzziness", "AUTO");

        query.put("multi_match", multi_match);
        esPara.put("query", query);

        String ret = searchES(type, esPara);
        return ret;
    }


    public static void insert(String type, String key, String value) throws Exception{
        JSONObject insert = new JSONObject();
        insert.put(key, value);
        String esUrl = url_pre + "/" + index + "/" + type + "?pretty";
        log.info("esUrl: " + esUrl);
        restES(esUrl, "post", insert);
    }

    public static String searchES(String type, JSONObject esPara) {
        String ret = "";
        String esUrl = url_pre + "/" + index + "/" + type + "/_search?pretty";
//        Log.logger.info("esUrl: " + esUrl);
        String method = "post";
        try {
            ret =  restES(esUrl, method, esPara);
        }catch (Exception e){ e.printStackTrace(); };
        return ret;
    }

    public static String restES(String esUrl, String method, JSONObject esPara) throws Exception{
        HttpClientBuilder hcb = HttpClientBuilder.create();
        HttpRequestRetryHandler hrrh = new DefaultHttpRequestRetryHandler();
        HttpClientBuilder httpClientBuilder = hcb.setRetryHandler(hrrh);

        CloseableHttpClient client = httpClientBuilder.build();

        HttpEntityEnclosingRequestBase http = null;
        switch (method){
            case "post":
                http = new HttpPost(esUrl);
                break;
            case "put":
                http = new HttpPut(esUrl);
                break;
            default:
                log.info("have no method");
        }

        http.addHeader("User-Agent","Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
        http.addHeader(HTTP.CONTENT_TYPE, "application/json");
        RequestConfig.Builder confBuilder = RequestConfig.custom();
        confBuilder.setConnectTimeout(120*1000);
        confBuilder.setConnectionRequestTimeout(60*1000);
        confBuilder.setSocketTimeout(60*1000);
        RequestConfig config = confBuilder.build();
        http.setConfig(config);
        CloseableHttpResponse response = null;
        String result = "";

        if(esPara != null && esPara.size() != 0) {
            String condition = esPara.toJSONString();
            log.info("负载数据: " + condition);
            StringEntity entity = new StringEntity(condition, "utf-8");

            // 将post内容装载到post中
            http.setEntity(entity);
        }

        response = client.execute(http);
        int code = response.getStatusLine().getStatusCode();
        if(code == HttpStatus.SC_OK) {
            result = EntityUtils.toString(response.getEntity());
        }

        response.close();
        client.close();

//        System.out.println("result: " + result);
        return result;
    }

}
