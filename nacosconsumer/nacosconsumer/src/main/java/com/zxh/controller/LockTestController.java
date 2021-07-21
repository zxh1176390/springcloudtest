package com.zxh.controller;

/**
 * @Description:
 * @Author: zhengxinhui
 * @Date: 2021/4/27 11:08
 */

import com.zxh.util.DistributedRedisLock;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 分布式Redis锁测试controller
 */
@Slf4j
@RestController
@RequestMapping("/lock")
public class LockTestController {

    private final String LOCK = "LOCK";

    //@Autowired
    private DistributedRedisLock distributedRedisLock;

    // 测试不释放锁
    @GetMapping("/testLock")
    public void testLock() {
        for (int i = 0; i < 5; i++) {
            new Thread(() -> {
                distributedRedisLock.lock(LOCK);
            }).start();
        }
    }

    // 实际业务开发使用分布式锁的方式
    @PostMapping
    public void post() {
        try {
            if (distributedRedisLock.lock(LOCK)) {
                // 业务逻辑
                log.info("开始业务逻辑");
            } else {
                // 处理获取锁失败的逻辑
                log.info("获取锁失败");
            }
        } catch (Exception e) {
            log.error("处理异常：", e);
        } finally {
            distributedRedisLock.unlock(LOCK);
        }
    }
}
