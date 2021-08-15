package com.geektcp.alpha.util.date;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * @author haiyang.tang on 10.24 024 10:08:31.
 */
public class DateBuilder {
    private static final String pattern_cst = "yyyy-MM-dd HH:mm:ss";
    private static final String pattern_std = "yyyy-MM-dd'T'HH:mm:ss";
    private static final String pattern_gmt = "EEE, d MMM yyyy HH:mm:ss 'GMT'";
    private static ThreadLocal<DateFormat> localDateFormat = new ThreadLocal<>();
    private static ThreadLocal<DateFormat> localDateFormatDefault = new ThreadLocal<>();
    
    public static Date transform(String dateStr) throws ParseException {
        String dateStrReplaced = dateStr.replaceAll("T", " ").replaceAll("Z", "");
        return getDateFormat().parse(dateStrReplaced);
    }

    public static Date transform(String parsePattern, String strDate) throws ParseException {
        Locale locale = Locale.US;
        if (strDate.contains(TimeZone.GMT.toString())) {
            locale = Locale.ENGLISH;
        }
        return getDateFormat(parsePattern, locale).parse(strDate);
    }

    public static String formatDefaultDate(Date date) {
        return getDateFormat().format(date);
    }

    public static String formatDefaultDate(String date) {
        return getDateFormat().format(date);
    }

    public static String format(String parsePattern, String strDate) throws ParseException {
        Date date = transform(parsePattern, strDate);
        return getDateFormat().format(date);
    }

    public static String format(String strDate) throws ParseException {
        Date date = transform(strDate);
        return getDateFormat().format(date);
    }

    ////////////////////////////////////////
    private static DateFormat getDateFormat() {
        DateFormat df = new SimpleDateFormat(pattern_cst);
        localDateFormat.set(df);
        return df;
    }

    private static DateFormat getDateFormat(String pattern, Locale locale) {
        DateFormat df = new SimpleDateFormat(pattern, locale);
        localDateFormat.set(df);
        return df;
    }

    /**
     * @author haiyang.tang on 10.24 024 10:58:40.
     */
    public enum TimeZone {
        CST,
        GMT,
        CET,
        UTC,
        IST,
        EST,
        MST,
        PST,
        ;
    }
}
