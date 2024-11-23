package com.example.demo.config;

import com.example.demo.properties.AppProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;

@EnableScheduling
@EnableAsync
@Configuration
public class ScheduledConfig {
    @Autowired
    private AppProperties appProperties;

    @Bean
    public Executor taskExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        //设置核心线程数
        executor.setCorePoolSize(appProperties.getExecutorConfig().getCorePoolSize());
        //设置最大线程数
        executor.setMaxPoolSize(appProperties.getExecutorConfig().getMaxPoolSize());
        //设置任务队列量
        executor.setQueueCapacity(appProperties.getExecutorConfig().getQueueCapacity());
        //设置线程活跃时间（秒）
        executor.setKeepAliveSeconds(appProperties.getExecutorConfig().getKeepAliveTime());
        //设置默认线程名称（线程前缀名称）
        executor.setThreadNamePrefix("taskExecutor-query-");
        //设置拒绝策略
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        //设置允许核心线程超市，默认是false
        executor.setAllowCoreThreadTimeOut(false);

        return executor;
    }
}
