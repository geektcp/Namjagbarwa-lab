package com.geektcp.alpha.spring.shiro.mapper;


import com.geektcp.alpha.spring.shiro.entity.job.Job;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * @author tanghaiyang
 */
public interface JobMapper extends BaseMapper<Job> {
	
	List<Job> queryList();
}