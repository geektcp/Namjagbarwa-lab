package com.geektcp.alpha.agent.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.geektcp.alpha.agent.service.TransformService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @author haiyang.tang on 10.22 022 10:38:06.
 */
@Slf4j
@Component
public class TransformServiceImpl implements TransformService {

    public JSONObject transform(JSONObject msg) {
        log.info("告警消息:\n{}", JSON.toJSONString(msg, true));

        JSONObject dingTalk = new JSONObject();

        log.info("钉钉告警消息: \n{}", JSON.toJSONString(dingTalk, true));
        return dingTalk;
    }


}
