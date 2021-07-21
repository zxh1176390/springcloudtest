package com.zxh.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.Supplier;

/**
 * @Description:
 * @Author: zhengxinhui
 * @Date: 2021/7/20 15:43
 */
public class BeanCopyUtil extends BeanUtils {
    private static final Logger logger = LoggerFactory.getLogger(BeanCopyUtil.class);

    public BeanCopyUtil() {
    }

    public static <S, T> List<T> copyListProperties(List<S> sources, Supplier<T> target) {
        return copyListProperties(sources, target, (BeanCopyUtilCallBack)null);
    }

    public static <S, T> List<T> copyListProperties(List<S> sources, Supplier<T> target, BeanCopyUtilCallBack<S, T> callBack) {
        if (CollectionUtils.isEmpty(sources)) {
            return null;
        } else {
            List<T> list = new ArrayList(sources.size());
            sources.stream().forEach((s) -> {
                T t = target.get();
                copyProperties(s, t);
                list.add(t);
                if (Objects.nonNull(callBack)) {
                    callBack.callBack(s, t);
                }

            });
            return list;
        }
    }

}
