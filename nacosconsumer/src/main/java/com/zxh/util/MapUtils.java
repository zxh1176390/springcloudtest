package com.zxh.util;

import java.util.Map;

/**
 * @Description:
 * @Author: zhengxinhui
 * @Date: 2021/4/30 17:18
 */
public class MapUtils {

    public static boolean isEmpty(Map map){
        if(map==null||map.isEmpty()){
            return true;
        }
        return false;
    }
}
