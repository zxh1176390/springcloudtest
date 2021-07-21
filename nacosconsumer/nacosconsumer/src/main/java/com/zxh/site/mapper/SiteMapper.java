package com.zxh.site.mapper;

import com.zxh.site.entity.Site;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zxh.site.vo.SiteVo;

import java.util.HashMap;
import java.util.List;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author 
 * @since 2021-07-20
 */
public interface SiteMapper extends BaseMapper<Site> {

    public List<SiteVo> queryByLngLat(HashMap hashMap);
}
