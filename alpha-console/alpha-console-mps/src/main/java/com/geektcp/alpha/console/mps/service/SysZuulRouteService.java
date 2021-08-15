package com.geektcp.alpha.console.mps.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.geektcp.alpha.console.mps.model.entity.SysZuulRoute;


public interface SysZuulRouteService extends IService<SysZuulRoute> {

    /**
     * 立即生效配置
     * @return
     */
    Boolean applyZuulRoute();
}
