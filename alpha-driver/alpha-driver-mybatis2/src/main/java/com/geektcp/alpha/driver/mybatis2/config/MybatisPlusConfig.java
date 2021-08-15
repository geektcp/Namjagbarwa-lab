package com.geektcp.alpha.driver.mybatis2.config;

import com.baomidou.mybatisplus.plugins.PaginationInterceptor;
import com.baomidou.mybatisplus.plugins.PerformanceInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.transaction.annotation.EnableTransactionManagement;


@Configuration
@EnableTransactionManagement
@MapperScan({ "com.geektcp.alpha.driver.mybatis.dao" })
public class MybatisPlusConfig {

	/**
	 * mybatis-plus分页插件<br>
	 * 文档：http://mp.baomidou.com<br>
	 */
	@Bean
	public PaginationInterceptor paginationInterceptor() {
		PaginationInterceptor paginationInterceptor = new PaginationInterceptor();
		paginationInterceptor.setDialectType("mysql");

		return paginationInterceptor;
	}

	/**
	 * 设置 dev test 环境开启
	 *
	 * @return
	 */
	@Bean
	@Profile({ "test" })
	public PerformanceInterceptor performanceInterceptor() {
		return new PerformanceInterceptor();
	}

}
