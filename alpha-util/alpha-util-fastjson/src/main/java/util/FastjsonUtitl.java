package util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.parser.Feature;
import com.alibaba.fastjson.serializer.SerializerFeature;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

/**
 * @author tanghaiyang on 2017/4/8.
 */

@Slf4j
public class FastjsonUtitl {

    public static void main(String[] args) throws Exception{
//        parseJSONArray("data/test.txt");
//        addElement();

        selectElement();

    }

    public static void selectElement(){
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("name", "hais");
        jsonObject.put("age", "23");
        jsonObject.put("id", "1111111");

        for (Map.Entry<String, Object> entry : jsonObject.entrySet()) {
            log.info(entry.getKey() + ":" + entry.getValue());
        }
//        jsonObject.getJSONArray("upstreams").add("ddddddd");

        System.out.println(jsonObject);

    }

    public static void addElement(){
        JSONObject jsonObject = new JSONObject();
        JSONArray jsonArray = new JSONArray();
        jsonArray.add("1111111");
        jsonArray.add("22323");
        jsonArray.add("24rfefef3fe");

        jsonObject.put("arr", jsonArray);

        System.out.println(jsonObject.toJSONString());
        JSONArray arr2 = jsonObject.getJSONArray("arr");
        arr2.add("555555");

        System.out.println(jsonObject.toJSONString());


    }

    public static JSONObject getJson(String actionStr ,Map<String,String> map) {
        JSONObject result = new JSONObject();
        JSONObject developer = new JSONObject();
        JSONObject device = new JSONObject();
        JSONObject user = new JSONObject();

        JSONObject param = new JSONObject();

        JSONObject child1 = new JSONObject();
        JSONObject child2 = new JSONObject();
        JSONObject child3 = new JSONObject();

        child1.put("key1","123");
        child1.put("key2","thy");

        child2.put("key1","123");
        child2.put("key2","thy");

        child3.put("key1","123");
        child3.put("key2","thy");

        JSONArray jsonStrs = new JSONArray();
        jsonStrs.add(0, child1);
        jsonStrs.add(1, child2);
        jsonStrs.add(2, child3);

        developer.put("apikey", "YHGGG4AK");
        developer.put("secretkey","035b5d5cfbe4685fa8f55111eb89157a");
        device.put("dnum","123");
        user.put("userid","123");
        result.put("action",actionStr);
        result.put("developer",developer);
        result.put("device",device);
        result.put("user",user);
        result.put("jsonStrs", jsonStrs);

        //????????????json??????????????????key-value????????????????????????
        for(String key:map.keySet()) {
            //??????hashmap???key??????
            param.put(key,map.get(key));
        }
        result.put("param",param);
        return  result;
    }


    public static void parseFile() throws Exception {
        String str_json = "{\"_id\":{\"$oid\":\"57c434c3ffaffed0c02f52b1\"},\"moive_type\":\"??????\\t??????\",\"official_website\":\"\",\"language\":\"?????????\",\"_in_time\":\"2016-08-29 21:12:35\",\"screenwriter\":\"???????????????????????? / Reidar J??nsson / ?????????????????????????????? / Per Berglund\",\"actor\":\"????????????????????????\\t??????????????????????????????\\t???????????????\\t?????????????????????\\tKicki Rundgren\\tLennart Hjulstr??m\\t????????????????????????\\tLeif Ericson\\tChristina Carlwind\\t??????????????????\\tViveca Dahl??n\\tArnold Alfredsson\\tFritz Elofsson\\tDidrik Gustafsson\\tJan-Philip Hollstr??m\",\"_src\":[{\"url\":\"https://movie.douban.com/subject/1291976/\",\"site_id\":{\"$numberLong\":\"30001487538087434\"}}],\"director\":\"????????????????????????\",\"douban_score\":\"8.5\",\"synopsis\":\"\\n                                ??????20??????50??????????????????????????????Anton Glanzelius ?????????????????????????????????Anki Lid??n ??????\",\"imdb\":\"tt0089606\",\"parti_people_sum\":\"9402\",\"_record_id\":\"d3e4a49ba1cde42887772e00f50d219f\",\"production_country\":\"??????\",\"release_time\":\"1985-12-12\",\"moive_title\":\"??????????????? Mitt liv som hund\",\"_utime\":\"2016-08-31 01:53:38\",\"alternate_name\":\"???????????? / My Life as a Dog\",\"running_time\":\"101 ??????\"}\n";

        String dir_src = "D:\\doc_kangjia\\??????????????????\\????????????\\movies_douban\\movies_douban.json";

        BufferedReader br = new BufferedReader(new FileReader(new File(dir_src)));
        BufferedWriter bw = new BufferedWriter(new FileWriter(new File("result_thy.txt"), true));

        String line = "";

        while ((line = br.readLine()) != null) {
            bw.write(parseJson(line));
            // break;
        }

        bw.flush();
        bw.close();
    }


