package com.geektcp.alpha.spring.shiro.controller.job;

import com.geektcp.alpha.spring.shiro.annotation.ControllerEndpoint;
import com.geektcp.alpha.spring.shiro.common.controller.BaseController;
import com.geektcp.alpha.spring.shiro.common.entity.Response;
import com.geektcp.alpha.spring.shiro.common.entity.QueryRequest;
import com.geektcp.alpha.spring.shiro.entity.job.Job;
import com.geektcp.alpha.spring.shiro.service.IJobService;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.wuwenze.poi.ExcelKit;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.quartz.CronExpression;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.List;
import java.util.Map;

/**
 * @author tanghaiyang
 */
@Slf4j
@Validated
@RestController
@RequestMapping("job")
public class JobController extends BaseController {

    @Autowired
    private IJobService jobService;

    @GetMapping
    @RequiresPermissions("job:view")
    public Response jobList(QueryRequest request, Job job) {
        Map<String, Object> dataTable = getDataTable(this.jobService.findJobs(request, job));
        return new Response().success().data(dataTable);
    }

    @GetMapping("cron/check")
    public boolean checkCron(String cron) {
        try {
            return CronExpression.isValidExpression(cron);
        } catch (Exception e) {
            return false;
        }
    }

    @PostMapping
    @RequiresPermissions("job:add")
    @ControllerEndpoint(operation = "新增定时任务", exceptionMessage = "新增定时任务失败")
    public Response addJob(@Valid Job job) {
        this.jobService.createJob(job);
        return new Response().success();
    }

    @GetMapping("delete/{jobIds}")
    @RequiresPermissions("job:delete")
    @ControllerEndpoint(operation = "删除定时任务", exceptionMessage = "删除定时任务失败")
    public Response deleteJob(@NotBlank(message = "{required}") @PathVariable String jobIds) {
        String[] ids = jobIds.split(StringPool.COMMA);
        this.jobService.deleteJobs(ids);
        return new Response().success();
    }

    @PostMapping("update")
    @ControllerEndpoint(operation = "修改定时任务", exceptionMessage = "修改定时任务失败")
    public Response updateJob(@Valid Job job) {
        this.jobService.updateJob(job);
        return new Response().success();
    }

    @GetMapping("run/{jobIds}")
    @RequiresPermissions("job:run")
    @ControllerEndpoint(operation = "执行定时任务", exceptionMessage = "执行定时任务失败")
    public Response runJob(@NotBlank(message = "{required}") @PathVariable String jobIds) {
        this.jobService.run(jobIds);
        return new Response().success();
    }

    @GetMapping("pause/{jobIds}")
    @RequiresPermissions("job:pause")
    @ControllerEndpoint(operation = "暂停定时任务", exceptionMessage = "暂停定时任务失败")
    public Response pauseJob(@NotBlank(message = "{required}") @PathVariable String jobIds) {
        this.jobService.pause(jobIds);
        return new Response().success();
    }

    @GetMapping("resume/{jobIds}")
    @RequiresPermissions("job:resume")
    @ControllerEndpoint(operation = "恢复定时任务", exceptionMessage = "恢复定时任务失败")
    public Response resumeJob(@NotBlank(message = "{required}") @PathVariable String jobIds) {
        this.jobService.resume(jobIds);
        return new Response().success();
    }

    @GetMapping("excel")
    @RequiresPermissions("job:export")
    @ControllerEndpoint(exceptionMessage = "导出Excel失败")
    public void export(QueryRequest request, Job job, HttpServletResponse response) {
        List<Job> jobs = this.jobService.findJobs(request, job).getRecords();
        ExcelKit.$Export(Job.class, response).downXlsx(jobs, false);
    }
}
