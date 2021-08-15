import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import io.restassured.response.Response;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.client.utils.URIBuilder;
import org.junit.Test;
import org.springframework.web.client.RestTemplate;
import util.HttpUtil;

import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

/**
 * @author tanghaiyang on 2019/3/18.
 */
@Slf4j
public class AssuredTest {

    public static final String url = "http://192.168.1.101:9000/query/work_graph/graphKExpand?gdbQuery=%7B%22debug%22:true,%22direction%22:%22ANY%22,%22edgeTables%22:%5B%22all_to_skill%22,%22company_person%22%5D,%22endVertices%22:%5B%7B%22objectKey%22:%22p2%22,%22schema%22:%22persons%22%7D,%7B%22objectKey%22:%22222222222222222222%22,%22schema%22:%22company%22%7D%5D,%22filterExpression%22:%22((t.type==%5C%22company%5C%22%20AND%20(t.city==%5C%22sayan%5C%22%20OR%20t.city==%5C%22dongguan%5C%22))%20OR%20(e.type==%5C%22all_to_skill%5C%22%20AND%20((e.quadrant%3E1%20AND%20e.quadrant%3C=800)%20OR%20(e.quadrant%3E=1000%20AND%20e.quadrant%3C=3000)))%20OR%20((t.type==%5C%22persons%5C%22%20AND%20(t.quadrant==%5C%224%5C%22%20OR%20t.quadrant==%5C%225%5C%22))%20AND%20(t.type==%5C%22persons%5C%22%20AND%20(t.quadrant==%5C%2210%5C%22%20OR%20t.quadrant==%5C%2220%5C%22))))%22,%22graph%22:%22work_graph%22,%22internalOption%22:%7B%22debug%22:true%7D,%22maxDepth%22:10,%22maxSize%22:100,%22offset%22:44,%22size%22:100,%22startVertices%22:%5B%7B%22objectKey%22:%22c4%22,%22schema%22:%22company%22%7D,%7B%22objectKey%22:%22p3%22,%22schema%22:%22persons%22%7D,%7B%22objectKey%22:%22c1%22,%22schema%22:%22company%22%7D%5D,%22type%22:%22K_EXPAND%22,%22vertexTables%22:%5B%22persons%22,%22skill%22,%22company%22%5D%7D";
//    public static final String url = "http://192.168.1.234:9000/query/graph_hello/graphKExpand?gdbQuery=%7B%22debug%22:true,%22direction%22:%22ANY%22,%22edgeTables%22:%5B%22all_to_skill%22,%22company_person%22%5D,%22endVertices%22:%5B%7B%22objectKey%22:%22p2%22,%22schema%22:%22persons%22%7D,%7B%22objectKey%22:%22222222222222222222%22,%22schema%22:%22company%22%7D%5D,%22filterExpression%22:%22((t.type==%5C%22company%5C%22%20AND%20(t.city==%5C%22sayan%5C%22%20OR%20t.city==%5C%22dongguan%5C%22))%20OR%20(e.type==%5C%22all_to_skill%5C%22%20AND%20((e.quadrant%3E1%20AND%20e.quadrant%3C=800)%20OR%20(e.quadrant%3E=1000%20AND%20e.quadrant%3C=3000)))%20OR%20((t.type==%5C%22persons%5C%22%20AND%20(t.quadrant==%5C%224%5C%22%20OR%20t.quadrant==%5C%225%5C%22))%20AND%20(t.type==%5C%22persons%5C%22%20AND%20(t.quadrant==%5C%2210%5C%22%20OR%20t.quadrant==%5C%2220%5C%22))))%22,%22graph%22:%22work_graph%22,%22internalOption%22:%7B%22debug%22:true%7D,%22maxDepth%22:10,%22maxSize%22:100,%22offset%22:44,%22size%22:100,%22startVertices%22:%5B%7B%22objectKey%22:%22c4%22,%22schema%22:%22company%22%7D,%7B%22objectKey%22:%22p3%22,%22schema%22:%22persons%22%7D,%7B%22objectKey%22:%22c1%22,%22schema%22:%22company%22%7D%5D,%22type%22:%22K_EXPAND%22,%22vertexTables%22:%5B%22persons%22,%22skill%22,%22company%22%5D%7D";
//    public static final String url = "http://192.168.1.234:9000/query/graph_hello/graphKExpand?gdbQuery=%7B%22debug%22:true,%22direction%22:%22ANY%22,%22edgeTables%22:%5B%22all_to_skill%22,%22company_person%22%5D,%22endVertices%22:%5B%7B%22objectKey%22:%22p2%22,%22schema%22:%22persons%22%7D,%7B%22objectKey%22:%22222222222222222222%22,%22schema%22:%22company%22%7D%5D,%22filterExpression%22:%22((t.type==%5C%22company%5C%22%20AND%20(t.city==%5C%22sayan%5C%22%20OR%20t.city==%5C%22dongguan%5C%22))%20OR%20(e.type==%5C%22all_to_skill%5C%22%20AND%20((e.quadrant%3E1%20AND%20e.quadrant%3C=800)%20OR%20(e.quadrant%3E=1000%20AND%20e.quadrant%3C=3000)))%20OR%20((t.type==%5C%22persons%5C%22%20AND%20(t.quadrant==%5C%224%5C%22%20OR%20t.quadrant==%5C%225%5C%22))%20AND%20(t.type==%5C%22persons%5C%22%20AND%20(t.quadrant==%5C%2210%5C%22+OR+t.quadrant==%5C%2220%5C%22))))%22,%22graph%22:%22work_graph%22,%22internalOption%22:%7B%22debug%22:true%7D,%22maxDepth%22:10,%22maxSize%22:100,%22offset%22:44,%22size%22:100,%22startVertices%22:%5B%7B%22objectKey%22:%22c4%22,%22schema%22:%22company%22%7D,%7B%22objectKey%22:%22p3%22,%22schema%22:%22persons%22%7D,%7B%22objectKey%22:%22c1%22,%22schema%22:%22company%22%7D%5D,%22type%22:%22K_EXPAND%22,%22vertexTables%22:%5B%22persons%22,%22skill%22,%22company%22%5D%7D";



