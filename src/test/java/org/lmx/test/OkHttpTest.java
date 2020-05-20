package org.lmx.test;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.lmx.framework.okhttp.BlockScoutClient;
import org.lmx.framework.okhttp.dto.req.TxListReqDto;
import org.lmx.framework.okhttp.dto.resp.TransactionRespDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * 功能描述：OkHttp测试
 *
 * @program: quick-http-start
 * @author: LM.X
 * @create: 2020-05-20 14:57
 **/
@Slf4j
@SpringBootTest
@RunWith(SpringRunner.class)
public class OkHttpTest {

    @Autowired
    private BlockScoutClient blockScoutClient;

    /**
     * 这里我们选用一个境外以太坊索引器网站BlockScout的Http接口进行测试。
     */
    @Test
    public void test() {
        TxListReqDto reqDto = new TxListReqDto();
        reqDto.setAddress("0x761cfd69d65a9616d7e36902a6b486cd7cb0d843");
        List<TransactionRespDto> transactionRespDtos = blockScoutClient.txList(reqDto);

        log.info("----->>>{}", JSON.toJSONString(transactionRespDtos));

    }
}
