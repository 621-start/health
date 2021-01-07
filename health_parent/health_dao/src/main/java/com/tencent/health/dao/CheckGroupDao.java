package com.tencent.health.dao;

import com.github.pagehelper.Page;
import com.tencent.health.entity.QueryPageBean;
import com.tencent.health.pojo.CheckGroup;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CheckGroupDao {
    /**
     * 添加检查组
     * @param checkGroup 检查组信息
     */
    void add(CheckGroup checkGroup);

    /**
     * 给检查组添加检查项
     * @param checkGroupId 检查组id
     * @param checkitemId 检查项id
     */
    void addCheckGroupCheckItem(@Param("checkGroupId") Integer checkGroupId, @Param("checkitemId") Integer checkitemId);

    /**
     * 分页查询检查组
     *
     * @param queryString 客户端携带的参数
     * @return
     */
    Page<CheckGroup> findByPage(String queryString);

    /**
     * 根据id查询检查组
     *
     * @param id
     * @return
     */
    CheckGroup findById(Integer id);

    /**
     * 查询检查组里包含的检查项
     *
     * @param id
     * @return
     */
    List<Integer> findCheckItems(Integer id);

    /**
     * 修改检查组表单项
     * @param checkGroup
     */
    void update(CheckGroup checkGroup);

    /**
     * 删除检查组旧的关系
     * @param id
     */
    void deletecheckItemIds(Integer id);
}