    @Test
    public void getHttpTest() {
        JSONObject vertex = new JSONObject();
        vertex.put("objectKey", "c3");
        vertex.put("schema", "company");

//        Response response = given().get("http://www.jianshu.com/users/recommended?seen_ids=&count=5&only_unfollowed=true");
        Response response = given()
                .param("data_str", vertex.toJSONString())
                .get("http://192.168.1.101:9000/query/exist");

        System.out.println("===================");
//        System.out.println(response.toString());

//        ResponseBody responseBody = response.prettyPeek();
        response.prettyPrint();
    }

    @Test
    public void getHttpTest2() {
        Response response = given()
                .param("objectKey", "c3")
                .param("schema", "company")
                .get("http://192.168.1.101:9000/query/exist");

        System.out.println("|||||||||||||||");
//        System.out.println(response.toString());

//        ResponseBody responseBody = response.prettyPeek();
        response.prettyPrint();
    }

    @Test
    public void getHttpTest3() {
        String url = "http://192.168.1.101:9000/query/work_graph/graphKExpand";
        String para = "%7B%22debug%22%3Atrue%2C%22direction%22%3A%22ANY%22%2C%22edgeTables%22%3A%5B%22all_to_skill%22%2C%22company_person%22%5D%2C%22endVertices%22%3A%5B%7B%22objectKey%22%3A%22p2%22%2C%22schema%22%3A%22persons%22%7D%2C%7B%22objectKey%22%3A%22222222222222222222%22%2C%22schema%22%3A%22company%22%7D%5D%2C%22filterExpression%22%3A%22%28%28t.type%3D%3D%5C%22company%5C%22+AND+%28t.city%3D%3D%5C%22sayan%5C%22+OR+t.city%3D%3D%5C%22dongguan%5C%22%29%29+OR+%28e.type%3D%3D%5C%22all_to_skill%5C%22+AND+%28%28e.quadrant%3E1+AND+e.quadrant%3C%3D800%29+OR+%28e.quadrant%3E%3D1000+AND+e.quadrant%3C%3D3000%29%29%29+OR+%28%28t.type%3D%3D%5C%22persons%5C%22+AND+%28t.quadrant%3D%3D%5C%224%5C%22+OR+t.quadrant%3D%3D%5C%225%5C%22%29%29+AND+%28t.type%3D%3D%5C%22persons%5C%22+AND+%28t.quadrant%3D%3D%5C%2210%5C%22+OR+t.quadrant%3D%3D%5C%2220%5C%22%29%29%29%29%22%2C%22graph%22%3A%22work_graph%22%2C%22internalOption%22%3A%7B%22debug%22%3Atrue%7D%2C%22maxDepth%22%3A10%2C%22maxSize%22%3A100%2C%22offset%22%3A44%2C%22size%22%3A100%2C%22startVertices%22%3A%5B%7B%22objectKey%22%3A%22c4%22%2C%22schema%22%3A%22company%22%7D%2C%7B%22objectKey%22%3A%22p3%22%2C%22schema%22%3A%22persons%22%7D%2C%7B%22objectKey%22%3A%22c1%22%2C%22schema%22%3A%22company%22%7D%5D%2C%22type%22%3A%22K_EXPAND%22%2C%22vertexTables%22%3A%5B%22persons%22%2C%22skill%22%2C%22company%22%5D%7D";

        Response response = given()
                .param("gdbQuery", para)
                .get(url);

        System.out.println("|||||||||||||||");
//        System.out.println(response.toString());

//        ResponseBody responseBody = response.prettyPeek();
        response.prettyPrint();
    }

