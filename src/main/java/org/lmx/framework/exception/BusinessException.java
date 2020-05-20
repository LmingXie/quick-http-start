package org.lmx.framework.exception;

import lombok.Data;

/**
 * 功能描述: 业务异常
 *
 * @author LM.X
 * @date 2020/5/6 12:56
 */
@Data
public class BusinessException extends RuntimeException {

    public static final String UNKNOWN_EXCEPTION = "unknown.exception";
    public static final String SERVICE_FAIL = "service.fail";
    public static final String NETWORK_EXCEPTION = "network.exception";
    public static final String TIMEOUT_EXCEPTION = "timeout.exception";

    private Integer code;

    public BusinessException() {
        super();
    }

    public BusinessException(String message, Throwable cause) {
        super(message, cause);
    }

    public BusinessException(String message) {
        super(message);
    }

    public BusinessException(Throwable cause) {
        super(cause);
    }

    public BusinessException(Integer code, String message, Throwable cause) {
        super(message, cause);
        this.code = code;
    }

    public BusinessException(Integer code, String message) {
        super(message);
        this.code = code;
    }
}