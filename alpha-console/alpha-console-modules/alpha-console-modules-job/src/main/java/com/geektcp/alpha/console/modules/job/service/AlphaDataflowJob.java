package com.geektcp.alpha.console.modules.job.service;

import com.dangdang.ddframe.job.api.ShardingContext;
import com.dangdang.ddframe.job.api.dataflow.DataflowJob;

import java.util.List;
//https://github.com/xjzrc/elastic-job-lite-spring-boot-starter

public class AlphaDataflowJob implements DataflowJob<Integer> {


    @Override
    public List<Integer> fetchData(ShardingContext shardingContext) {

        return null;
    }

    @Override
    public void processData(ShardingContext shardingContext, List<Integer> list) {

    }
}
