package com.zxh.util;

import org.apache.commons.lang3.concurrent.BasicThreadFactory;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @Description:
 * @Author: zhengxinhui
 * @Date: 2021/7/20 17:14
 */
public class ThreadPoolExecutorUtil {
    private static ThreadPoolExecutor threadPool;
    static {
        init();
    }
    public static ThreadPoolExecutor getThreadPoolExecutor() {
        init();
        return threadPool;
    }

    private static void init() {
        if(null==threadPool){
            synchronized (ThreadPoolExecutorUtil.class) {
                if(null==threadPool) {
                    BasicThreadFactory threadFactory = new BasicThreadFactory.Builder().namingPattern("tuanyouThreadPool-").build();

                    threadPool = new ThreadPoolExecutor(10, 50, 3,
                            TimeUnit.SECONDS, new ArrayBlockingQueue<Runnable>(50),threadFactory,
                            new ThreadPoolExecutor.CallerRunsPolicy());
                }
            }
        }
    }
}

