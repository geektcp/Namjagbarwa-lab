package com.geektcp.alpha.util.base;

import org.junit.Test;

import java.nio.charset.StandardCharsets;

/**
 * @author Mr.Tang on 2021/3/30 10:55.
 */
public class CharLenTest {

    @Test
    public  void main() throws Exception{
        char a= (char) Integer.MAX_VALUE;
        System.out.println((int)a);

        System.out.println("一".getBytes(StandardCharsets.UTF_8).length);
        System.out.println("%".getBytes(StandardCharsets.UTF_8).length);
        System.out.println("B".getBytes(StandardCharsets.UTF_8).length);

        System.out.println("=============");
        char b = '国';
        char c = 'a';
        System.out.println(charToByte('国').length);
        System.out.println(charToByte('B').length);
        System.out.println(charToByte(c).length);

    }

    private static byte[] charToByte(char c) {
        byte[] b = new byte[2];
        b[0] = (byte) ((c & 0xFF00) >> 8);
        b[1] = (byte) (c & 0xFF);
        return b;
    }

}

