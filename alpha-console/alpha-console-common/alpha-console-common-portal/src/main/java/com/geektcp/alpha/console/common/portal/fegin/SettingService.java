package com.geektcp.alpha.console.common.portal.fegin;

import com.geektcp.alpha.console.common.portal.fegin.fallback.SettingServiceFallbackImpl;
import com.geektcp.alpha.console.common.portal.vo.SysSettingMenuVO;
import com.geektcp.alpha.console.common.portal.vo.SysSettingVO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "alpha-console-data", fallback = SettingServiceFallbackImpl.class )
public interface SettingService {

    @RequestMapping(value="/setting/findSysByGlobalKey",method = RequestMethod.POST,consumes = MediaType.APPLICATION_JSON_VALUE)
    SysSettingVO findSysByGlobalKey(@RequestBody String sysGlobalKey);

    @GetMapping(value="/setting/findSysMenusById/{id}")
    List<SysSettingMenuVO> findSysMenusById(@PathVariable("id") String sysId);
}
