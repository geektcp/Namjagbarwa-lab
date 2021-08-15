package com.geektcp.alpha.algorithm.arithmetic;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import lombok.extern.slf4j.Slf4j;
import com.geektcp.alpha.algorithm.arithmetic.BTreeNode;
import com.geektcp.alpha.algorithm.arithmetic.FileParse;
import com.geektcp.alpha.algorithm.arithmetic.TreeNode;


/**
 * @author tanghaiyang on 2019/2/21.
 */
@Slf4j
public class Application {
    public static JSONArray result = null;

    public static void main(String[] args) {
        String filterPath = "filterSample.txt";
        JSONObject condition = FileParse.buildFilter(filterPath);

        String resultPath = "resultSample.txt";
        result = FileParse.buildResult(resultPath);

        JSONObject filter = condition.getJSONObject("filter");
       // log.info("filter: {}", JSON.toJSONString(filter, SerializerFeature.PrettyFormat));

        BTreeNode root = new BTreeNode();

        buildTree(root, filter);

        log.info("=============", root.getData());

        JSONArray ret = root.calculate();

        log.info("ret: {}", JSON.toJSONString(ret,SerializerFeature.PrettyFormat));

    }


    public static void buildTree(BTreeNode tree, JSONObject filter){
        if(filter.containsKey("logic")) {
            String logic = filter.getString("logic");
            tree.setOperation(logic);
        }

        if(filter.containsKey("filters")) {
            JSONArray filters = filter.getJSONArray("filters");
            for (Object o : filters) {
                JSONObject ele = (JSONObject) o;
                if (ele.containsKey("operator")) {
                    BTreeNode subNode = new BTreeNode();
                    subNode.setSingleFilter(ele);
                    JSONArray data = excuteFilter(result, ele);
                    //log.info("data: \n{}",JSON.toJSONString(data,SerializerFeature.PrettyFormat));
                    subNode.setData(data);
                    tree.getChild().add(subNode);
                }else if (ele.containsKey("logic")) {
                    BTreeNode subTree = new BTreeNode();
                    buildTree(subTree, ele);
                    tree.getChild().add(subTree);
                }
            }
        }
    }


    public static JSONArray excuteFilter(JSONArray result, JSONObject singleFilter){
        JSONArray ret = new JSONArray();
        JSONArray filterValue = singleFilter.getJSONArray("value");
        String filterField = singleFilter.getString("field");
        String fieldType = singleFilter.getString("fieldType");
        for(Object o: result){
            JSONObject ele = (JSONObject)o;
            JSONObject attributes = ele.getJSONObject("attributes");
            Object resultField = attributes.get(filterField);
            if(filterValue.contains(resultField)){
                ret.add(ele);
            }
        }

        return ret;
    }



    public static void calculator() {
        double a = new TreeNode("1*2+(3+4)+5-6").calculate();
        System.out.println(a);

    }
}
