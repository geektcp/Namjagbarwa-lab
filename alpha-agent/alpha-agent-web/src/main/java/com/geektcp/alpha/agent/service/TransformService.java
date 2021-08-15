package com.geektcp.alpha.agent.service;

import com.alibaba.fastjson.JSONObject;

/**
 * @author tanghaiyang on 2018/5/16.
 */
public interface TransformService {

    JSONObject transform(JSONObject msg);

}

