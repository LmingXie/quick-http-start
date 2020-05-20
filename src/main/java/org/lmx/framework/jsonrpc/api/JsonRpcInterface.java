package org.lmx.framework.jsonrpc.api;

/**
 * 功能描述：JsonRpc自定义接口
 *
 * @program: quick-http-start
 * @author: LM.X
 * @create: 2020-05-20 15:36
 **/
public interface JsonRpcInterface {

    /**
     * 功能描述: 获取ETH余额（方法名必须和API中定义的一致）
     *
     * @param address 地址
     * @param latest  最新
     * @return 返回16进制的余额（如果接口响应一个复杂实体，定义响应结构的DTO类，即可）
     * @author LM.X
     * @date 2020/5/20 16:14
     */
    String eth_getBalance(String address, String latest);
}
