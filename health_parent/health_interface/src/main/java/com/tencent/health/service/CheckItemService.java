package com.tencent.health.service;

import com.tencent.health.entity.PageResult;
import com.tencent.health.entity.QueryPageBean;
import com.tencent.health.exception.MyException;
import com.tencent.health.pojo.CheckItem;

import java.util.List;

/**
 * @author 老王
 */
public interface CheckItemService {
    /**
     * 查找所有检查项
     * @return
     */
    List<CheckItem> findAll();

    /**
     * 添加检查项
     * @param item
     */
    void add(CheckItem item);

    /**
     * 分页查找检查项
     * @param queryPageBean
     * @return
     */
    PageResult<CheckItem> findPage(QueryPageBean queryPageBean);

    /**
     * 根據id找检查项
     * @param id
     * @return
     */
    CheckItem findById(Integer id);

    /**
     * 修改表单项
     * @param checkItem
     */
    void update(CheckItem checkItem);

    /**
     * 删除检查项
     * @param id
     * @return
     */
    void deleteById(int id)throws MyException;
}
