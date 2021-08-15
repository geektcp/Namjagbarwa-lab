package util;

import org.apache.http.client.HttpRequestRetryHandler;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.*;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpRequestRetryHandler;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

/**
 * @author tanghaiyang on 2019/2/14.
 */
public class HttpUtil {
    private static final String APPLICATION_JSON = "application/json";
    private static final int CONNECT_TIMEOUT = 120*1000;
    private static final int REQUEST_TIMEOUT = 60*1000;
    private static final int SOCKET_TIMEOUT = 60*1000;


    public static String doPost(String url, String data)throws Exception{
        HttpPost method = new HttpPost(url);
        return doMethod(method, data);
    }

    public static String doPut(String url, String data)throws Exception{
        HttpPut method = new HttpPut(url);
        return doMethod(method, data);
    }

    private static String doMethod(HttpEntityEnclosingRequestBase method, String data) throws Exception{
        HttpClientBuilder hcb = HttpClientBuilder.create();
        HttpRequestRetryHandler hrrh = new DefaultHttpRequestRetryHandler();
        HttpClientBuilder httpClientBuilder = hcb.setRetryHandler(hrrh);
        CloseableHttpClient client = httpClientBuilder.build();
        method.addHeader("User-Agent","Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
        method.addHeader(HTTP.CONTENT_TYPE,APPLICATION_JSON);
        RequestConfig.Builder confBuilder = RequestConfig.custom();
        confBuilder.setConnectTimeout(CONNECT_TIMEOUT);
        confBuilder.setConnectionRequestTimeout(REQUEST_TIMEOUT);
        confBuilder.setSocketTimeout(SOCKET_TIMEOUT);
        RequestConfig config = confBuilder.build();
        method.setConfig(config);
        CloseableHttpResponse response = null;
        StringEntity entity = new StringEntity(data, "utf-8");
        entity.setContentEncoding("UTF-8");
        entity.setContentType("application/json");
        method.setEntity(entity);
        response = client.execute(method);
        int code = response.getStatusLine().getStatusCode();
        String result = EntityUtils.toString(response.getEntity());
        response.close();
        client.close();
        return result;
    }

    public static String doGet(String url) throws Exception{
        HttpGet get = new HttpGet(url);
        return doMethod(get);
    }

    private static String doMethod(HttpRequestBase method)throws Exception{
        CloseableHttpResponse response = null;
        CloseableHttpClient client;
        HttpClientBuilder hcb = HttpClientBuilder.create();
        HttpRequestRetryHandler hrrh = new DefaultHttpRequestRetryHandler();
        HttpClientBuilder httpClientBuilder = hcb.setRetryHandler(hrrh);
        client = httpClientBuilder.build();
        method.addHeader("User-Agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
        method.addHeader(HTTP.CONTENT_TYPE, APPLICATION_JSON);
        RequestConfig.Builder confBuilder = RequestConfig.custom();
        confBuilder.setConnectTimeout(CONNECT_TIMEOUT);
        confBuilder.setConnectionRequestTimeout(REQUEST_TIMEOUT);
        confBuilder.setSocketTimeout(SOCKET_TIMEOUT);
        RequestConfig config = confBuilder.build();
        method.setConfig(config);
        response = client.execute(method);
        int code = response.getStatusLine().getStatusCode();
        String result = EntityUtils.toString(response.getEntity());
        response.close();
        client.close();
        return result;
    }
}
