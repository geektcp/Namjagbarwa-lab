package com.geektcp.alpha.console.mps.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.geektcp.alpha.console.mps.model.entity.SysLog;


public interface SysLogService extends IService<SysLog> {

    /**
     * 通过ID删除日志（逻辑删除）
     *
     * @param id 日志ID
     * @return true/false
     */
    Boolean updateByLogId(Long id);
}
