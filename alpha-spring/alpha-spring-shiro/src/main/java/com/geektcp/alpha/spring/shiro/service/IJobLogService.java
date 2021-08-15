package com.geektcp.alpha.spring.shiro.service;

import com.geektcp.alpha.spring.shiro.common.entity.QueryRequest;
import com.geektcp.alpha.spring.shiro.entity.job.JobLog;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @author tanghaiyang
 */
public interface IJobLogService extends IService<JobLog> {

    IPage<JobLog> findJobLogs(QueryRequest request, JobLog jobLog);

    void saveJobLog(JobLog log);

    void deleteJobLogs(String[] jobLogIds);
}
