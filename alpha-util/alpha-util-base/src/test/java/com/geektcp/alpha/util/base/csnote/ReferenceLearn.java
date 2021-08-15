package com.geektcp.alpha.util.base.csnote;

import org.junit.Assert;
import org.junit.Test;

import java.lang.ref.PhantomReference;
import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;

/**
 * @author tanghaiyang on 2020/5/13 20:04.
 * 四种引用类型：强引用，软引用，弱引用，虚引用
 */
@SuppressWarnings("all")
public class ReferenceLearn {

    /**
     * 强引用
     * 被强引用关联的对象不会被回收。
     */
    @Test
    public void name() {
        Object obj = new Object();
        Assert.assertTrue(true);
    }

    /**
     * 软引用
     * 被软引用关联的对象只有在内存不够的情况下才会被回收。
     */
    @Test
    public void softReferenceTest() {
        Object obj = new Object();
        SoftReference<Object> sf = new SoftReference<>(obj);
        obj = null;  // 使对象只被软引用关联
        Assert.assertTrue(true);
    }

    /**
     * 弱引用
     * 被弱引用关联的对象一定会被回收，也就是说它只能存活到下一次垃圾回收发生之前。
     */
    @Test
    public void weakReference() {
        Object obj = new Object();
        WeakReference<Object> wf = new WeakReference<>(obj);
        obj = null;
        Assert.assertTrue(true);
    }

    /**
     * 虚引用
     * 虚引用加不加都不影响关联的对象，只是标记跟踪作用
     * 为一个对象设置虚引用的唯一目的是能在这个对象被回收时收到一个系统通知。
     */
    @Test
    public void phantomReference() {
        Object obj = new Object();
        PhantomReference<Object> pf = new PhantomReference<>(obj, null);
        obj = null;
        Assert.assertTrue(true);
    }
}
