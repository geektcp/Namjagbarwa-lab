package util;

import com.alibaba.fastjson.JSONObject;
import org.junit.Test;

/**
 * @author tanghaiyang on 2019/3/13.
 */
public class StringTest {
    public static void main(String[] args) {
        JSONObject session = new JSONObject();
        session.put("session", "");
        System.out.println( session.toJSONString());
    }

    @Test
    public void testSession(){
        JSONObject session = new JSONObject();
        session.put("session", "");
        System.out.println( session.toJSONString());
    }

    @Test
    public void testSession2(){
        JSONObject session = new JSONObject();
        session.put("session", new String());
        System.out.println( session.toJSONString());
    }

    @Test
    public void testSession3(){
        JSONObject session = new JSONObject();
        session.put("session", null);
        System.out.println( session.toJSONString());
    }

    @Test
    public void testVoidString(){
        String a = new String();
        String b = "";
        String c = null;
        if(a == "")
            System.out.println("a == ''");
        if(a.isEmpty())
            System.out.println("a is Empty");
        if("".equals(a))
            System.out.println("a equals ''");
        if(b == "")
            System.out.println("b == ''");
        if(b.isEmpty())
            System.out.println("b is Empty");
        if("".equals(b))
            System.out.println("b equals ''");
        //报java.lang.NullPointerException
//        if(c == "")
//            System.out.println("c == ''");
        //报java.lang.NullPointerException
//        if(c.isEmpty())
//            System.out.println("c is Empty");
        if("".equals(c))
            System.out.println("c equals ''");
    }

}
