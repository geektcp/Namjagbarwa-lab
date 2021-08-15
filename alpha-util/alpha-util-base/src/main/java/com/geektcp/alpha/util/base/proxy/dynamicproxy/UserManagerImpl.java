package com.geektcp.alpha.util.base.proxy.dynamicproxy;

import com.geektcp.alpha.util.base.proxy.staticproxy.UserManagerProxy;
import lombok.extern.slf4j.Slf4j;

/**
 * @author tanghaiyang on 2019/8/15.
 */
@Slf4j
public class UserManagerImpl implements UserManagerProxy {

    @Override
    public void addUser(String userId, String userName) {
        log.info("UserManagerImpl.addUser");
    }

    @Override
    public void delUser(String userId) {
        log.info("UserManagerImpl.delUser");
    }

    @Override
    public String findUser(String userId) {
        log.info("UserManagerImpl.findUser");
        return "张三";
    }

    @Override
    public void modifyUser(String userId, String userName) {
        log.info("UserManagerImpl.modifyUser");

    }

}
