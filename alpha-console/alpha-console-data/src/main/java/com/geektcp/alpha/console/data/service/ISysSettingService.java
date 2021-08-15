package com.geektcp.alpha.console.data.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.geektcp.alpha.console.data.model.entity.SysSetting;


/**
 *
 * SysSetting 表数据服务层接口
 *
 */
public interface ISysSettingService extends IService<SysSetting> {

	SysSetting findSysByGlobalKey(String sysGlobalKey);


}