    /*
    * http://192.168.1.101:9000/query/work_graph/graphKExpand?gdbQuery=%7B%22debug%22:true,%22direction%22:%22ANY%22,%22edgeTables%22:%5B%22all_to_skill%22,%22company_person%22%5D,%22endVertices%22:%5B%7B%22objectKey%22:%22p2%22,%22schema%22:%22persons%22%7D,%7B%22objectKey%22:%22222222222222222222%22,%22schema%22:%22company%22%7D%5D,%22filter%22:%7B%22logicOperators%22:%5B%22OR%22,%22OR%22%5D,%22rules%22:%5B%7B%22schema%22:%22company%22,%22field%22:%22city%22,%22values%22:%5B%22sayan%22,%22dongguan%22%5D,%22schemaType%22:%22vertex%22,%22type%22:%22term%22%7D,%7B%22schema%22:%22all_to_skill%22,%22field%22:%22quadrant%22,%22values%22:%5B%7B%22includeUpper%22:true,%22includeLower%22:false,%22from%22:1,%22to%22:800%7D,%7B%22includeUpper%22:true,%22includeLower%22:true,%22from%22:1000,%22to%22:3000%7D%5D,%22schemaType%22:%22edge%22,%22type%22:%22range%22%7D,%7B%22logicOperators%22:%5B%22AND%22,%22OR%22%5D,%22rules%22:%5B%7B%22schema%22:%22persons%22,%22field%22:%22quadrant%22,%22values%22:%5B4,5%5D,%22schemaType%22:%22vertex%22,%22type%22:%22term%22%7D,%7B%22schema%22:%22persons%22,%22field%22:%22quadrant%22,%22values%22:%5B10,20%5D,%22schemaType%22:%22vertex%22,%22type%22:%22term%22%7D%5D%7D%5D%7D,%22filterExpression%22:%22((t.type==%5C%22company%5C%22%20AND%20(t.city==%5C%22sayan%5C%22%20OR%20t.city==%5C%22dongguan%5C%22))%20OR%20(e.type==%5C%22all_to_skill%5C%22%20AND%20((e.quadrant%3E1%20AND%20e.quadrant%3C=800)%20OR%20(e.quadrant%3E=1000%20AND%20e.quadrant%3C=3000)))%20OR%20((t.type==%5C%22persons%5C%22%20AND%20(t.quadrant==%5C%224%5C%22%20OR%20t.quadrant==%5C%225%5C%22))%20AND%20(t.type==%5C%22persons%5C%22%20AND%20(t.quadrant==%5C%2210%5C%22%20OR%20t.quadrant==%5C%2220%5C%22))))%22,%22graph%22:%22work_graph%22,%22internalOption%22:%7B%22debug%22:true%7D,%22maxDepth%22:10,%22maxSize%22:100,%22offset%22:44,%22size%22:100,%22startVertices%22:%5B%7B%22objectKey%22:%22c4%22,%22schema%22:%22company%22%7D,%7B%22objectKey%22:%22p3%22,%22schema%22:%22persons%22%7D,%7B%22objectKey%22:%22c1%22,%22schema%22:%22company%22%7D%5D,%22type%22:%22K_EXPAND%22,%22vertexTables%22:%5B%22persons%22,%22skill%22,%22company%22%5D%7D
    *
    * */
    @Test
    public void getTest() throws Exception{
        String raw = "http://192.168.1.101:9000/query/work_graph/graphKExpand?gdbQuery=%7B%22debug%22:true,%22direction%22:%22ANY%22,%22edgeTables%22:%5B%22all_to_skill%22,%22company_person%22%5D,%22endVertices%22:%5B%7B%22objectKey%22:%22p2%22,%22schema%22:%22persons%22%7D,%7B%22objectKey%22:%22222222222222222222%22,%22schema%22:%22company%22%7D%5D,%22filter%22:%7B%22logicOperators%22:%5B%22OR%22,%22OR%22%5D,%22rules%22:%5B%7B%22schema%22:%22company%22,%22field%22:%22city%22,%22values%22:%5B%22sayan%22,%22dongguan%22%5D,%22schemaType%22:%22vertex%22,%22type%22:%22term%22%7D,%7B%22schema%22:%22all_to_skill%22,%22field%22:%22quadrant%22,%22values%22:%5B%7B%22includeUpper%22:true,%22includeLower%22:false,%22from%22:1,%22to%22:800%7D,%7B%22includeUpper%22:true,%22includeLower%22:true,%22from%22:1000,%22to%22:3000%7D%5D,%22schemaType%22:%22edge%22,%22type%22:%22range%22%7D,%7B%22logicOperators%22:%5B%22AND%22,%22OR%22%5D,%22rules%22:%5B%7B%22schema%22:%22persons%22,%22field%22:%22quadrant%22,%22values%22:%5B4,5%5D,%22schemaType%22:%22vertex%22,%22type%22:%22term%22%7D,%7B%22schema%22:%22persons%22,%22field%22:%22quadrant%22,%22values%22:%5B10,20%5D,%22schemaType%22:%22vertex%22,%22type%22:%22term%22%7D%5D%7D%5D%7D,%22filterExpression%22:%22((t.type==%5C%22company%5C%22%20AND%20(t.city==%5C%22sayan%5C%22%20OR%20t.city==%5C%22dongguan%5C%22))%20OR%20(e.type==%5C%22all_to_skill%5C%22%20AND%20((e.quadrant%3E1%20AND%20e.quadrant%3C=800)%20OR%20(e.quadrant%3E=1000%20AND%20e.quadrant%3C=3000)))%20OR%20((t.type==%5C%22persons%5C%22%20AND%20(t.quadrant==%5C%224%5C%22%20OR%20t.quadrant==%5C%225%5C%22))%20AND%20(t.type==%5C%22persons%5C%22%20AND%20(t.quadrant==%5C%2210%5C%22%20OR%20t.quadrant==%5C%2220%5C%22))))%22,%22graph%22:%22work_graph%22,%22internalOption%22:%7B%22debug%22:true%7D,%22maxDepth%22:10,%22maxSize%22:100,%22offset%22:44,%22size%22:100,%22startVertices%22:%5B%7B%22objectKey%22:%22c4%22,%22schema%22:%22company%22%7D,%7B%22objectKey%22:%22p3%22,%22schema%22:%22persons%22%7D,%7B%22objectKey%22:%22c1%22,%22schema%22:%22company%22%7D%5D,%22type%22:%22K_EXPAND%22,%22vertexTables%22:%5B%22persons%22,%22skill%22,%22company%22%5D%7D";
        String queryUrl = "http://192.168.1.101:9000/query";
        String database = "work_graph";
        String queryName = "graphKExpand";
//        String gdbQuery = "{\"debug\":true,\"direction\":\"ANY\",\"edgeTables\":[\"all_to_skill\",\"company_person\"],\"endVertices\":[{\"objectKey\":\"p2\",\"schema\":\"persons\"},{\"objectKey\":\"222222222222222222\",\"schema\":\"company\"}],\"filterExpression\":\"((t.type==\\\"company\\\" AND (t.city==\\\"sayan\\\" OR t.city==\\\"dongguan\\\")) OR (e.type==\\\"all_to_skill\\\" AND ((e.quadrant>1 AND e.quadrant<=800) OR (e.quadrant>=1000 AND e.quadrant<=3000))) OR ((t.type==\\\"persons\\\" AND (t.quadrant==\\\"4\\\" OR t.quadrant==\\\"5\\\")) AND (t.type==\\\"persons\\\" AND (t.quadrant==\\\"10\\\" OR t.quadrant==\\\"20\\\"))))\",\"graph\":\"work_graph\",\"internalOption\":{\"debug\":true},\"maxDepth\":10,\"maxSize\":100,\"offset\":44,\"size\":100,\"startVertices\":[{\"objectKey\":\"c4\",\"schema\":\"company\"},{\"objectKey\":\"p3\",\"schema\":\"persons\"},{\"objectKey\":\"c1\",\"schema\":\"company\"}],\"type\":\"K_EXPAND\",\"vertexTables\":[\"persons\",\"skill\",\"company\"]}";
//        String gdbQuery = "{\"debug\":true,\"direction\":\"ANY\",\"edgeTables\":[\"all_to_skill\",\"company_person\"],\"endVertices\":[{\"objectKey\":\"p2\",\"schema\":\"persons\"},{\"objectKey\":\"222222222222222222\",\"schema\":\"company\"}],\"filterExpression\":\"((t.type==\\\"company\\\" AND (t.city==\\\"sayan\\\" OR t.city==\\\"dongguan\\\")) OR (e.type==\\\"all_to_skill\\\" AND ((e.quadrant>1 AND e.quadrant<=800) OR (e.quadrant>=1000 AND e.quadrant<=3000))) OR ((t.type==\\\"persons\\\" AND (t.quadrant==\\\"4\\\" OR t.quadrant==\\\"5\\\")) AND (t.type==\\\"persons\\\" AND (t.quadrant==\\\"10\\\" OR t.quadrant==\\\"20\\\"))))\",\"graph\":\"work_graph\",\"internalOption\":{\"debug\":true},\"maxDepth\":10,\"maxSize\":100,\"offset\":44,\"size\":100,\"startVertices\":[{\"objectKey\":\"c4\",\"schema\":\"company\"},{\"objectKey\":\"p3\",\"schema\":\"persons\"},{\"objectKey\":\"c1\",\"schema\":\"company\"}],\"type\":\"K_EXPAND\",\"vertexTables\":[\"persons\",\"skill\",\"company\"]}";
//        String gdbQuery = "{\"debug\":true,\"direction\":\"ANY\",\"edgeTables\":[\"all_to_skill\",\"company_person\"],\"endVertices\":[{\"objectKey\":\"p2\",\"schema\":\"persons\"},{\"objectKey\":\"222222222222222222\",\"schema\":\"company\"}],\"filterExpression\":\"((t.type==\\\"company\\\" AND (t.city==\\\"sayan\\\" OR t.city==\\\"dongguan\\\")) OR (e.type==\\\"all_to_skill\\\" AND ((e.quadrant>1 AND e.quadrant<=800) OR (e.quadrant>=1000 AND e.quadrant<=3000))) OR ((t.type==\\\"persons\\\" AND (t.quadrant==\\\"4\\\" OR t.quadrant==\\\"5\\\")) AND (t.type==\\\"persons\\\" AND (t.quadrant==\\\"10\\\" OR t.quadrant==\\\"20\\\"))))\",\"graph\":\"work_graph\",\"internalOption\":{\"debug\":true},\"maxDepth\":10,\"maxSize\":100,\"offset\":44,\"size\":100,\"startVertices\":[{\"objectKey\":\"c4\",\"schema\":\"company\"},{\"objectKey\":\"p3\",\"schema\":\"persons\"},{\"objectKey\":\"c1\",\"schema\":\"company\"}],\"type\":\"K_EXPAND\",\"vertexTables\":[\"persons\",\"skill\",\"company\"]}";
//        String gdbQuery = "{\"debug\":true,\"direction\":\"ANY\",\"edgeTables\":[\"all_to_skill\",\"company_person\"],\"endVertices\":[{\"objectKey\":\"p2\",\"schema\":\"persons\"},{\"objectKey\":\"222222222222222222\",\"schema\":\"company\"}],\"graph\":\"work_graph\",\"internalOption\":{\"debug\":true},\"maxDepth\":10,\"maxSize\":100,\"offset\":44,\"size\":100,\"startVertices\":[{\"objectKey\":\"c4\",\"schema\":\"company\"},{\"objectKey\":\"p3\",\"schema\":\"persons\"},{\"objectKey\":\"c1\",\"schema\":\"company\"}],\"type\":\"K_EXPAND\",\"vertexTables\":[\"persons\",\"skill\",\"company\"]}";
        String gdbQuery = "{\"debug\":true}";

        JSONObject jsonObject = JSONObject.parseObject(gdbQuery);
//        String gdbQueryEncode = URLEncoder.encode(gdbQuery, "UTF-8");
//        String gdbQueryEncode = URLEncoder.encode(gdbQuery, "ISO-8859-1");
        String gdbQueryEncode = URLEncoder.encode(gdbQuery,   StandardCharsets.UTF_8.toString());

        String gdbQueryDecode = URLDecoder.decode(gdbQueryEncode,   StandardCharsets.UTF_8.toString());

//        log.info(searchUrlEncode);
        String baseUrl = queryUrl + "/" + database + "/" + queryName;
        String searchUrl = baseUrl + "?gdbQuery=" + gdbQuery;

        log.info("baseUrl: \n{}", baseUrl);
        log.info("gdbQueryEncode: \n{}", gdbQueryEncode);
        log.info("gdbQueryDecode: \n{}", gdbQueryDecode);
        log.info("searchUrl: \n{}",searchUrl);

//        Response response = given().get(searchUrl);
//        log.info("response: {}", response.prettyPrint());

        log.info("===================================");
        String searchUrlEncode = baseUrl + "?gdbQuery=" + gdbQueryEncode;
        Response responseEncode = given().get(searchUrlEncode);
        log.info("responseEncode: {}", responseEncode.prettyPrint());

    }


