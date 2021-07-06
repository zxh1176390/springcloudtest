package com.zxh.config;

import com.alibaba.csp.sentinel.annotation.aspectj.SentinelResourceAspect;
import com.alibaba.csp.sentinel.slots.block.RuleConstant;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRule;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRuleManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import java.util.List;

/**
 * @Description:
 * @Author: zhengxinhui
 * @Date: 2021/7/6 18:02
 */
@Configuration
public class NacosConfig {

    @PostConstruct
    public void init() {
        List<FlowRule> flowRules = FlowRuleManager.getRules();
        //创建流控规则对象
        FlowRule flowRule = new FlowRule();
        //设置流控规则 QPS
        flowRule.setGrade(RuleConstant.FLOW_GRADE_QPS);
        //设置受保护的资源
        flowRule.setResource("helloSentinelV1");
        flowRule.setStrategy(0);
        //设置受保护的资源的阈值
        flowRule.setCount(1);
        flowRules.add(flowRule);
        //加载配置好的规则
        FlowRuleManager.loadRules(flowRules);

    }
    @Bean
    public SentinelResourceAspect sentinelResourceAspect() {
        return new SentinelResourceAspect();
    }
}
