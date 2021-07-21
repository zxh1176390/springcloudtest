package com.zxh.site.entity;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.TableName;
import com.zxh.base.pojo.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 *
 * @author 
 * @since 2021-07-20
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("saas_site")
public class Site extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 工地编号
     */
    private String gongdiCode;

    /**
     * 工地标题
     */
    private String gongdiTitle;

    /**
     * 楼盘/楼宇名称
     */
    private String buildingName;

    /**
     * 楼宇面积，单位㎡
     */
    private BigDecimal buildingArea;

    /**
     * 户型
     */
    private String houseType;

    /**
     * 装修费（单位：万元）
     */
    private BigDecimal renovationCosts;

    /**
     * 装修风格
     */
    private String houseStyle;

    /**
     * 封面图
     */
    private String coverImg;

    /**
     * 工地说明
     */
    private String gongdiDescription;

    /**
     * 阶段/状态
     */
    private String gongdiStage;

    /**
     * 来源：0-新建，1-项目
     */
    private Integer gongdiFromType;

    /**
     * 若来源是项目，则存project_uid
     */
    private String gongdiFromCode;

    /**
     * 工地排序：0-置顶
     */
    private Integer gongdiOrder;

    /**
     * 状态：0-启用，1-停用
     */
    private Integer gongdiStatus;

    /**
     * 参观数
     */
    private Integer visitNum;

    /**
     * SEO头部的关键字
     */
    private String headKeywords;

    /**
     * SEO头部的描述
     */
    private String headDescription;

    /**
     * vr
     */
    private String vrLink;

    /**
     * 省市区编号
     */
    private String areaCode;

    /**
     * 地址
     */
    private String addr;

    /**
     * 经度
     */
    private BigDecimal lng;

    /**
     * 纬度
     */
    private BigDecimal lat;

    /**
     * 楼盘对应字典code
     */
    private String buildingCode;


}