    @Test
    public void test3(){
        log.info("test3=============");
        String url = "http://192.168.1.101:9000/query/work_graph/graphKExpand?gdbQuery=%7B%22debug%22:true,%22direction%22:%22ANY%22,%22edgeTables%22:%5B%22all_to_skill%22,%22company_person%22%5D,%22endVertices%22:%5B%7B%22objectKey%22:%22p2%22,%22schema%22:%22persons%22%7D,%7B%22objectKey%22:%22222222222222222222%22,%22schema%22:%22company%22%7D%5D,%22filterExpression%22:%22((t.type==%5C%22company%5C%22%20AND%20(t.city==%5C%22sayan%5C%22%20OR%20t.city==%5C%22dongguan%5C%22))%20OR%20(e.type==%5C%22all_to_skill%5C%22%20AND%20((e.quadrant%3E1%20AND%20e.quadrant%3C=800)%20OR%20(e.quadrant%3E=1000%20AND%20e.quadrant%3C=3000)))%20OR%20((t.type==%5C%22persons%5C%22%20AND%20(t.quadrant==%5C%224%5C%22%20OR%20t.quadrant==%5C%225%5C%22))%20AND%20(t.type==%5C%22persons%5C%22%20AND%20(t.quadrant==%5C%2210%5C%22%20OR%20t.quadrant==%5C%2220%5C%22))))%22,%22graph%22:%22work_graph%22,%22internalOption%22:%7B%22debug%22:true%7D,%22maxDepth%22:10,%22maxSize%22:100,%22offset%22:44,%22size%22:100,%22startVertices%22:%5B%7B%22objectKey%22:%22c4%22,%22schema%22:%22company%22%7D,%7B%22objectKey%22:%22p3%22,%22schema%22:%22persons%22%7D,%7B%22objectKey%22:%22c1%22,%22schema%22:%22company%22%7D%5D,%22type%22:%22K_EXPAND%22,%22vertexTables%22:%5B%22persons%22,%22skill%22,%22company%22%5D%7D";
        given().get(url).prettyPrint();

    }

