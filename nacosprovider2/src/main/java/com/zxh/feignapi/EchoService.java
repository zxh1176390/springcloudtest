package com.zxh.feignapi;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description:
 * @Author: zhengxinhui
 * @Date: 2021/4/9 17:44
 */
@RestController
public interface EchoService {

    @RequestMapping(value = "/echo/{message}")
    String echo(@PathVariable("message") String message);

}
