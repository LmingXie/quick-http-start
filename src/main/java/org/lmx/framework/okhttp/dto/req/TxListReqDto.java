package org.lmx.framework.okhttp.dto.req;

import lombok.Data;

/**
 * 功能描述：通过地址获取交易
 *
 * @program: comex-wallet-server
 * @author: LM.X
 * @create: 2020-05-07 16:43
 **/
@Data
public class TxListReqDto {
    /**
     * 币种
     */
    private String coin;
    /**
     * 用于识别帐户的160位代码
     */
    private String address;

    /**
     * 一个字符串，代表按块号方向的顺序。默认为降序。可用值：asc，desc
     */
    private String sort;

    /**
     * 代表起始块号的非负整数。
     */
    private Integer startblock;

    /**
     * 一个非负整数，表示结束块号。
     */
    private Integer endblock;

    /**
     * 一个非负整数，表示要用于分页的页码。 “偏移”必须同时提供
     */
    private Integer page;

    /**
     * 一个非负整数，表示分页时要返回的最大记录数。 “页面”必须一起提供。
     */
    private Integer offset;

    /**
     * 一个字符串，代表要过滤的字段。如果未给出，则返回匹配到，来自或合同地址的交易。可用值：to, from
     */
    private String filterby;

    /**
     * 表示起始块时间戳。
     */
    private Long starttimestamp;

    /**
     * 表示结束块时间戳。
     */
    private Long endtimestamp;
}