    @Test
    public void test4(){
        log.info("test4=============");
        String url = "http://192.168.1.101:9000/query/work_graph/graphKExpand?gdbQuery=%7B%22debug%22:true,%22direction%22:%22ANY%22,%22edgeTables%22:%5B%22all_to_skill%22,%22company_person%22%5D,%22endVertices%22:%5B%7B%22objectKey%22:%22p2%22,%22schema%22:%22persons%22%7D,%7B%22objectKey%22:%22222222222222222222%22,%22schema%22:%22company%22%7D%5D,%22filterExpression%22:%22((t.type==%5C%22company%5C%22%20AND%20(t.city==%5C%22sayan%5C%22%20OR%20t.city==%5C%22dongguan%5C%22))%20OR%20(e.type==%5C%22all_to_skill%5C%22%20AND%20((e.quadrant%3E1%20AND%20e.quadrant%3C=800)%20OR%20(e.quadrant%3E=1000%20AND%20e.quadrant%3C=3000)))%20OR%20((t.type==%5C%22persons%5C%22%20AND%20(t.quadrant==%5C%224%5C%22%20OR%20t.quadrant==%5C%225%5C%22))%20AND%20(t.type==%5C%22persons%5C%22%20AND%20(t.quadrant==%5C%2210%5C%22%20OR%20t.quadrant==%5C%2220%5C%22))))%22,%22graph%22:%22work_graph%22,%22internalOption%22:%7B%22debug%22:true%7D,%22maxDepth%22:10,%22maxSize%22:100,%22offset%22:44,%22size%22:100,%22startVertices%22:%5B%7B%22objectKey%22:%22c4%22,%22schema%22:%22company%22%7D,%7B%22objectKey%22:%22p3%22,%22schema%22:%22persons%22%7D,%7B%22objectKey%22:%22c1%22,%22schema%22:%22company%22%7D%5D,%22type%22:%22K_EXPAND%22,%22vertexTables%22:%5B%22persons%22,%22skill%22,%22company%22%5D%7D";
        given().headers("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8")
                .header("User-Agent", "Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/72.0.3626.121 Safari/537.36")
                .get(url)
                .prettyPrint();

    }

