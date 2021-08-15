package com.geektcp.alpha.db.es6.dao;

import com.geektcp.alpha.common.spring.model.TResponse;
import com.geektcp.alpha.db.es6.bean.StoreURL;
import com.geektcp.alpha.db.es6.bean.Source;

import java.util.List;

/**
 * Created by nagle on 2017/12/28.
 */
public interface EsIndexDao {

    void testConnect(StoreURL storeURL);

    boolean existsIndex(StoreURL storeURL, String index);

    boolean existsType(StoreURL storeURL, String index, String type);

    boolean createIndex(StoreURL storeURL, String index);

    boolean deleteIndex(StoreURL storeURL, String index);

    boolean createType(StoreURL storeURL, String index, String type);

    TResponse bulkUpsert(StoreURL storeURL, String index, String type, List<Source> sourceList);

    TResponse delete(StoreURL storeURL, String index, String type, List<Source> sources);
}
