package com.zxh.site.repository.impl;

import com.zxh.site.entity.Site;
import com.zxh.site.mapper.SiteMapper;
import com.zxh.site.repository.SiteRepository;
import com.zxh.base.BaseRepositoryImpl;
import org.springframework.stereotype.Repository;

/**
* <p>
* Repository服务实现类
* </p>
*
* @author 
* @since 2021-07-20
*/
@Repository
public class SiteRepositoryImpl extends BaseRepositoryImpl<SiteMapper, Site> implements SiteRepository {

}
