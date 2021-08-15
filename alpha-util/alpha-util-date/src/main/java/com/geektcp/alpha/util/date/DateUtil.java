package com.geektcp.alpha.util.date;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

/**
 * @author tanghaiyang on 2019/9/28.
 */
public class DateUtil {

    public static void main(String[] args) throws Exception {
        LocalDateTime start = LocalDateTime.now();
        System.out.println(start.toString());
        System.out.println(start.getSecond());

        Thread.sleep(3000);
        LocalDateTime end = LocalDateTime.now();
        System.out.println(end.toString());
        System.out.println(end.getSecond());
        System.out.println(end.toInstant(ZoneOffset.of("+8")).getEpochSecond());
    }

}
