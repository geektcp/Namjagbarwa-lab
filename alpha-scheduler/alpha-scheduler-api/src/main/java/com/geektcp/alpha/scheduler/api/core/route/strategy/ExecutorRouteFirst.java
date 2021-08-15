package com.geektcp.alpha.scheduler.api.core.route.strategy;

import com.geektcp.alpha.scheduler.api.core.route.ExecutorRouter;
import com.geektcp.alpha.scheduler.core.biz.model.ReturnT;
import com.geektcp.alpha.scheduler.core.biz.model.TriggerParam;
import java.util.List;

/**
 * Created by xuxueli on 17/3/10.
 */
public class ExecutorRouteFirst extends ExecutorRouter {

    @Override
    public ReturnT<String> route(TriggerParam triggerParam, List<String> addressList){
        return new ReturnT<>(addressList.get(0));
    }

}
