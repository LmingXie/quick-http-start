package org.lmx.framework.exception;

import lombok.extern.slf4j.Slf4j;
import org.lmx.framework.base.BaseApiService;
import org.lmx.framework.base.BaseResponse;
import org.springframework.beans.TypeMismatchException;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 功能描述: 统一异常处理
 *
 * @author LM.X
 * @date 2020/5/6 12:14
 */
@Slf4j
@ControllerAdvice
public class GlobalControllerAdvice extends BaseApiService {
    /**
     * 功能描述: 全局业务异常处理
     *
     * @param ex
     * @return 响应业务异常信息
     * @author LM.X
     * @date 2020/5/6 13:01
     */
    @ResponseBody
    @ExceptionHandler(value = BusinessException.class)
    public BaseResponse businessHandler(BusinessException ex) {
        log.error("BusinessException:", ex);
        if (ex.getCode() == 500) {
            log.error("uncaught Exception:", ex);
            return setResultError("系统异常，请联系管理员");
        }
        return setResultError(ex.getCode(), ex.getMessage());
    }

    /**
     * 功能描述: 400系列异常捕获
     *
     * @param ex
     * @return 响应业务异常信息
     * @author LM.X
     * @date 2020/5/6 13:01
     */
    @ResponseBody
    @ExceptionHandler({MissingServletRequestParameterException.class, HttpMessageNotReadableException.class, TypeMismatchException.class})
    public BaseResponse requestHandler(Throwable ex) {
        log.error("400:", ex);
        return setResultError("接口请求异常");
    }

    /**
     * 功能描述: 405错误
     *
     * @param ex
     * @return BaseResponse
     * @author LM.X
     * @date 2020/5/7 9:40
     */
    @ResponseBody
    @ExceptionHandler({HttpRequestMethodNotSupportedException.class})
    public BaseResponse request405(HttpRequestMethodNotSupportedException ex) {
        log.error("405:", ex);
        return setResultError(405, ex.getMessage());
    }

    /**
     * 功能描述: 406错误
     *
     * @param ex
     * @return com.comex.base.BaseResponse
     * @author LM.X
     * @date 2020/5/7 9:40
     */
    @ResponseBody
    @ExceptionHandler({HttpMediaTypeNotAcceptableException.class})
    public BaseResponse request406(HttpMediaTypeNotAcceptableException ex) {
        log.error("406:", ex);
        return setResultError(406, ex.getMessage());
    }

    /**
     * 功能描述: 全局运行时异常处理
     *
     * @param e 异常信息
     * @return 响应系统错误
     * @author LM.X
     * @date 2020/5/6 13:01
     */
    @ResponseBody
    @ExceptionHandler(value = Exception.class)
    public BaseResponse errorHandler(Exception e) {
        log.error("uncaught Exception:", e);
        return setResultError("系统异常，请联系管理员");
    }
}
