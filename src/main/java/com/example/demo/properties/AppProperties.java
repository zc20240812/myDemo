package com.example.demo.properties;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Getter
@Setter
@Component
@ConfigurationProperties(value = "app")
public class AppProperties {
    private ExecutorConfig executorConfig;

    @Data
    public static class ExecutorConfig {
        /**
         * @Author zhangchen
         * @Filed 核心线程数
         * @Date 2024-11-21
         */
        private Integer corePoolSize;
        /**
         * @Author zhangchen
         * @Filed 最大线程数
         * @Date 2024-11-21
         */
        private Integer maxPoolSize;
        /**
         * @Author zhangchen
         * @Filed 任务队列容量（阻塞队列）
         * @Date 2024-11-21
         */
        private Integer queueCapacity;
        /**
         * @Author zhangchen
         * @Filed 线程空闲时间
         * @Date 2024-11-21
         */
        private Integer keepAliveTime;
    }
}
