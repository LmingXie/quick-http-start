package org.lmx.framework.jsonrpc.config;


import lombok.extern.slf4j.Slf4j;
import org.lmx.framework.exception.BusinessException;
import org.lmx.framework.jsonrpc.api.JsonRpcInterface;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.net.URL;

@Slf4j
@Component
public class RpcClientConfiguration {

    @Value("${api.eth.infura.rpcUrl}")
    private String rpcUrl;

    @Value("${api.eth.infura.rpcUser}")
    private String rpcUser;

    @Value("${api.eth.infura.rpcPassword}")
    private String rpcPassword;

    /**
     * 功能描述: 客户端工厂方法
     *
     * @return T
     * @author LM.X
     * @date 2019/12/4 13:32
     */
    private <T> T getClient(Class<T> T) {
        try {
            RpcClientFactory clientFactory = new RpcClientFactory(new URL(rpcUrl), rpcUser, rpcPassword);
            return clientFactory.getClient(T);
        } catch (Exception e) {
            log.error("获取客户端时发生异常 error:{}", e);
            throw new BusinessException(e);
        }
    }

    @Bean
    public JsonRpcInterface getJsonRpcInterface() {
        return getClient(JsonRpcInterface.class);
    }
}
