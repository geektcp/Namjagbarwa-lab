package com.geektcp.alpha.sys.auth.service;


import com.geektcp.alpha.common.spring.model.PageResponse;
import com.geektcp.alpha.common.spring.model.TResponse;
import com.geektcp.alpha.sys.auth.model.qo.SysUserQo;
import com.geektcp.alpha.sys.auth.model.suo.SysUserSuo;
import com.geektcp.alpha.sys.auth.model.vo.SysUserVo;

import java.util.List;

/**
 * @author tanghaiyang on 2018/1/4.
 */
public interface DemoService {

    List<SysUserVo> find(SysUserQo qo);

    PageResponse<SysUserVo> findPage(SysUserQo qo);

    TResponse<SysUserVo> findUserRoles(SysUserQo qo);

    PageResponse<SysUserVo> findPageUserRoles(SysUserQo qo);

    TResponse saveOrUpdate(SysUserSuo suo);

    TResponse delete(Long userId);
}
