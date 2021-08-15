package com.geektcp.alpha.driver.mybatis2.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.geektcp.alpha.driver.mybatis2.dao.UserDao;
import com.geektcp.alpha.driver.mybatis2.model.qo.PageQo;
import com.geektcp.alpha.driver.mybatis2.model.vo.PageResponse;
import com.geektcp.alpha.driver.mybatis2.model.po.UserPo;
import com.geektcp.alpha.driver.mybatis2.model.qo.UserQo;
import com.geektcp.alpha.driver.mybatis2.model.vo.UserVo;
import com.geektcp.alpha.driver.mybatis2.service.UserService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author haiyang on 3/27/20 4:17 PM.
 */
@Service
@Slf4j
public class UserServiceImpl extends ServiceImpl<UserDao, UserPo> implements UserService {

    @Override
    public PageResponse<UserVo> findPage(UserQo qo) {
        Wrapper<UserPo> wrapper = new EntityWrapper<>();
        PageQo pageQo = qo.getPageQo();
        // com.baomidou.mybatisplus.plugins.pagination.PageHelper
        // must use com.github.pagehelper.PageHelper
        PageHelper.startPage(pageQo.getPageNo(), pageQo.getPageSize());
        List<UserPo> result = this.selectList(wrapper);
        PageInfo<UserPo> pageInfo = new PageInfo<>(result);
        log.info("pageInfo: {}", JSON.toJSONString(pageInfo, true));
        return new PageResponse();
    }
}
