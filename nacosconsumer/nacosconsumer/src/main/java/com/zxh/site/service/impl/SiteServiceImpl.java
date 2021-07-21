package com.zxh.site.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zxh.site.entity.Site;
import com.zxh.site.mapper.SiteMapper;
import com.zxh.site.service.ISiteService;
import com.zxh.site.repository.SiteRepository;
import com.zxh.site.vo.SiteVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

/**
* <p>
* 服务实现类
* </p>
*
* @author 
* @since 2021-07-20
*/
@Service
public class SiteServiceImpl implements ISiteService {
    @Autowired
    private SiteRepository siteRepository;
    @Autowired
    private SiteMapper siteMapper;
    @Override
    public PageInfo<SiteVo> querySites(int pageNum, int pageSize,HashMap params) {
        PageHelper.startPage(pageNum, pageSize);
        List<SiteVo> list = siteMapper.queryByLngLat(params);
        PageInfo<SiteVo> pageInfo = new PageInfo<>(list);
        return pageInfo;
    }
}