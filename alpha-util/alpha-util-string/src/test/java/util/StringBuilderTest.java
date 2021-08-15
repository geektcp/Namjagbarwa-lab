package util;

import org.junit.Test;

/**
 * @author tanghaiyang on 2019/3/20.
 */
public class StringBuilderTest {

    @Test
    public void test(){
        StringBuilder sb = new StringBuilder();

        String city = "sanya";
        sb.append("city=");
        sb.append(city);

        sb.append(" age=");
        sb.append(4);

        System.out.println(sb.toString());


    }


}
