package queue;

/* Created by Haiyang on 2018/7/17. */

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ConcurrentLinkedQueue;

public class QueueManager extends Thread {
    private static Logger logger = LoggerFactory.getLogger(QueueManager.class);

    // 定义一个上传队列
    private static ConcurrentLinkedQueue<JSONArray> queueUpload = new ConcurrentLinkedQueue<>();

    /*
    * 删除元素
    *
    * */
    public void remove(){

    }

    public static void add(JSONArray record){
        queueUpload.add(record);
    }

    public static JSONArray poll(){
        return queueUpload.poll();
    }

    public static void clear(){
        queueUpload.clear();
    }


    public static int getQueueLenth(){
        return queueUpload.size();
    }

}