    @Test
    public void test5() throws Exception{
        log.info("test5=============");
        String ret = HttpUtil.doGet(url);
        log.info("ret: {}", ret);
    }


    @Test
    public void test6() throws Exception{
        log.info("test6=============");
        given()
//                .headers("Content-Type", "application/json")
               // .header("User-Agent", "Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/72.0.3626.121 Safari/537.36")
                .header("User-Agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)")
//                .header(HTTP.CONTENT_TYPE, "application/json")
//                .header(HTTP.CONTENT_TYPE, "application/x-www-form-urlencoded")
                .get("http://192.168.1.101:9000/query/graph500/test?gdpQuery=aaaa ffff")
                .prettyPrint();

    }

    @Test
    public void test7() throws Exception{
        log.info("test7=============");
        RestTemplate restTemplate = new RestTemplate();
        URIBuilder builder = new URIBuilder(url);
        JSONObject ret = restTemplate.getForObject(builder.build(), JSONObject.class);

        log.info(JSON.toJSONString(ret, SerializerFeature.PrettyFormat));
    }

    @Test
    public void test8() throws Exception{
        log.info("test8=============");
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://192.168.1.101:9000/query/work_graph/graphKExpand";
        URIBuilder builder = new URIBuilder(url);
//        String gdbQuery = "{\"debug\":true,\"direction\":\"ANY\",\"edgeTables\":[\"all_to_skill\",\"company_person\"],\"endVertices\":[{\"objectKey\":\"p2\",\"schema\":\"persons\"},{\"objectKey\":\"222222222222222222\",\"schema\":\"company\"}],\"graph\":\"work_graph\",\"internalOption\":{\"debug\":true},\"maxDepth\":10,\"maxSize\":100,\"offset\":44,\"size\":100,\"startVertices\":[{\"objectKey\":\"c4\",\"schema\":\"company\"},{\"objectKey\":\"p3\",\"schema\":\"persons\"},{\"objectKey\":\"c1\",\"schema\":\"company\"}],\"type\":\"K_EXPAND\",\"vertexTables\":[\"persons\",\"skill\",\"company\"]}";
        String gdbQuery = "((t.type==\"company\" AND (t.city==\"sayan\" OR t.city==\"dongguan\")) OR (e.type==\"all_to_skill\" AND ((e.quadrant>1 AND e.quadrant<=800) OR (e.quadrant>=1000 AND e.quadrant<=3000))) OR ((t.type==\"persons\" AND (t.quadrant==4 OR t.quadrant==5)) AND (t.type==\"persons\" AND (t.quadrant==10 OR t.quadrant==20))))";

        log.info(gdbQuery);
        Map<String, Object> params = new HashMap<>();
//        params.put("gdbQuery", URLEncoder.encode(gdbQuery, "UTF-8"));
        params.put("gdbQuery", gdbQuery);
        for (Map.Entry<String, Object> entry : params.entrySet()) {
//            builder.addParameter(entry.getKey(), Objects.toString(entry.getValue(), ""));
            builder.addParameter(entry.getKey(), URLEncoder.encode(gdbQuery, "UTF-8"));
        }
        JSONObject ret = restTemplate.getForObject(builder.build(), JSONObject.class);

        log.info(JSON.toJSONString(ret, SerializerFeature.PrettyFormat));
    }

//    @Test
//    public void test9(){
//        Map<String, Object> params;
//        if (requestQo instanceof Map) {
//            params = new HashMap<>((Map) requestQo);
//        } else {
//            params = JSONUtils.toMap(requestQo);
//        }
//        return params;
//        Map<String, Object> params = this.getParameters(request);
//        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url);
//        for (Map.Entry<String, Object> entry : params.entrySet()) {
//            builder.queryParam(entry.getKey(), Objects.toString(entry.getValue(), ""));
//        }
//        return restTemplate.getForObject(builder.build(true).toUri(), responseType);
//
//
//    }

