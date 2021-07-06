package com.zxh.service.impl;

import com.zxh.service.SendCouponService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 * @Description:
 * @Author: zhengxinhui
 * @Date: 2021/5/14 10:25
 */
@Service
@Slf4j
public class SendCouponServiceImpl implements SendCouponService {

    @Override
    @Async(value = "testExecutor")
    public void sendCoupon() {
        try {
            Thread.sleep(1000);
        }catch (Exception e) {
        }

        log.info("我是多线程执行的");
    }
}
