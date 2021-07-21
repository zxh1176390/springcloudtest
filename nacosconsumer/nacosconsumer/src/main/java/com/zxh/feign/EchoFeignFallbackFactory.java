package com.zxh.feign;

import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @Description:
 * @Author: zhengxinhui
 * @Date: 2021/4/13 11:17
 */
@Component
@Slf4j
public class EchoFeignFallbackFactory implements FallbackFactory<EchoFeignClient> {
    @Override
    public EchoFeignClient create(Throwable throwable) {

        return new EchoFeignClient() {
            @Override
            public String echo(@PathVariable("message") String message) {
                String result = "EchoFeignFallbackFactory 降级值";
                log.error(result);
                return result;
            }
        };
    }
}
