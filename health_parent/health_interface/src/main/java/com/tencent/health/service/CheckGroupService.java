package com.tencent.health.service;

import com.tencent.health.entity.PageResult;
import com.tencent.health.entity.QueryPageBean;
import com.tencent.health.pojo.CheckGroup;

import java.util.List;

/**
 * @author 老王
 */
public interface CheckGroupService {
    /**
     * 添加检查组
     * @param checkGroup 检查组信息
     * @param checkitemIds 检查组包含的检查项
     * @return
     */
    void add(CheckGroup checkGroup, Integer[] checkitemIds);

    /**
     * 分页查询检查组
     * @param queryPageBean 客户端携带的参数
     * @return
     */
    PageResult<CheckGroup> findByPage(QueryPageBean queryPageBean);

    /**
     * 根据id查询检查组
     * @param id
     * @return
     */
    CheckGroup findById(Integer id);

    /**
     * 查询检查组里包含的检查项
     * @param id
     * @return
     */
    List<Integer> findCheckItems(Integer id);

    /**
     *  修改检查组信息
     * @param checkGroup 表单信息
     * @param checkitemIds 检查组包含的检查项
     * @return
     */
    void update(CheckGroup checkGroup, Integer[] checkitemIds);
}
