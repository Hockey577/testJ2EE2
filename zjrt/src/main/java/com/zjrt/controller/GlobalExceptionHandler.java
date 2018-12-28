package com.zjrt.controller;

import com.zjrt.dto.ResultInfo;
import com.zjrt.exception.BusinessException;
import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Apple on 2018/2/21.
 */
@RestControllerAdvice
public class GlobalExceptionHandler {
    private Logger logger = Logger.getLogger(GlobalExceptionHandler.class);
    /**
     * 业务异常统一处理
     * @param req
     * @param bx
     * @return
     */
    @ExceptionHandler(value = BusinessException.class)
    public ResultInfo notFoundErrorHandler(HttpServletRequest req, BusinessException bx) {
        logger.error(bx);
        ResultInfo info = new ResultInfo();
        info.fail();
        //TODO 扩展错误编码
        info.setState("50001");
        info.setMsg(bx.getMessage());
        return info;
    }

    /**
     * 位置异常统一处理
     * @param req
     * @param ex
     * @return
     */
    @ExceptionHandler(value = RuntimeException.class)
    public ResultInfo defaultErrorHandler(HttpServletRequest req, RuntimeException ex) {
        // 其他异常处理逻辑...
        logger.error(ex);
        ResultInfo info = new ResultInfo();
        info.fail();
        info.setMsg(ex.getMessage());
        return info;
    }
}
