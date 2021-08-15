package com.geektcp.alpha.algorithm.arithmetic;

import alpha.common.base.util.FileUtils;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

/**
 * @author tanghaiyang on 2019/2/21.
 */
public class FileParse {
    public static JSONObject buildFilter(String dataFile){
        return JSONObject.parseObject(FileUtils.readTxt(dataFile));
    }


    public static JSONArray buildResult(String dataFile){
        return JSONArray.parseArray(FileUtils.readTxt(dataFile));
    }
}
