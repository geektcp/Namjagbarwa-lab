package com.geektcp.alpha.console.data.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.geektcp.alpha.console.data.mapper.SysSettingMapper;
import com.geektcp.alpha.console.data.model.entity.SysSetting;
import com.geektcp.alpha.console.data.service.ISysSettingService;
import org.springframework.stereotype.Service;

/**
 *
 * SysSetting 表数据服务层接口实现类
 *
 */
@Service
public class SysSettingServiceImpl extends ServiceImpl<SysSettingMapper, SysSetting> implements ISysSettingService {
	
	@Override
	public SysSetting findSysByGlobalKey(String sysGlobalKey) {
		return this.getOne(new QueryWrapper<SysSetting>().eq("sys_Global_Key",sysGlobalKey));
	}


}