package com.zxh.util;

/**
 * @Description:
 * @Author: zhengxinhui
 * @Date: 2021/7/20 15:44
 */

@FunctionalInterface
public interface BeanCopyUtilCallBack<S, T> {
    void callBack(S var1, T var2);
}