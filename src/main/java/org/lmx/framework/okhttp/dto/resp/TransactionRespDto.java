package org.lmx.framework.okhttp.dto.resp;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.math.BigInteger;
import java.util.List;

/**
 * 功能描述：交易信息Vo
 *
 * @author: LM.X
 * @create: 2020-05-07 18:25
 **/
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class TransactionRespDto {

    private String blockHash;
    private Long blockNumber;
    private Long confirmations;
    private String contractAddress;
    private BigInteger cumulativeGasUsed;
    private String from;
    private BigInteger gas;
    private BigInteger gasPrice;
    private BigInteger gasUsed;
    private String hash;
    private String input;
    private String isError;
    private Long nonce;
    private Long timeStamp;
    private String to;
    private String transactionIndex;
    private String txreceipt_status;
    private BigInteger value;

    private BigInteger gasLimit;
    private List<Log> logs;
    private Boolean success;

    private Integer tokenDecimal;
    private Integer tokenID;
    private String tokenName;
    private String tokenSymbol;

    @Data
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Log {
        private String address;
        private String data;
        private List<String> topics;
    }
}
