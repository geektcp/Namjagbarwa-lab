package com.geektcp.alpha.db.es6.dao;

import com.geektcp.alpha.db.es6.bean.StoreURL;
import com.geektcp.alpha.db.es6.model.EsQuery;
import com.geektcp.alpha.db.es6.model.EsQueryResult;

import java.util.List;

/**
 * @author tanghaiyang on 2019/5/7.
 */
public interface EsSearchDao {

    EsQueryResult search(StoreURL storeURL, EsQuery esQuery);

    EsQueryResult searchByIds(StoreURL storeURL, EsQuery esQuery);

    EsQueryResult searchByFields(StoreURL storeURL, EsQuery esQuery);

    List<EsQueryResult> multiSearch(StoreURL storeURL, List<EsQuery> list);

    EsQueryResult searchByDSL(StoreURL storeURL, String queryDSL);

}
