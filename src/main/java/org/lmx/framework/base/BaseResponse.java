package org.lmx.framework.base;

import lombok.Data;

/**
 * 功能描述: 接口统一返回码
 *
 * @author LM.X
 * @date 2020/4/24 17:07
 */
@Data
public class BaseResponse<T> {

    /**
     * 返回码
     */
    private Integer code;
    /**
     * 消息
     */
    private String msg;
    /**
     * 返回
     */
    private T data;
    // 分页

    public BaseResponse() {
    }

    public BaseResponse(Integer code, String msg, T data) {
        super();
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public BaseResponse setData(T data) {
        this.data = data;
        return this;
    }
}
