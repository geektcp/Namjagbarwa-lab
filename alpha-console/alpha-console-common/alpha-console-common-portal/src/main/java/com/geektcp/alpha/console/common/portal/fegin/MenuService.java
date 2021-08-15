package com.geektcp.alpha.console.common.portal.fegin;

import com.geektcp.alpha.console.common.portal.fegin.fallback.MenuServiceFallbackImpl;
import com.geektcp.alpha.console.common.portal.vo.SysMenuVO;
import com.geektcp.alpha.console.common.portal.vo.TreeMenu;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Set;

@FeignClient(name = "alpha-console-data",  fallback = MenuServiceFallbackImpl.class)
public interface MenuService {

    @GetMapping("/menu/findTreeMenusByUserId/{id}")
    List<TreeMenu> findTreeMenusByUserId(@PathVariable("id") String userId);

    @GetMapping("/menu/findMenusByRole/{role}")
    Set<SysMenuVO> findMenusByRole(@PathVariable("role") String roleCode);
}
