package com.tencent.health.service.impl;

import com.alibaba.druid.util.StringUtils;
import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.tencent.health.dao.CheckGroupDao;
import com.tencent.health.entity.PageResult;
import com.tencent.health.entity.QueryPageBean;
import com.tencent.health.pojo.CheckGroup;
import com.tencent.health.service.CheckGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service(interfaceClass = CheckGroupService.class)
public class CheckGroupServiceImpl implements CheckGroupService {

    @Autowired
    private CheckGroupDao checkGroupDao;
    /**
     * 添加检查组
     *
     * @param checkGroup   检查组信息
     * @param checkitemIds 检查组包含的检查项
     * @return
     */
    @Override
    public void add(CheckGroup checkGroup, Integer[] checkitemIds) {
        checkGroupDao.add(checkGroup);

        Integer checkGroupId = checkGroup.getId();
        if (checkitemIds != null) {
            for (Integer checkitemId : checkitemIds) {
                checkGroupDao.addCheckGroupCheckItem(checkGroupId,checkitemId);
            }
        }

    }
    /**
     * 分页查询检查组
     *
     * @param queryPageBean 客户端携带的参数
     * @return
     */
    @Override
    public PageResult<CheckGroup> findByPage(QueryPageBean queryPageBean) {
        PageHelper.startPage(queryPageBean.getCurrentPage(),queryPageBean.getPageSize());

        if (!StringUtils.isEmpty(queryPageBean.getQueryString())) {
            queryPageBean.setQueryString("%"+queryPageBean.getQueryString()+"%");
        }
        Page<CheckGroup> page = checkGroupDao.findByPage(queryPageBean.getQueryString());
        PageResult<CheckGroup> result = new PageResult<>(page.getTotal(), page.getResult());
        return result;
    }

    /**
     * 根据id查询检查组
     *
     * @param id
     * @return
     */
    @Override
    public CheckGroup findById(Integer id) {

        return checkGroupDao.findById(id);
    }

    /**
     * 查询检查组里包含的检查项
     *
     * @param id
     * @return
     */
    @Override
    public List<Integer> findCheckItems(Integer id) {
        List<Integer> items = checkGroupDao.findCheckItems(id);
        return items;
    }

    /**
     * 修改检查组信息
     *
     * @param checkGroup   表单信息
     * @param checkitemIds 检查组包含的检查项
     * @return
     */
    @Override
    @Transactional(rollbackFor = {Exception.class})
    public void update(CheckGroup checkGroup, Integer[] checkitemIds) {
        // 先修改表单信息
        checkGroupDao.update(checkGroup);
        // 删除旧关系
        checkGroupDao.deletecheckItemIds(checkGroup.getId());
        // 建立新关系
        for (Integer checkitemId : checkitemIds) {
            checkGroupDao.addCheckGroupCheckItem(checkGroup.getId(),checkitemId);
        }

    }
}
