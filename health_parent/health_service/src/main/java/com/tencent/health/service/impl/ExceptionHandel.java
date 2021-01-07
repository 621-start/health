package com.tencent.health.service.impl;

import com.tencent.health.entity.Result;
import com.tencent.health.exception.MyException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


/**
 * 处理异常类
 *
 * @author 老王
 */
@RestControllerAdvice
public class ExceptionHandel {

    @ExceptionHandler(MyException.class)
    public Result MyException(MyException e) {
        return new Result(false, e.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public Result Exception(Exception e) {
        return new Result(false, "连接异常,请联系管理员");
    }
}
