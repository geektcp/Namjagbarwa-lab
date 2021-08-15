package com.geektcp.alpha.console.common.portal.fegin;

import com.geektcp.alpha.console.common.portal.fegin.fallback.UserServiceFallbackImpl;
import com.geektcp.alpha.console.common.portal.vo.SysUserVO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@FeignClient(name = "alpha-console-data", fallback = UserServiceFallbackImpl.class)
public interface UserService {

    @GetMapping("/user/findUserById/{id}")
    SysUserVO findUserById(@PathVariable("id") String userId);
}
