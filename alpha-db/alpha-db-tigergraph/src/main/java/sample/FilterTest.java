package sample;

import alpha.common.base.json.JSONUtils;
import alpha.common.base.util.FileUtils;
import com.alibaba.fastjson.JSONObject;

/**
 * @author tanghaiyang on 2019/2/12.
 */
public class FilterTest {
    public static void main(String[] args) {
        String jsonStr = FileUtils.readTxt("expand_graph.json");
        System.out.println(jsonStr);

        JSONObject json = JSONObject.parseObject(jsonStr);
        System.out.println(json.toJSONString());
    }
}