    public static String parseJson(String str_json) {
        String ret = "";

        JSONObject jsonObject = JSONObject.parseObject(str_json, Feature.OrderedField);
        JSONObject obj_id = (JSONObject) jsonObject.get("_id");

        String synopsis = patternString(jsonObject.get("synopsis").toString());
        String moive_title = patternString(jsonObject.get("moive_title").toString());
        String alternate_name = patternString(jsonObject.get("alternate_name").toString());
        String actor = patternString(jsonObject.get("actor").toString());
        String official_website = patternString(jsonObject.get("official_website").toString());
        String language = patternString(jsonObject.get("language").toString());
        String production_country = patternString(jsonObject.get("production_country").toString());

        //??????????????????replace????????????????????????
        // String synopsis = replace(jsonObject.get("synopsis").toString(), "\\|", ";");

        ret += obj_id.get("$oid").toString() + "|"
                + jsonObject.get("moive_type") + "|"
                + official_website + "|"
                + language + "|"
                + jsonObject.get("_in_time") + "|"
                + jsonObject.get("screenwriter") + "|"
                + actor + "|";

        JSONArray obj_src = jsonObject.getJSONArray("_src");
        JSONObject TMP_loop;

        for (int i = 0; i < obj_src.size(); i++) {
            TMP_loop = (JSONObject) obj_src.get(i);
            //System.out.println(TMP_loop.get("url"));
            JSONObject obj_site_id = (JSONObject) TMP_loop.get("site_id");

            ret += TMP_loop.get("url") + "|";
            ret += obj_site_id.get("$numberLong") + "|";
            //System.out.println(obj_site_id.get("$numberLong"));
        }

        ret += jsonObject.get("director") + "|"
                + jsonObject.get("douban_score") + "|"
                + synopsis + "|"
                + jsonObject.get("imdb") + "|"
                + jsonObject.get("parti_people_sum") + "|"
                + jsonObject.get("_record_id") + "|"
                + production_country + "|"
                + jsonObject.get("release_time") + "|"
                + moive_title + "|"
                + jsonObject.get("_utime") + "|"
                + alternate_name + "|"
                + jsonObject.get("running_time")
        ;

        String ret_no_feed = patternString(ret, "(\r\n|\r|\n|\n\r)", "\t");

        ret_no_feed += "\n";

        return ret_no_feed;
    }


    public static String patternString(String str, String ...args){
        String src = "\\|";
        String dst = ";";
        if(args.length == 2){
            src = args[0];
            dst = args[1];
        }

        Pattern pattern_synopsis = Pattern.compile(src);
        String ret = pattern_synopsis.matcher(str).replaceAll(dst);

        return ret;
    }

    public static void parseJSONArray(String file_path) throws Exception {
        List<String> arrayList = new ArrayList<>();
        BufferedReader br = new BufferedReader(new FileReader(new File(file_path)));
        String line = "";
        while ((line = br.readLine()) != null) {
//            System.out.println(line);
            arrayList.add(line);
        }

        for(int i=0; i<arrayList.size();i++) {
            log.info("line: " + arrayList.get(i));
            JSONArray jsonArray = JSONArray.parseArray(arrayList.get(i));
            System.out.println("jsonArray: " + jsonArray.toString());
            System.out.println("jsonArray.size: " + jsonArray.size());
        }

    }


    @Test
    public void addJsonObject(){
        JSONObject first = new JSONObject();
        JSONObject second = new JSONObject();

        first.put("a", 1);
        first.put("b", 2);
        JSONObject tmp = new JSONObject();
        JSONArray sex1 = new JSONArray();
        sex1.add("male");
        tmp.put("sex", sex1);
        first.put("c", tmp);

        second.put("h", 11);
        second.put("i", 12);
        JSONObject tmp2 = new JSONObject();
        JSONArray sex2 = new JSONArray();
        sex2.add("female");
        tmp2.put("sex", sex2);
        second.put("c",tmp2);


        first.putAll(second);


        System.out.println(JSON.toJSONString(first, SerializerFeature.PrettyFormat));

    }

}

