package com.tencent.health.dao;

import com.github.pagehelper.Page;
import com.tencent.health.pojo.CheckItem;

import java.util.List;

/**
 * @author 老王
 */
public interface CheckItemDao {
    /**
     * 查找所有检查项
     * @return
     */
    List<CheckItem> findAll();

    /**
     * 添加检查项
     * @param checkItem
     */
    void add(CheckItem checkItem);

    /**
     * 添加检查项
     * @param queryString
     * @return
     */
    Page<CheckItem> findByCondtion(String queryString);

    /**
     * 根據id找检查项
     *
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
     * 查找要删除检查项
     * @param id
     * @return
     */
    int findCount(int id);

    /**
     * 删除检查项
     * @param id
     * @return
     */
    void deleteById(int id);

}
