package org.lmx.framework.okhttp;

import lombok.extern.slf4j.Slf4j;
import org.lmx.framework.okhttp.api.EthBlockScoutRpcClient;
import org.lmx.framework.okhttp.base.BaseRsp;
import org.lmx.framework.okhttp.base.Generator;
import org.lmx.framework.okhttp.dto.req.TxListReqDto;
import org.lmx.framework.okhttp.dto.resp.TransactionRespDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import java.net.Proxy;
import java.util.List;

/**
 * 功能描述：BlockScout客户端
 *
 * @program: comex-wallet-server
 * @author: LM.X
 * @create: 2020-05-08 11:28
 **/
@Slf4j
@Component
public class BlockScoutClient {
    private final EthBlockScoutRpcClient ethBlockScoutService;

    public BlockScoutClient(@Value("${api.eth.blockscout.url}") String url,
                            @Value("${api.eth.blockscout.proxy.host}") String host,
                            @Value("${api.eth.blockscout.proxy.port}") Integer port) {

        ethBlockScoutService = (StringUtils.isEmpty(host) || ObjectUtils.isEmpty(port)) ?
                new Generator().builder().createService(EthBlockScoutRpcClient.class, url) :
                new Generator().builder(Proxy.Type.HTTP, host, port).createService(EthBlockScoutRpcClient.class, url);
    }

    public List<TransactionRespDto> txList(TxListReqDto dto) {
        BaseRsp<List<TransactionRespDto>> baseRsp;
        baseRsp = Generator.executeSync(ethBlockScoutService.txList(
                dto.getAddress(), dto.getSort(), dto.getStartblock(), dto.getEndblock(), dto.getPage(),
                dto.getOffset(), dto.getFilterby(), dto.getStarttimestamp(), dto.getEndtimestamp()
        ));

        if (baseRsp != null && !baseRsp.isSuccess()) {
            log.error("通过地址获取交易：{}", baseRsp.getMessage());
            throw baseRsp.getException();
        }
        return baseRsp.getResult();
    }
}
