package com.zxh.site.vo;

import com.baomidou.mybatisplus.annotation.TableName;
import com.zxh.base.pojo.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;

/**
 * @author
 * @since 2021-07-20
 */
@Data
public class SiteVo{

    private static final long serialVersionUID = 1L;


    /**
     * 经度
     */
    private double lng;

    /**
     * 纬度
     */
    private double lat;

    /**
     * 楼盘对应字典code
     */
    private String buildingName;


    private double distance;
}
