import util.HttpUtil;

/**
 * @author tanghaiyang on 2019/2/14.
 */
public class HttpUtilTest {

    public static void main(String[] args) throws Exception{
        String url = "http://xxxxx:xx";
        String data = "{\"a\":1, \"b\":\"sdfsdf\"}";
        String result = HttpUtil.doPost(url, data);
        System.out.println(result);
    }
}
