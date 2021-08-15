package com.geektcp.alpha.agent.util;

import com.geektcp.alpha.agent.example.bean.Person;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * @author haiyang.tang on 12.04 004 11:46:50.
 */
public class ReverseTest {


    public static void main(String[] args) {
        try {
            System.out.println("1111111");
            Person person = new Person("小明", 10001);
            person.Speak();
            person.run("10");
            Method m1 = person.getClass().getMethod("Speak", null);
            Method m2 = person.getClass().getMethod("run", String.class);
            Method m3 = person.getClass().getMethod("set");
            System.out.println(m1);
            System.out.println(m2);
            System.out.println(m3);
            m3.invoke(person);

            Field ID = person.getClass().getDeclaredField("ID");

            System.out.println("==" + ID.getName());
            System.out.println("==" + ID.getType());

            ID.setAccessible(true);
            ID.set(person,55);
            int value = (int)ID.get(person);

            System.out.println("==" + value);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }


}
