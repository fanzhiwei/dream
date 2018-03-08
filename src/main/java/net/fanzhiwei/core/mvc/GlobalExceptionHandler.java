package net.fanzhiwei.core.mvc;
import javax.servlet.http.HttpServletRequest;

import org.springframework.core.annotation.Order;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
/**
 * @author fanzhiwei
 */
@RestController
@ControllerAdvice
public class GlobalExceptionHandler {
    /**
     * 默认的其他错误信息封装
     *
     * @param req
     * @param ex
     * @return
     */
    @ExceptionHandler(value = {Exception.class})
    @Order(Integer.MAX_VALUE)
    public ApiResult noHandlerFoundException(HttpServletRequest req, Exception ex) {
        // 这里可以对异常统一进行收集处理
        return ApiResult.createErrorInstance(900, String.format("接口未知异常,className:%s,exceptionMsg:%s",ex.getClass().getName(),ex.getMessage()));
    }

}
