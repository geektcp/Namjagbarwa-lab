package com.geektcp.alpha.driver.mybatis2.service;

import com.geektcp.alpha.driver.mybatis2.model.vo.PageResponse;
import com.geektcp.alpha.driver.mybatis2.model.qo.UserQo;
import com.geektcp.alpha.driver.mybatis2.model.vo.UserVo;

/**
 * @author haiyang on 3/27/20 4:16 PM.
 */
public interface UserService {

    PageResponse<UserVo> findPage(UserQo qo);

}
