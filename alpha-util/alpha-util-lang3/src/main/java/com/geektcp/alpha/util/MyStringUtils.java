package com.geektcp.alpha.util;

import com.google.common.primitives.Booleans;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.*;
import org.junit.Test;

import java.util.Arrays;

/**
 * @author tanghaiyang on 2019/7/7.
 */
@Slf4j
public class MyStringUtils {

    @Test
    public void test() {
        String a = "";
        String b = null;
        String c = " ";
        String d = "abc";
        String e = "test str";
        String[] aa = {"ddd", "ddffff"};
        System.out.println("StringUtils.isAnyEmpty(a, b, c): " + StringUtils.isAnyEmpty(a, b, c));
        System.out.println("StringUtils.isAllBlank(a,c): " + StringUtils.isAllBlank(a, c));
        System.out.println("StringUtils.isAllBlank(a,c): " + StringUtils.isAllBlank(a, c));
        System.out.println("ClassUtils.getName(this.getClass(): "+ ClassUtils.getName(this.getClass()));
        System.out.println("ClassUtils.getPackageName(this.getClass()): "+ ClassUtils.getPackageName(this.getClass()));
        System.out.println("ClassUtils.getCanonicalName(this.getClass()): "+ ClassUtils.getCanonicalName(this.getClass()));
        System.out.println("StringUtils.isAlpha(d): " + StringUtils.isAlpha(d));
        System.out.println("StringUtils.isAlpha(e): " + StringUtils.isAlpha(e));
        System.out.println("StringUtils.isNumeric(e): " + StringUtils.isNumeric(e));
    }

    @Test
    public void ArrayUtils() {
        int[] array = {1, 2, 3};
        System.out.println(ArrayUtils.contains(array, 2));//true
        System.out.println(ArrayUtils.contains(array, 4));//false

        int[] array1 = {1, 2, 3};
        int[] array2 = {4, 5, 6};
        int[] array3 = ArrayUtils.addAll(array1, array2);
        for (int i : array3) {
            System.out.println(i);
        }

        int[] array4 = {1, 2, 3};

        int[] clone = ArrayUtils.clone(array4);
        for (int i : clone) {
            System.out.println(i);
        }

        int[] array5 = {4, 5, 6};
        int[] subArray = ArrayUtils.subarray(array5, 0, 2);
        for (int i : subArray) {
            System.out.println(i);
        }

        int[] array6 = {4, 5, 6};
        Integer[] arrayObject = ArrayUtils.toObject(array6);
        for (Integer integer : arrayObject) {
            System.out.println(integer);
        }
        ArrayUtils.swap(array6, 2, 1);
        System.out.println(Arrays.toString(array6));

    }

    @Test
    public void BooleanUtils() {
        boolean a = false;
        boolean b = true;
        int c = BooleanUtils.compare(a, b);
        System.out.println(BooleanUtils.toStringYesNo(b));
        Boolean d = Boolean.FALSE;
        boolean f = BooleanUtils.toBoolean(d);
        System.out.println(BooleanUtils.toInteger(a));
        boolean g =  BooleanUtils.and(Booleans.toArray(Booleans.asList(false,true)));
        System.out.println(g);
    }


    @Test
    public void SystemUtils(){
        System.out.println(SystemUtils.IS_JAVA_1_7);
        System.out.println(SystemUtils.IS_JAVA_1_8);
        System.out.println(SystemUtils.IS_OS_MAC);
        System.out.println(SystemUtils.IS_OS_WINDOWS);
        System.out.println(SystemUtils.getUserHome());
        System.out.println(SystemUtils.getHostName());
        System.out.println(SystemUtils.getJavaHome());
        System.out.println(SystemUtils.JAVA_HOME);
        System.out.println(SystemUtils.getEnvironmentVariable("JAVA_HOME", ""));

    }

    @Test
    public void Validate(){
//        Validate.notEmpty()
    }

    @Test
    public void RandomStringUtils(){
        System.out.println( RandomStringUtils.random(10));
        System.out.println( RandomStringUtils.randomAlphabetic(10));
        System.out.println( RandomStringUtils.randomAlphanumeric(10));
        System.out.println( RandomStringUtils.randomAscii(10));
        System.out.println( RandomStringUtils.randomPrint(10));
        System.out.println( RandomStringUtils.randomGraph(10));

    }

    @Test
    public void SerializationUtils(){
        SerializeObject object =SerializeObject.build().city("shenzhen").age(30).name("nagle");
        byte[] bytes = SerializationUtils.serialize(object);
        SerializeObject deObject = SerializationUtils.deserialize(bytes);
        System.out.println(deObject);
    }

    @Test
    public void StringUtitls(){
        System.out.println(StringUtils.containsAny("aabbccdd", "aa","bb","cc"));

        System.out.println(StringUtils.containsAny("aab1ccdd", "aa","bb"));
    }

}
