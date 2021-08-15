package com.geektcp.alpha.agent.util;

import java.util.Random;

/**
 * @author haiyang.tang on 12.09 009 17:22:44.
 */
public class RandomUtilTest {

    public static Random random = new Random(1000);

    public static void main(String[] args) {

        printRandom(random);
        System.out.println("====");
        printRandom(random);
        System.out.println("====");
        printRandom(random);

    }

    private static void printRandom(Random random) {
        System.out.println(random.nextInt());
        System.out.println(random.nextInt());
        System.out.println(random.nextInt());
        System.out.println(random.nextInt());
        System.out.println(random.nextInt());
        System.out.println(random.nextInt());
    }
}
