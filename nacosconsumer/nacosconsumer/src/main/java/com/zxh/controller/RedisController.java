package com.zxh.controller;

import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RMap;
import org.redisson.api.RedissonClient;
import org.redisson.client.codec.IntegerCodec;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description:
 * @Author: zhengxinhui
 * @Date: 2021/4/27 11:10
 */
@Slf4j
//@RestController
//@RequestMapping("/redis")
public class RedisController {

    @Autowired
    private RedissonClient redissonClient;

    @Autowired
    private RedisTemplate redisTemplate;

    @RequestMapping(value = "/aa")
    public String echo() {
        redisTemplate.opsForValue().set("test","我是测试数据");
        System.out.println(redisTemplate.opsForValue().get("test"));
        redissonClient.getMap("maptest");
        RMap rMap=redissonClient.getMap("maptest");
        rMap.put("1111","我是111");
        String result=(String) redissonClient.getMap("maptest").get("1111");
        System.out.println(result);
        //redissonClient.getList();
        return result;
    }
}
