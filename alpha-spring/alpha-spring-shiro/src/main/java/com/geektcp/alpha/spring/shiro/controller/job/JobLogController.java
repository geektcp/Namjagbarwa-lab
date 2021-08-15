package com.geektcp.alpha.spring.shiro.controller.job;

import com.geektcp.alpha.spring.shiro.annotation.ControllerEndpoint;
import com.geektcp.alpha.spring.shiro.common.controller.BaseController;
import com.geektcp.alpha.spring.shiro.common.entity.Response;
import com.geektcp.alpha.spring.shiro.common.entity.QueryRequest;
import com.geektcp.alpha.spring.shiro.entity.job.JobLog;
import com.geektcp.alpha.spring.shiro.service.IJobLogService;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.wuwenze.poi.ExcelKit;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.NotBlank;
import java.util.List;
import java.util.Map;

/**
 * @author tanghaiyang
 */
@Slf4j
@Validated
@RestController
@RequestMapping("jobLog")
public class JobLogController extends BaseController {

    @Autowired
    private IJobLogService jobLogService;

    @GetMapping
    @RequiresPermissions("job:log:view")
    public Response jobLogList(QueryRequest request, JobLog log) {
        Map<String, Object> dataTable = getDataTable(this.jobLogService.findJobLogs(request, log));
        return new Response().success().data(dataTable);
    }

    @GetMapping("delete/{jobIds}")
    @RequiresPermissions("job:log:delete")
    @ControllerEndpoint(exceptionMessage = "删除调度日志失败")
    public Response deleteJobLog(@NotBlank(message = "{required}") @PathVariable String jobIds) {
        String[] ids = jobIds.split(StringPool.COMMA);
        this.jobLogService.deleteJobLogs(ids);
        return new Response().success();
    }

    @GetMapping("excel")
    @RequiresPermissions("job:log:export")
    @ControllerEndpoint(exceptionMessage = "导出Excel失败")
    public void export(QueryRequest request, JobLog jobLog, HttpServletResponse response) {
        List<JobLog> jobLogs = this.jobLogService.findJobLogs(request, jobLog).getRecords();
        ExcelKit.$Export(JobLog.class, response).downXlsx(jobLogs, false);
    }
}
