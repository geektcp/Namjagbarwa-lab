package com.geektcp.alpha.console.data.admin;

import com.geektcp.alpha.console.data.model.entity.SysSetting;
import com.geektcp.alpha.console.data.model.entity.SysSettingMenu;
import com.geektcp.alpha.console.data.model.vo.SysSettingMenuVO;
import com.geektcp.alpha.console.data.model.vo.SysSettingVO;
import com.geektcp.alpha.console.data.service.ISysSettingMenuService;
import com.geektcp.alpha.console.data.service.ISysSettingService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@RestController()
@RequestMapping("/setting")
public class SettingController {

    @Autowired
    private ISysSettingService sysSettingService;

    @Autowired
    private ISysSettingMenuService sysSettingMenuService;

    @RequestMapping("/findSysByGlobalKey")
    public SysSettingVO findSysByGlobalKey(@RequestBody String sysGlobalKey) {
        SysSettingVO settingVO = new SysSettingVO();
        SysSetting setting =  sysSettingService.findSysByGlobalKey(sysGlobalKey);
        if(setting!=null){
            BeanUtils.copyProperties(setting,settingVO);
        }
        return settingVO;
    }

    @GetMapping("/findSysMenusById/{id}")
    public List<SysSettingMenuVO> findSysMenusById(@PathVariable String id) {
        List<SysSettingMenuVO> SysSettingMenuVOs = new ArrayList<SysSettingMenuVO>();
        List<SysSettingMenu> SysSettingMenus =sysSettingMenuService.selectBySetting(id);
        SysSettingMenus.forEach(input->{
            SysSettingMenuVO vo = new SysSettingMenuVO();
            BeanUtils.copyProperties(input,vo);
            SysSettingMenuVOs.add(vo);
        });
        return SysSettingMenuVOs;
    }

}
