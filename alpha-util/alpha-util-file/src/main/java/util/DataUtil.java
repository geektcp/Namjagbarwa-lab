package util;

/**
 * @author tanghaiyang on 2019/1/18.
 */

import com.alibaba.fastjson.JSONObject;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Random;

public class DataUtil {

    public static String getRandomString(int length) {
        String str = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        Random random = new Random();
        StringBuilder sb = new StringBuilder();

        for(int i = 0; i < length; ++i) {
            int number = random.nextInt(62);
            sb.append(str.charAt(number));
        }

        return sb.toString();
    }

    public static String getRandomInt(int length) {
        String str = "0123456789";
        Random random = new Random();
        StringBuilder sb = new StringBuilder();

        for(int i = 0; i < length; ++i) {
            int number = random.nextInt(10);
            sb.append(str.charAt(number));
        }

        return sb.toString();
    }

    public static JSONObject buildDataVetice(String table, List<JSONObject> list) {
        JSONObject ret = new JSONObject();
        JSONObject vertices = new JSONObject();
        ret.put("vertices", vertices);
        JSONObject tableData = new JSONObject();
        for(JSONObject record: list){
            JSONObject jsonObject = new JSONObject();
            for(String key: record.keySet()){
                Object value = record.get(key);
                if(!key.equals("vid")) {
                    JSONObject valueData = new JSONObject();
                    valueData.put("value", value);
                    jsonObject.put(key, valueData);
                }
            }
            String vetexId = record.getString("vid");
            if(vetexId == null || vetexId.length()==0) {
                jsonObject.put("vid", getRandomString(3));
            }
            tableData.put(vetexId, jsonObject);
        }
        vertices.put(table, tableData);
        return ret;
    }

    public static JSONObject buildDataEdge(List<JSONObject> list) {
        JSONObject ret = new JSONObject();
        JSONObject edges = new JSONObject();
        ret.put("edges", edges);

        for(JSONObject record: list){
            String key = record.getString("fromType");
            if(!edges.containsKey(key)) {
                edges.put(key, new JSONObject());
            }

            JSONObject fromType = edges.getJSONObject(key);
            key = record.getString("fromId");
            if(!fromType.containsKey(key)) {
                fromType.put(key, new JSONObject());
            }

            JSONObject fromId = fromType.getJSONObject(key);
            key = record.getString("edgeType");
            if(!fromId.containsKey(key)) {
                fromId.put(key, new JSONObject());
            }

            JSONObject edgeType = fromId.getJSONObject(key);
            key = record.getString("endType");
            if(!edgeType.containsKey(key)) {
                edgeType.put(key, new JSONObject());
            }

            JSONObject endType = edgeType.getJSONObject(key);
            key = record.getString("endId");
            if(!endType.containsKey(key)) {
                endType.put(key, new JSONObject());
            }

            JSONObject endId = endType.getJSONObject(key);
            key = record.getString("attribute");
            if(key != null && !endId.containsKey(key)) {
                endId.putAll(record.getJSONObject("attribute"));
            }
        }

        return ret;
    }

    // 加密
    public static String getBase64(String str) {
        byte[] b = null;
        String s = null;
        try {
            b = str.getBytes("utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        if (b != null) {
            s = new BASE64Encoder().encode(b);
        }
        return s;
    }

    // 解密
    public static String getFromBase64(String s) {
        byte[] b = null;
        String result = null;
        if (s != null) {
            BASE64Decoder decoder = new BASE64Decoder();
            try {
                b = decoder.decodeBuffer(s);
                result = new String(b, "utf-8");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return result;
    }


}
