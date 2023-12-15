package top.oneyi.generator.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * 第一种方式使用: 线程池配置
 * 第二种方式: 在启动类上开启异步处理 @EnableAsync , 在方法上开启异步处理 @Async
 */
@Component
public class TaskExecutor {

    @Value("${spring.task.execution.pool.queue-capacity}")
    private int queueCapacity;

    @Value("${spring.task.execution.pool.max-size}")
    private int poolMaxSize;

    @Value("${spring.task.execution.pool.core-size}")
    private int poolCoreSize;

    @Resource
    private ThreadPoolTaskExecutor executor;
}
