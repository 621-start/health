package com.tencent.health.controller;


import com.alibaba.dubbo.config.annotation.Reference;
import com.tencent.health.constant.MessageConstant;
import com.tencent.health.entity.PageResult;
import com.tencent.health.entity.QueryPageBean;
import com.tencent.health.entity.Result;
import com.tencent.health.pojo.CheckGroup;
import com.tencent.health.service.CheckGroupService;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/checkGroup")
public class CheckGroupController {

    @Reference
    private CheckGroupService checkGroupService;

    /**
     * 添加检查组
     * @param checkGroup 检查组信息
     * @param checkitemIds 检查组包含的检查项
     * @return
     */
    @PostMapping("/add")
    public Result add(@RequestBody CheckGroup checkGroup , Integer[] checkitemIds){
        checkGroupService.add(checkGroup , checkitemIds);
        return new Result(true, MessageConstant.ADD_CHECKGROUP_SUCCESS);
    }

    /**
     * 分页查询检查组
     * @param queryPageBean 客户端携带的参数
     * @return
     */
    @PostMapping("/findPage")
    public Result findByPage(@RequestBody QueryPageBean queryPageBean){
        PageResult<CheckGroup> pageResult = checkGroupService.findByPage(queryPageBean);
        return new Result(true,MessageConstant.QUERY_CHECKGROUP_SUCCESS,pageResult);
    }

    /**
     * 根据id查询检查组
     * @param id
     * @return
     */
    @GetMapping("/findById")
    public Result findByid(Integer id){
        CheckGroup checkGroup = checkGroupService.findById(id);
        return new Result(true,MessageConstant.QUERY_CHECKGROUP_SUCCESS,checkGroup);
    }

    /**
     * 查询检查组里包含的检查项
     * @param id
     * @return
     */
    @GetMapping("/findCheckItems")
    public Result findCheckItems(Integer id){
        List<Integer>  list = checkGroupService.findCheckItems(id);
        return new Result(true,MessageConstant.QUERY_CHECKGROUP_SUCCESS,list);
    }

    /**
     *  修改检查组信息
     * @param checkGroup 表单信息
     * @param checkitemIds 检查组包含的检查项
     * @return
     */
    @PostMapping("/update")
    public Result update(@RequestBody CheckGroup checkGroup,Integer[] checkitemIds){
        checkGroupService.update(checkGroup,checkitemIds);
        return new Result(true,MessageConstant.EDIT_CHECKGROUP_SUCCESS);
    }
}
