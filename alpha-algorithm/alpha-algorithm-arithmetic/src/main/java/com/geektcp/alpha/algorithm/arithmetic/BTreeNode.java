package com.geektcp.alpha.algorithm.arithmetic;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

/**
 * @author tanghaiyang on 2019/2/21.
 */
@Data
@Slf4j
public class BTreeNode {
    private JSONArray data;
    private JSONObject singleFilter;
    private String operation;
    private List<BTreeNode> child = new LinkedList<>();

    public JSONArray calculate(){
        JSONArray ret = new JSONArray();
        if(Objects.isNull(operation) || child.isEmpty()){
            return data;
        }else {
            if(operation.equals("AND")) {
                for (BTreeNode tree : child) {
                    JSONArray tmpDataAnd = tree.calculate();
                    log.info("tmpDataAnd: {}", JSON.toJSONString(tmpDataAnd, SerializerFeature.PrettyFormat));
                    if(ret.isEmpty()){
                        ret.addAll(tmpDataAnd);
                    }else {
                        ret.retainAll(tmpDataAnd);
                    }
                }
            }else if(operation.equals("OR")){
                for(BTreeNode tree: child){
                    JSONArray tmpDataOr = tree.calculate();
                    log.info("tmpDataOr: {}", JSON.toJSONString(tmpDataOr, SerializerFeature.PrettyFormat));
                    ret.removeAll(tmpDataOr);
                    ret.addAll(tmpDataOr);
                }
            }
        }

        return ret;
    }
}
