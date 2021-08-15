package com.geektcp.alpha.spring.querydsl.service.service;

import com.geektcp.alpha.common.spring.model.PageResponse;
import com.geektcp.alpha.common.spring.model.TResponse;
import com.geektcp.alpha.spring.querydsl.service.model.qo.SmGroupQo;
import com.geektcp.alpha.spring.querydsl.service.model.suo.SmGroupSuo;

/**
 * Created by HaiyangWork on 2018/12/22.
 */
public interface SmGroupService {

    TResponse saveOrUpdate(SmGroupSuo suo);

    TResponse delete(SmGroupSuo suo);

    TResponse find(SmGroupQo qo);

    PageResponse findPage(SmGroupQo qo);

    TResponse search(SmGroupQo qo);
}
