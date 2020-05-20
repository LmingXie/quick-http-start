package org.lmx.framework.jsonrpc.config;

import com.googlecode.jsonrpc4j.JsonRpcHttpClient;
import com.googlecode.jsonrpc4j.ProxyUtil;
import org.springframework.util.Base64Utils;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * 功能描述: JsonRPC工厂
 *
 * @author LM.X
 * @return
 * @date 2019/12/4 14:29
 */
public class RpcClientFactory {
    private static String OS = System.getProperty("os.name").toLowerCase();

    private static String convertStream(java.io.InputStream is) {
        Scanner s = new Scanner(is).useDelimiter("\\A");
        return s.hasNext() ? s.next() : "";
    }

    private static boolean isWindows() {
        return (OS.indexOf("win") >= 0);
    }

    private final JsonRpcHttpClient client;

    /**
     * 构建Http客户端
     *
     * @param url
     * @param username
     * @param password
     * @throws IOException
     */
    public RpcClientFactory(URL url, String username, String password) throws IOException {
        String cred = Base64Utils.encodeToString((username + ":" + password).getBytes());
        Map<String, String> headers = new HashMap<>(1);
        headers.put("Authorization", "Basic " + cred);
        headers.put("Content-Type", "application/json");
        client = new JsonRpcHttpClient(url, headers);
    }

    public <T> T getClient(Class<T> rpcInterfaceClass) {
        return ProxyUtil.createClientProxy(rpcInterfaceClass.getClassLoader(), rpcInterfaceClass, client);
    }

}
