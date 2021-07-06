package com.zxh.controller;

import com.zxh.vo.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description:
 * @Author: zhengxinhui
 * @Date: 2021/4/9 17:44
 */
@RestController
@RefreshScope
public class NacosConsumerFeignController {
/*    @Autowired
    private EchoService echoService;*/

    @Value("${testStr}")
    private String  testStr;

    @Autowired
    private Student student;

    @RequestMapping(value = "/echo/hi")
    public String echo() {
        System.out.println("testStr="+testStr);
        System.out.println(student.getName());
        System.out.println(student.getAge());
        System.out.println(student.getAddress());
        return testStr;
    }
}
