package com.zxh.controller;

import com.alibaba.csp.sentinel.Entry;
import com.alibaba.csp.sentinel.EntryType;
import com.alibaba.csp.sentinel.SphU;
import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.alibaba.csp.sentinel.slots.block.RuleConstant;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRule;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRuleManager;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.zxh.feign.EchoFeignClient;
import com.zxh.vo.Student;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @Description:
 * @Author: zhengxinhui
 * @Date: 2021/4/9 17:44
 */
@RestController
@RefreshScope
@Slf4j
public class NacosConsumerFeignController {
    @Resource
    private EchoFeignClient echoService;

    @Value("${testStr}")
    private String testStr;

    @Autowired
    private Student student;
    @Autowired
    private RestTemplate restTemplate;

    @RequestMapping(value = "/echo/{message}")
    public String echo(@PathVariable("message") String message) {
/*        System.out.println("testStr="+testStr);
        System.out.println(student.getName());
        System.out.println(student.getAge());
        System.out.println(student.getAddress());*/
        String result = echoService.echo(message);
        log.info(result);
        return result;
/*        String object = restTemplate.getForObject("http://nacos-provider-app/echo/"+message, String.class);
        System.out.println(object);
        return object;*/
    }

    @RequestMapping(value = "/hysecho/{message}")

    @HystrixCommand(groupKey = "threadGroup", commandKey = "GetUserByIdCommand", fallbackMethod = "fallBack",
            commandProperties = {
                    @HystrixProperty(name = "execution.isolation..threadtimeoutInMilliseconds", value = "1000"),//指定多久超时，单位毫秒。超时进fallback
                    @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "3"),//判断熔断的最少请求数，默认是10；只有在一个统计窗口内处理的请求数量达到这个阈值，才会进行熔断与否的判断
                    @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "2"),//判断熔断的阈值，默认值50，表示在一个统计窗口内有50%的请求处理失败，会触发熔断
            },
            threadPoolProperties = {
                    @HystrixProperty(name = "coreSize", value = "5"),
                    @HystrixProperty(name = "maxQueueSize", value = "10"),
                    @HystrixProperty(name = "keepAliveTimeMinutes", value = "2"),
                    @HystrixProperty(name = "queueSizeRejectionThreshold", value = "15"),
                    @HystrixProperty(name = "metrics.rollingStats.numBuckets", value = "12"),
                    @HystrixProperty(name = "metrics.rollingStats.timeInMilliseconds", value = "1440")
            })
    public String hysecho(@PathVariable("message") String message) {
        String result = echoService.echo(message);
        log.info(result);
        return result;
    }

    public String fallBack(String message) {
        String result = "我被降级了";
        log.info(result);
        return result;
    }

    @RequestMapping(value = "/v2/{message}")
    @SentinelResource(value = "test",entryType = EntryType.IN, blockHandler = "blockHandler", fallback = "fallbackHandler")
    public String testSentinel(@PathVariable("message") String message) {
        try {
            Thread.sleep(1000);
            System.out.println("执行本地业务2");
        }catch (InterruptedException e) {
        }
        String result ="Ok2";
         //result = echoService.echo(message);
        return result;
    }
    /**
     * 初始化流控规则
     */

    @RequestMapping(value = "/v1/{message}")
    public String testSentinelV1(@PathVariable("message") String message) {

        Entry entity =null;
        //关联受保护的资源
        try {
            entity = SphU.entry("helloSentinelV1");
            try {
                Thread.sleep(1000);
                System.out.println("执行本地业务1");
            }catch (InterruptedException e) {
            }

            //结束执行自己的业务方法
        } catch (BlockException e) {
            log.info("testHelloSentinelV1方法被流控了");
            return "testHelloSentinelV1方法被流控了";
        }finally {
            if(entity!=null) {
                entity.exit();
            }
        }
        return "OK1";
    }

    public String blockHandler(String message) {
        String result="超过访问次数2次，被限流了";
        log.error(result);
        return result;
    }

    public String fallbackHandler(String message) {
        log.error("失败被降级了");
        return "----fallbackHandler";
    }
}