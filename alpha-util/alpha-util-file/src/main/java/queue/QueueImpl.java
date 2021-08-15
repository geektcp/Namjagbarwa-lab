package queue;


import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by Administrator on 2017/2/17.
 */
public class QueueImpl implements Runnable {
    private Logger loggerKafka = LoggerFactory.getLogger("Kafka");
    private Logger loggerFlusher = LoggerFactory.getLogger("Flusher");
//    private Logger logger = LoggerFactory.getLogger(getClass());

    public static boolean isPoll = true;
    private int threadSeq;
    private ReentrantLock lock = new ReentrantLock();
    private String ucenterTables = "";
    private String flusherTables = "";

    public QueueImpl(int threadSeq, String ucenterTables, String flusherTables) {
        this.threadSeq = threadSeq;
        this.ucenterTables = ucenterTables;
        this.flusherTables = flusherTables;
    }

    public void run()  {
        long threadId = Thread.currentThread().getId();
        loggerKafka.info("start thread for queue, threadSeq: {} | threadId: {}", threadSeq, threadId);

        while(isPoll){
            JSONArray record = QueueManager.poll();
            if(record == null) {
                loggerKafka.debug("threadId: {} | record is null, and continue", threadId);
                try {
                    Thread.sleep(5000);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                continue;
            }

            String line = JSONArray.toJSONString(record, SerializerFeature.PrettyFormat);
            loggerKafka.debug("line: {}", record);
//            System.out.println(JSONArray.toJSONString(record, SerializerFeature.PrettyFormat));

        }


    }




}

