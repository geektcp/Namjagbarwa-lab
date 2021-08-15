import org.junit.Test;

        import java.net.URLDecoder;
        import java.net.URLEncoder;

/**
 * Created by HaiyangHome on 2019/3/23.
 */
public class UrlEncodeTest {


    /**
     * urlencode转码不能随便用，因为她会把空格转换成+号，而不是标准的%20字符。
     * 对于spring构建的服务端不会有这个问题。但我在tiger服务器上遇到这种问题。
     * 所以urlencode只适用于服务端支持的协议是RFC1738
     * 如果服务端只支持RFC 2396标准，那么服务端解码时，会把加号+当成保留字符，而不转码
     * */
    @Test
    public void test()throws Exception{
        String charset = "UTF-8";
        String str = "aabb ccddfff++++111";
        System.out.println(URLEncoder.encode(str, charset));

        String decodeStr = "aabb+ccddfff%2B%2B%2B%2B111";
        System.out.println(URLDecoder.decode(decodeStr, charset));

        String decodeStr2 = "aabb%20ccddfff%2B%2B%2B%2B111";
        System.out.println(URLDecoder.decode(decodeStr, charset));
    }

}
