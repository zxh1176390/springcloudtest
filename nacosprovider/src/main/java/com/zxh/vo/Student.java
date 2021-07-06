package com.zxh.vo;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * @Description:
 * @Author: zhengxinhui
 * @Date: 2021/4/12 15:03
 */
@ConfigurationProperties(prefix = "stu")
@Component
@Data
public class Student {

    private String name;
    private Integer age;
    private String address;

}