    @Test
    public void test10()throws Exception{
        log.info("test10=============");
        String url = "http://localhost:10030/api/url/test?gdbQuery=%7B%22debug%22:true,%22direction%22:%22ANY%22,%22edgeTables%22:%5B%22all_to_skill%22,%22company_person%22%5D,%22endVertices%22:%5B%7B%22objectKey%22:%22p2%22,%22schema%22:%22persons%22%7D,%7B%22objectKey%22:%22222222222222222222%22,%22schema%22:%22company%22%7D%5D,%22filterExpression%22:%22((t.type==%5C%22company%5C%22%20AND%20(t.city==%5C%22sayan%5C%22%20OR%20t.city==%5C%22dongguan%5C%22))%20OR%20(e.type==%5C%22all_to_skill%5C%22%20AND%20((e.quadrant%3E1%20AND%20e.quadrant%3C=800)%20OR%20(e.quadrant%3E=1000%20AND%20e.quadrant%3C=3000)))%20OR%20((t.type==%5C%22persons%5C%22%20AND%20(t.quadrant==%5C%224%5C%22%20OR%20t.quadrant==%5C%225%5C%22))%20AND%20(t.type==%5C%22persons%5C%22%20AND%20(t.quadrant==%5C%2210%5C%22%20OR%20t.quadrant==%5C%2220%5C%22))))%22,%22graph%22:%22work_graph%22,%22internalOption%22:%7B%22debug%22:true%7D,%22maxDepth%22:10,%22maxSize%22:100,%22offset%22:44,%22size%22:100,%22startVertices%22:%5B%7B%22objectKey%22:%22c4%22,%22schema%22:%22company%22%7D,%7B%22objectKey%22:%22p3%22,%22schema%22:%22persons%22%7D,%7B%22objectKey%22:%22c1%22,%22schema%22:%22company%22%7D%5D,%22type%22:%22K_EXPAND%22,%22vertexTables%22:%5B%22persons%22,%22skill%22,%22company%22%5D%7D";

        String ret = HttpUtil.doGet(url);
        log.info("ret: {}", ret);

    }

