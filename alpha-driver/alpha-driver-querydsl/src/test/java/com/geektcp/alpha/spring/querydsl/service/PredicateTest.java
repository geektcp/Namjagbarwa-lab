package com.geektcp.alpha.spring.querydsl.service;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

/**
 * Created by HaiyangWork on 2019/1/2.
 */
public class PredicateTest {
    public static void main(String[] args) {
        test();
    }

    private static void testAssert() {
        boolean isOk = 1 > 2;
        assert isOk;
        System.out.println("程序正常");
    }

    private static void test() {
        //给list添加参数
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9);
        System.out.println("输出所有参数字:");
        eval(list, n -> true);
        System.out.println("\n输出能被2整除的数字:");
        eval(list, n -> n % 2 == 0);
        System.out.println("\n输出大于3的数字:");
        eval(list, n -> n > 3);
    }

    private static void eval(List<Integer> list, Predicate<Integer> predicate) {
        for (Integer n : list) {
            if (predicate.test(n)) {
                //可以将满足条件的参数返回，这里只做输出
                System.out.print(n + " ");
            }
        }
    }

}
