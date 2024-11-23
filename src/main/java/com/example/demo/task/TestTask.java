package com.example.demo.task;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class TestTask {
    /**
     * @Author zhangchen
     * @Descript 注解@Async里的“taskExecutor”就是ScheduledConfig里定义的bean: public Executor taskExecutor()
     * @Date 2024-11-21
     */
    @Async("taskExecutor")
    @Scheduled(cron = "${app.task-query}")
    public void query() throws Exception {
        log.info("任务执行中,定时间隔1分钟!");
    }
}
