package com.zxh.site.service;

import com.github.pagehelper.PageInfo;
import com.zxh.site.entity.Site;
import com.zxh.site.vo.SiteVo;

import java.math.BigDecimal;
import java.util.HashMap;

/**
* <p>
* 服务实现类
* </p>
*
* @author 
* @since 2021-07-20
*/
public interface ISiteService {
    public PageInfo<SiteVo> querySites(int pageNum, int pageSize, HashMap params);
}