package org.lmx.framework.okhttp.api;

import org.lmx.framework.okhttp.base.BaseRsp;
import org.lmx.framework.okhttp.dto.resp.TransactionRespDto;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

import java.util.List;

/**
 * 功能描述：BlockScout区块浏览器客户端
 *
 * @program: comex-wallet-server
 * @author: LM.X
 * @create: 2020-05-07 18:05
 **/
public interface EthBlockScoutRpcClient {
    /**
     * 功能描述: 通过地址获取交易
     * <pre>
     *     此处不可使用@Body注解，因为offset参数与okhttp3的{@link okhttp3.RequestBody}中的offset参数冲突。
     *  okhttp3通过在{@link okhttp3.RequestBody}添加offset参数，实现分段提交。如果使用@Body进行入参则会默认多出一个 offset参数，
     *  并会将接口的offset覆盖，其默认值int类型0。
     *
     *  BlockScout浏览器提供的此接口在接受一个类型不同的值时会抛出："Something went wrong."的错误信息。
     *
     *  通过调试观察{@link okhttp3.RealCall}的getResponseWithInterceptorChain()方法中 originalRequest 值可发现，
     *  @Body入参时会添加额外参数（“offset”、“content”、“byteCount”）。
     *
     *  结论：在使用okhttp3进行Body入参时，参数中不可出现参数名为 “offset”、“content”、“byteCount” 的参数。
     *  调试结果过参考：{@link body调试结果呈现.png}
     * </pre>
     *
     * @param address        用于识别帐户的160位代码
     * @param sort           一个字符串，代表按块号方向的顺序。默认为降序。可用值：asc，desc
     * @param startblock     起始块号
     * @param endblock       结束块号
     * @param page           分页的页码
     * @param offset         分页时要返回的最大记录数
     * @param filterby       一个字符串，代表要过滤的字段。如果未给出，则返回匹配到，来自或合同地址的交易。可用值：to, from
     * @param starttimestamp 起始块时间戳
     * @param endtimestamp   表示结束块时间戳
     * @return 历史交易列表
     * @author LM.X
     * @date 2020/5/8 18:01
     */
    @GET("/eth/mainnet/api?module=account&action=txlist")
    Call<BaseRsp<List<TransactionRespDto>>> txList(@Query("address") String address,
                                                   @Query("sort") String sort,
                                                   @Query("startblock") Integer startblock,
                                                   @Query("endblock") Integer endblock,
                                                   @Query("page") Integer page,
                                                   @Query("offset") Integer offset,
                                                   @Query("filterby") String filterby,
                                                   @Query("starttimestamp") Long starttimestamp,
                                                   @Query("endtimestamp") Long endtimestamp);
}
