package com.zxh.base;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.github.pagehelper.PageInfo;

import java.util.List;
import java.util.Optional;

public interface IBaseRepository<T> extends IService<T> {

    /**
     * 根据UID查询 单条数据
     * @param uid
     * @return
     */
    Optional<T> selectByUid(String uid);

    /**
     * 根据UID(s)列表查询
     * @param uidList
     * @return
     */
    List<T> listByUid(List<String> uidList);

    /**
     * 根据UID更新
     * @param entity
     * @return
     */
    boolean updateByUid(T entity);


    /**
     * 分页查询
     * @param pageNum
     * @param pageSize
     * @param wrapper
     * @return
     */
    PageInfo<T> pageList(int pageNum, int pageSize, Wrapper<T> wrapper);
}
