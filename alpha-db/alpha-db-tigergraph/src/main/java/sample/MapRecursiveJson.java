package sample;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import lombok.extern.slf4j.Slf4j;

import java.util.*;

/**
 * @author tanghaiyang on 2019/2/11.
 */
@Slf4j
public class MapRecursiveJson {
    public static void main(String[] args) {
        String startpoint = "p7";
        String endpoint = "s4";

        JSONObject json = buildJson();
        log.info(JSON.toJSONString(json, SerializerFeature.PrettyFormat));

        JSONObject json2 = buildJson2();
        log.info(JSON.toJSONString(json2, SerializerFeature.PrettyFormat));

        JSONArray keys = new JSONArray();
        keys.add(endpoint);

//        JSONArray ret = new JSONArray();
//        ret.add(endpoint);
        Set<String> ret = new HashSet<>();

        JSONArray tmpkeys = new JSONArray();

        for(int i=0;i<5; i++){
            tmpkeys.clear();
//            getKeys(json, keys, ret, tmpkeys);
            getKeys2(json,json2, keys, ret, tmpkeys);
            keys.clear();
            keys.addAll(tmpkeys);
//            ret.addAll(keys);
//            break;
//            if(ret.contains(startpoint)) { break;}
//            if(ret.size() > 10) break;
        }
        log.info("ret: " + ret);
    }


    private static void getKeys(JSONObject json, JSONArray keys, Set<String> ret, JSONArray tmpkeys){
        for(Object keyObject: keys) {
            String key = keyObject.toString();
            for (Map.Entry<String, Object> entry : json.entrySet()) {
                String tmpkey = entry.getKey();
                JSONArray tmpValue = (JSONArray) entry.getValue();
                if(tmpkey.equals(key) ){
                    for(Object o: tmpValue){
                        ret.add(o.toString());
                    }
                    tmpkeys.addAll(tmpValue);
                    log.info("tmpValue: {}", tmpValue);
                }

                if(tmpValue.contains(key)){
                    ret.add(tmpkey);
                    tmpkeys.add(tmpkey);
                    log.info("tmpkey: {}", tmpkey);
                }
            }

//            if (json.containsKey(key)) {
//                ret.addAll(json.getJSONArray(key));
//                tmpkeys.addAll(json.getJSONArray(key));
//                log.info("key: " + json.get(key));
//            }

            log.info("===============tmpkeys: {}", tmpkeys);
        }

    }

    private static void getKeys2(JSONObject json, JSONObject json2, JSONArray keys, Set<String> ret, JSONArray tmpkeys){
        for(Object keyObject: keys) {
            String key = keyObject.toString();

            if (json.containsKey(key)) {
                JSONArray arr = json.getJSONArray(key);
                for(Object o: arr) {
                    ret.add(o.toString());
                }
                tmpkeys.addAll(arr);
                log.info("key: " + json.get(key));
            }

            if (json2.containsKey(key)) {
                JSONArray arr = json2.getJSONArray(key);
                for(Object o: arr) {
                    ret.add(o.toString());
                }
                tmpkeys.addAll(arr);
                log.info("key: " + arr);
            }

            log.info("===============tmpkeys: {}", tmpkeys);
        }

    }


    private static JSONObject buildJson() {
        JSONObject ret = new JSONObject();
        JSONArray arr1 = new JSONArray();
        JSONArray arr2 = new JSONArray();
        JSONArray arr3 = new JSONArray();
        JSONArray arr4 = new JSONArray();
        JSONArray arr5 = new JSONArray();

        ret.put("s1", arr1);
        arr1.add("p0");
        arr1.add("s4");

        ret.put("p0", arr2);
        arr2.add("c3");

        ret.put("s4", arr3);
        arr3.add("c0");

        ret.put("c3", arr4);
        arr4.add("p7");

        ret.put("c0", arr5);
        arr5.add("p7");

        return ret;

//        {
//            "s1": [ "p0", "s4" ],
//            "p0": [ "c3" ],
//            "s4": [ "c0"  ],
//            "c3": [ "p7" ],
//            "c0": [ "p7" ]
//        }
//        path:
//        p7, c0, s4
//        p7, c3, p9, s1, s4
//        [p0, c3, s4, p7, c0, s1]
    }

    private static JSONObject buildJson2() {
        JSONObject ret = new JSONObject();
        JSONArray arr1 = new JSONArray();
        JSONArray arr2 = new JSONArray();
        JSONArray arr3 = new JSONArray();
        JSONArray arr4 = new JSONArray();
        JSONArray arr5 = new JSONArray();

        ret.put("p0", arr1);
        arr1.add("s1");

        ret.put("s4", arr2);
        arr2.add("s1");

        ret.put("c3", arr3);
        arr3.add("p0");

        ret.put("c0", arr4);
        arr4.add("s4");

        ret.put("p7", arr5);
        arr5.add("c3");
        arr5.add("c0");

        return ret;

//        {
//            "p0": [ "s1" ],
//            "s4": [ "s1" ],
//            "c3": [ "p0" ],
//            "c0": [ "s4" ],
//            "p7": [ "c3", "c0" ]
//        }
    }
}

