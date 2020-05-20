package org.lmx.framework.okhttp.base;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import org.lmx.framework.exception.BusinessException;
import org.springframework.util.StringUtils;

/**
 * 功能描述：通用响应类。
 *
 * <pre>
 *     OkHttp 具体需根据Http接口响应的数据体格式进行构建
 * </pre>
 *
 * @author LM.X
 * @date 2020/5/20 14:54
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class BaseRsp<T> {
    private T result;
    private String message;
    private String status;

    public boolean isSuccess() {
        return StringUtils.isEmpty(status) ? Boolean.FALSE : status.equals("1");
    }

    public BusinessException getException() {
        return new BusinessException(message);
    }
}
