package com.geektcp.alpha.util.base.proxy.staticproxy;

import lombok.extern.slf4j.Slf4j;

/**
 * @author tanghaiyang on 2019/8/15.
 * 代理类--代理用户管理实现类
 */
@Slf4j
public class UserManagerImplProxy implements UserManagerProxy {

    // 目标对象
    private UserManagerProxy userManager;

    // 通过构造方法传入目标对象
    public UserManagerImplProxy(UserManagerProxy userManager) {
        this.userManager = userManager;
    }

    @Override
    public void addUser(String userId, String userName) {
        try {
            //添加打印日志的功能
            //开始添加用户
            log.info("start-->addUser()");
            userManager.addUser(userId, userName);
            //添加用户成功
            log.info("success-->addUser()");
        } catch (Exception e) {
            //添加用户失败
            log.info("error-->addUser()");
        }
    }

    @Override
    public void delUser(String userId) {
        userManager.delUser(userId);
    }

    @Override
    public String findUser(String userId) {
        userManager.findUser(userId);
        return "张三";
    }

    @Override
    public void modifyUser(String userId, String userName) {
        userManager.modifyUser(userId, userName);
    }

}
