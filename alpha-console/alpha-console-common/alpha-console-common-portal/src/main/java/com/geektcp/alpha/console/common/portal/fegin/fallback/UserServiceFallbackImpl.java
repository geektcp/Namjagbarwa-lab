package com.geektcp.alpha.console.common.portal.fegin.fallback;

import com.geektcp.alpha.console.common.portal.fegin.UserService;
import com.geektcp.alpha.console.common.portal.vo.SysUserVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


/**
 * 用户服务的fallback
 */
@Slf4j
@Service
public class UserServiceFallbackImpl implements UserService {

    @Override
    public SysUserVO findUserById(String userId) {
        log.error("调用{}异常:{}", "findTreeMenusByUserId", userId);
        return null ;
    }
}
