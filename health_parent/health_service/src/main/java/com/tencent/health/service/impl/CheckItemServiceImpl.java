package com.tencent.health.service.impl;

import com.alibaba.druid.util.StringUtils;
import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.tencent.health.constant.MessageConstant;
import com.tencent.health.dao.CheckItemDao;
import com.tencent.health.entity.PageResult;
import com.tencent.health.entity.QueryPageBean;
import com.tencent.health.exception.MyException;
import com.tencent.health.pojo.CheckItem;
import com.tencent.health.service.CheckItemService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Service
public class CheckItemServiceImpl implements CheckItemService {

    @Autowired
    private CheckItemDao checkItemDao;

    /**
     * 查询所有
     * @return
     */
    @Override
    public List<CheckItem> findAll() {
        return checkItemDao.findAll();
    }

    /**
     * 添加检查项
     * @param item
     */
    @Override
    public void add(CheckItem item) {
        checkItemDao.add(item);
    }

    /**
     * 分页查找检查项
     * @param queryPageBean
     * @return
     */
    @Override
    public PageResult<CheckItem> findPage(QueryPageBean queryPageBean) {
        // 开启分页插件，并写入参数，底层会为我们拼接字符串条件，并且开启分页
        PageHelper.startPage(queryPageBean.getCurrentPage(), queryPageBean.getPageSize());

        if (!StringUtils.isEmpty(queryPageBean.getQueryString())) {
            queryPageBean.setQueryString("%" + queryPageBean.getQueryString() + "%");
        }

        // 拼接好条件调用dao查找，返回的数据用Page接收，分页插件的实体类，但我们不能返回
        Page<CheckItem> page = checkItemDao.findByCondtion(queryPageBean.getQueryString());
        // 在实体类获取需要的参数，封装进我们准备好的实体类，传入web层
        PageResult<CheckItem> pageResult = new PageResult<>(page.getTotal(), page.getResult());
        return pageResult;
    }

    /**
     * 根據id找检查项
     *
     * @param id
     * @return
     */
    @Override
    public CheckItem findById(Integer id) {

        return checkItemDao.findById(id);
    }

    /**
     * 修改表单项
     *
     * @param checkItem
     */
    @Override
    public void update(CheckItem checkItem) {
        checkItemDao.update(checkItem);
    }

    /**
     * 删除检查项
     * @param id
     * @return
     */
    @Override
    public void deleteById(int id) {
        int count = checkItemDao.findCount(id);

        if (count > 0){
            throw new MyException(MessageConstant.DELETE_CHECKGROUP_FAIL);
        }
        checkItemDao.deleteById(id);
    }
}
