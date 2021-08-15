package util;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;
import java.util.List;

/**
 * @author tanghaiyang on 2019/3/6.
 */
public class BeanJsonTest {

    private static final String cmd = "ls";

    public static void main(String[] args) throws Exception {
        Person person = new Person();
        person.setName("nagle");
        person.setAge(11);
        person.setSex("male");
        person.setCity("shenzhen");
        person.setBirthday("2018-12-11");

        List<String> colors = new ArrayList<>();
        colors.add("yellow");
        colors.add("blue");
        person.setColors(colors);

        System.out.println(JSON.toJSONString(person));
        System.out.println(JSON.toJSONString(cmd));
        System.out.println(new ObjectMapper().writeValueAsString(cmd));
        System.out.println(cmd);
    }
}
