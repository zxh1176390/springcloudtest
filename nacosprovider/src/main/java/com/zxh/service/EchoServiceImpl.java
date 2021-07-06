package com.zxh.service;

import com.zxh.feignapi.EchoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * @Description:
 * @Author: zhengxinhui
 * @Date: 2021/4/12 16:49
 */
@Service
@Slf4j
public class EchoServiceImpl implements EchoService {

    @Value("${server.port}")
    private String port;

    @Override
    public String echo(String message) {
        String result="接收message:" + message + "，provider端口["+port+"] 返回message: 我收到了";
        log.info(result);
        try {
            Thread.sleep(6000);
        }catch (InterruptedException e) {
            log.error(e.getMessage());
        }
        return result;
    }
}
