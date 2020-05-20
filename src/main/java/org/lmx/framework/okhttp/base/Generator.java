package org.lmx.framework.okhttp.base;

import com.fasterxml.jackson.core.JsonParseException;
import lombok.extern.slf4j.Slf4j;
import okhttp3.OkHttpClient;
import org.lmx.framework.exception.BusinessException;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.util.concurrent.TimeUnit;

/**
 * 功能描述: RPC客户端生成器
 *
 * @author LM.X
 * @date 2019/12/30 11:39
 */
@Slf4j
public class Generator {
    private static OkHttpClient okHttpClient;
    private static Retrofit.Builder builder;
    private static Retrofit retrofit;

    /**
     * 功能描述: 构建Http对象，并设置代理
     *
     * @param host_proxy 代理主机
     * @param port_proxy 代理端口
     * @return retrofit2.Retrofit.Builder
     * @author LM.X
     * @date 2020/5/8 11:51
     */
    public Generator builder(Proxy.Type type, String host_proxy, Integer port_proxy) {
        if (null == type) {
            type = Proxy.Type.HTTP;
        }

        okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(60, TimeUnit.MINUTES)
                .readTimeout(60, TimeUnit.MINUTES)
                .proxy(new Proxy(type, new InetSocketAddress(host_proxy, port_proxy)))
                .build();
        builder = new Retrofit.Builder().addConverterFactory(JacksonConverterFactory.create());
        return this;
    }

    /**
     * 功能描述: 构建Http对象
     *
     * @return retrofit2.Retrofit.Builder
     * @author LM.X
     * @date 2020/5/8 11:51
     */
    public Generator builder() {
        okHttpClient = new OkHttpClient.Builder().build();
        builder = new Retrofit.Builder().addConverterFactory(JacksonConverterFactory.create());
        return this;
    }

    public <S> S createService(Class<S> serviceClass, String baseUrl) {
        builder.baseUrl(baseUrl);
        builder.client(okHttpClient);
        builder.addConverterFactory(JacksonConverterFactory.create());
        retrofit = builder.build();
        return retrofit.create(serviceClass);
    }

    public static <T> T executeSync(Call<T> call) {
        try {
            Response<T> response = call.execute();
            if (response.isSuccessful()) {
                return response.body();
            } else {
                BaseRsp baseRsp = getApiError(response);
                throw new BusinessException(Integer.valueOf(baseRsp.getStatus()), baseRsp.getMessage());
            }
        } catch (IOException e) {
            throw new BusinessException(500, e.getMessage(), e);
        }
    }

    private static BaseRsp getApiError(Response<?> response) throws IOException {
        BaseRsp convert;
        try {
            convert = (BaseRsp) retrofit.responseBodyConverter(BaseRsp.class, new Annotation[0]).convert(response.errorBody());
        } catch (JsonParseException e) {
            convert = new BaseRsp();
            convert.setStatus(String.valueOf(response.code()));
            convert.setMessage(response.message());
        }
        return convert;
    }
}
