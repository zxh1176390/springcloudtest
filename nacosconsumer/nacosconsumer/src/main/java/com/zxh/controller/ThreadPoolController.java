package com.zxh.controller;

import com.zxh.service.SendCouponService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description:
 * @Author: zhengxinhui
 * @Date: 2021/5/14 10:15
 */
@RestController
public class ThreadPoolController {

    @Autowired
    private SendCouponService sendCouponService;

    @RequestMapping(value = "/thread")
    public String thread() {
        for (int i = 0; i < 1000;i++){
            sendCouponService.sendCoupon();
        }
        return "success";
    }
}
