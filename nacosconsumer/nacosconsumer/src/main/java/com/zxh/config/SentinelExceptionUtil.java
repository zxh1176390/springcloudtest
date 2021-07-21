/*
 * Copyright 1999-2018 Alibaba Group Holding Ltd.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.zxh.config;

import com.alibaba.csp.sentinel.slots.block.AbstractRule;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Sentinel限流异常处理类
 */
public final class SentinelExceptionUtil {
    private static Logger logger = LoggerFactory.getLogger(SentinelExceptionUtil.class);

    public static String handleException(BlockException ex) {
        try {
            AbstractRule rule = ex.getRule();
            logger.error("Sentinel 异常，ruleLimitApp=" + ex.getRuleLimitApp() + " , rule -> " + JSONObject.toJSONString(rule), ex);
        } catch (Exception e) {
            logger.error("Sentinel 异常处理错误", e);
        }
        return "Sentinel 异常，ruleLimitApp=" + ex.getRuleLimitApp();

    }

}
