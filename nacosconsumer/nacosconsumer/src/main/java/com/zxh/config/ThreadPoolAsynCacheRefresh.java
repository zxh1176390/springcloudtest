package com.zxh.config;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

@Configuration
@Slf4j
public class ThreadPoolAsynCacheRefresh  {


    @Bean(name = "cacheCheckThreadPool")
    public ThreadPoolTaskScheduler productPoolConf() {
        ThreadPoolTaskScheduler pool = new ThreadPoolTaskScheduler();
        pool.setPoolSize(2);
        pool.setThreadNamePrefix("cacheCheckThreadPool-");
        // 等待任务完成再销毁线程池(让任务先关闭)
        pool.setWaitForTasksToCompleteOnShutdown(true);
        pool.setAwaitTerminationSeconds(20);
        pool.setRemoveOnCancelPolicy(true);
        pool.initialize();
        //丢弃旧任务
        pool.setRejectedExecutionHandler( new ThreadPoolExecutor.DiscardOldestPolicy());
        log.info("cacheCheckThreadPool init complete");
        return pool;
    }


    @Bean(name = "priceThreadPool")
    public ThreadPoolExecutor priceThreadPool() {
        ThreadFactory threadFactory = new ThreadFactoryBuilder().setNameFormat("price-pool-%d").build();
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(200, 200, 60L, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<Runnable>(), threadFactory);
        threadPoolExecutor.allowCoreThreadTimeOut(true);
        return threadPoolExecutor;
    }

}