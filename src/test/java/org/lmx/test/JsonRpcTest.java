package org.lmx.test;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.lmx.framework.jsonrpc.api.JsonRpcInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.math.BigInteger;

/**
 * 功能描述：JsonRpc调用测试
 *
 * @program: quick-http-start
 * @author: LM.X
 * @create: 2020-05-20 16:15
 **/
@Slf4j
@SpringBootTest
@RunWith(SpringRunner.class)
public class JsonRpcTest {
    @Autowired
    private JsonRpcInterface jsonRpcInterface;

    @Test
    public void test() {
        String address = "0xc94770007dda54cF92009BFF0dE90c06F603a09f";
        // 如果接口响应一个复杂实体，定义响应结构的DTO类，即可。入参，同理。
        String balance = jsonRpcInterface.eth_getBalance(address, "latest");
        log.info("原始16进制余额：{}", balance);

        String[] split = balance.split("0x");
        balance = split[1];
        BigInteger balanceBigInteger = new BigInteger(balance, 16);

        String balanceWei = balanceBigInteger.toString(10);
        log.info("去掉前缀余额是：{} Wei", balanceWei);

        BigDecimal balanceETH = new BigDecimal(balanceWei).divide(BigDecimal.TEN.pow(18));
        log.info("转换为余额是：{} ETH", balanceETH);
    }
}
