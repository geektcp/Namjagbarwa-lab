package com.geektcp.alpha.util.date;

import org.junit.Test;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * @author haiyang.tang on 10.24 024 10:26:54.
 */
public class DateBuilderTest {

    @Test
    public void parseTest() throws Exception {


        String time1 = "Mon Oct 21 16:45:03 CST 2019";
        String pattern1 = "EEE MMM d HH:mm:ss zzz yyyy";


        String time2 = "2018-06-01 10:12:05";
        String pattern2 = "yyyy-MM-dd HH:mm:ss";


        String time3 = "Thu, 15 Sep 2011 12:13:41 GMT";
        String pattern3 = "EEE, d MMM yyyy HH:mm:ss zzz";

        String time4 = "2019-10-22T10:29:26.540210169+08:00";
        String pattern4 = "yyyy-MM-dd'T'HH:mm:ss";

        System.out.println(DateBuilder.transform(pattern4,time4));

        System.out.println(DateBuilder.format(pattern1, time1));
        System.out.println(DateBuilder.format(pattern2, time2));
        System.out.println(DateBuilder.format(pattern3, time3));

        System.out.println(DateBuilder.format(pattern4, time4));

        System.out.println(DateBuilder.format(time2));
        System.out.println(DateBuilder.format(time4));
    }


//    @Test
//    public void testTime() throws Exception {
//         String time = "2017-01-03T04:35:33+00:00";
//
//        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
//
//        Timestamp c = getTimestamp(time, sf);
//
//        System.out.println(c);
//    }

//    public static Timestamp getTimestamp(Object obj, String pattern) throws ParseException {
//        String str = obj.toString().trim();
//        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
//        if(str.equals("")) return null;
//        else return new Timestamp(sdf.parse(str).getTime());
//    }
}
