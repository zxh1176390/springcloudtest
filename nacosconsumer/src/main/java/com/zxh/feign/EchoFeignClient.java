package com.zxh.feign;

import com.alibaba.csp.sentinel.EntryType;
import com.alibaba.csp.sentinel.annotation.SentinelResource;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description:
 * @Author: zhengxinhui
 * @Date: 2021/4/9 17:44
 */
@FeignClient(value = "nacos-provider-app"/*fallback = EchoFeignClient.DefaultFallback.class,fallbackFactory = EchoFeignFallbackFactory.class*/)
public interface EchoFeignClient {

    @RequestMapping(value = "/echo/{message}")
    String echo(@PathVariable("message") String message);

    @Component
    class DefaultFallback implements EchoFeignClient{
        @Override
        public String echo(String message) {
            String result="fallback echo() 方法降级值";
            System.out.println(result);
            return result;
        }
    }

}
