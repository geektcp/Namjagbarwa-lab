package com.geektcp.alpha.spring.shiro.controller.job;

import com.geektcp.alpha.spring.shiro.common.entity.AlphaConstant;
import com.geektcp.alpha.spring.shiro.utils.AlphaUtil;
import com.geektcp.alpha.spring.shiro.entity.job.Job;
import com.geektcp.alpha.spring.shiro.service.IJobService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.constraints.NotBlank;

/**
 * @author tanghaiyang
 */
@Controller("jobView")
@RequestMapping(AlphaConstant.VIEW_PREFIX + "job")
public class ViewController {

    @Autowired
    private IJobService jobService;

    @GetMapping("job")
    @RequiresPermissions("job:view")
    public String online() {
        return AlphaUtil.view("job/job");
    }

    @GetMapping("job/add")
    @RequiresPermissions("job:add")
    public String jobAdd() {
        return AlphaUtil.view("job/jobAdd");
    }

    @GetMapping("job/update/{jobId}")
    @RequiresPermissions("job:update")
    public String jobUpdate(@NotBlank(message = "{required}") @PathVariable Long jobId, Model model) {
        Job job = jobService.findJob(jobId);
        model.addAttribute("job", job);
        return AlphaUtil.view("job/jobUpdate");
    }

    @GetMapping("log")
    @RequiresPermissions("job:log:view")
    public String log() {
        return AlphaUtil.view("job/jobLog");
    }

}
