package com.zxh.base;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.beust.jcommander.internal.Lists;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zxh.base.pojo.BaseEntity;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Optional;

/**
 * BaseService-Impl
 *
 * @version 1.0
 * @datetime 2021/4/27 1:32 下午
 */
public class BaseRepositoryImpl<M extends BaseMapper<T>, T extends BaseEntity> extends ServiceImpl<M, T> implements IBaseRepository<T> {

    @Override
    public Optional<T> selectByUid(String uid) {
        LambdaQueryWrapper<T> wrapper = new LambdaQueryWrapper<T>().eq(T::getUid, uid);
        return Optional.ofNullable(getOne(wrapper));
    }

    @Override
    public List<T> listByUid(List<String> uidList) {
        if(CollectionUtils.isEmpty(uidList)) {
            return Lists.newArrayList();
        }
        LambdaQueryWrapper<T> wrapper = new LambdaQueryWrapper<T>().in(T::getUid, uidList);
        return list(wrapper);
    }

    @Override
    public boolean updateByUid(T entity) {
        return update(entity, new LambdaQueryWrapper<T>().eq(T::getUid, entity.getUid()));
    }


    @Override
    public PageInfo<T> pageList(int pageNum, int pageSize, Wrapper<T> wrapper) {
        return PageHelper.startPage(pageNum, pageSize).doSelectPageInfo(() -> baseMapper.selectList(wrapper));
    }
}
