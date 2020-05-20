package org.lmx.framework.base;

import lombok.Data;
import org.springframework.beans.BeanUtils;

/**
 * 功能描述: 基础业务API，封装统一返回结果集
 *
 * @author LM.X
 * @date 2020/4/24 16:48
 */
@Data
public class BaseApiService<T> {

    public BaseResponse<T> setResultError(Integer code, String msg) {
        return setResult(code, msg, null);
    }

    /**
     * 返回错误，可以传msg
     *
     * @param msg
     * @return
     */
    public BaseResponse<T> setResultError(String msg) {
        return setResult(500, msg, null);
    }


    /**
     * 通用封装 通用封装
     *
     * @param code
     * @param msg
     * @param data
     * @return
     */

    public BaseResponse<T> setResult(Integer code, String msg, T data) {
        return new BaseResponse<T>(code, msg, data);
    }

}