    @Test
    public void test11()throws Exception{
        log.info("test11=============");
        String url = "http://localhost:10030/api/url/test?gdbQuery=%7B%22debug%22:true,%22direction%22:%22ANY%22,%22edgeTables%22:%5B%22all_to_skill%22,%22company_person%22%5D,%22endVertices%22:%5B%7B%22objectKey%22:%22p2%22,%22schema%22:%22persons%22%7D,%7B%22objectKey%22:%22222222222222222222%22,%22schema%22:%22company%22%7D%5D,%22filterExpression%22:%22((t.type==%5C%22company%5C%22%20AND%20(t.city==%5C%22sayan%5C%22%20OR%20t.city==%5C%22dongguan%5C%22))%20OR%20(e.type==%5C%22all_to_skill%5C%22%20AND%20((e.quadrant%3E1%20AND%20e.quadrant%3C=800)%20OR%20(e.quadrant%3E=1000%20AND%20e.quadrant%3C=3000)))%20OR%20((t.type==%5C%22persons%5C%22%20AND%20(t.quadrant==%5C%224%5C%22%20OR%20t.quadrant==%5C%225%5C%22))%20AND%20(t.type==%5C%22persons%5C%22%20AND%20(t.quadrant==%5C%2210%5C%22%20OR%20t.quadrant==%5C%2220%5C%22))))%22,%22graph%22:%22work_graph%22,%22internalOption%22:%7B%22debug%22:true%7D,%22maxDepth%22:10,%22maxSize%22:100,%22offset%22:44,%22size%22:100,%22startVertices%22:%5B%7B%22objectKey%22:%22c4%22,%22schema%22:%22company%22%7D,%7B%22objectKey%22:%22p3%22,%22schema%22:%22persons%22%7D,%7B%22objectKey%22:%22c1%22,%22schema%22:%22company%22%7D%5D,%22type%22:%22K_EXPAND%22,%22vertexTables%22:%5B%22persons%22,%22skill%22,%22company%22%5D%7D";

        RestTemplate restTemplate = new RestTemplate();
        URIBuilder builder = new URIBuilder(url);
        String ret = restTemplate.getForObject(builder.build(), String.class);

        log.info(ret);

    }

    @Test
    public void test12()throws Exception{
        String url = "http://192.168.1.101:9000/query/graph500/test?gdpQuery=";
        String paraEncode = URLEncoder.encode("aaaa ffff", "UTF-8");
        log.info("urlEncode: {}", paraEncode);
        log.info("url + paraEncode: {}", url + paraEncode);
        String ret = HttpUtil.doGet(url + paraEncode);
        log.info("ret: {}", ret);
    }

    @Test
    public void test13()throws Exception{
//        String url = "http://192.168.1.101:9000/query/graph500/test?gdpQuery=aaaa ffff";
        String ur1 = "http://192.168.1.101:9000/query/graph500/test?gdpQuery=aaaa%20ffff";
        String url2 = "http://192.168.1.101:9000/query/graph500/test?gdpQuery=aaaa%2Bffff";

        String ret = HttpUtil.doGet(url2);
        log.info("ret: {}", ret);
    }

    @Test
    public void test14()throws Exception{
        String url = "ssss|1111";
        String url1 = URLEncoder.encode(url, "UTF-8");
        String url2 = URLEncoder.encode(url1, "UTF-8");
        String url3 = URLEncoder.encode(url2, "UTF-8");
        log.info("url1: {}\n{}\n{}", url, url1, url2,url3);
    }

}
