import alpha.common.base.constant.Constants;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import queue.QueueImpl;
import queue.QueueManager;
import util.FileParser;

import java.io.File;
import java.util.Objects;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;


/**
 * @author tanghaiyang on 2019/2/13.
 */
public class Main {

    private static final int MAX_LINE_SIZE = 3;

    private static LinkedBlockingQueue<JSONObject> queue = new LinkedBlockingQueue<>();

    public static void main(String[] args) throws Exception{
        startQueueManager();

        String filePath = "D:\\CodeGraph\\lab\\graph-lab-tiger\\src\\main\\resources\\data";
        JSONObject ret = FileParser.listPath(filePath);

        System.out.println(JSON.toJSONString(ret, SerializerFeature.PrettyFormat));

        File file = new File(ret.getJSONObject("vertex").getJSONArray("persons").get(0).toString());
        long pos = 0L;
        while (true) {
            JSONObject res = FileParser.readLines(file, Constants.UTF8, pos, MAX_LINE_SIZE);
            if (Objects.isNull(res)) break;
            JSONArray lines = res.getJSONArray("lines");

            if (Objects.nonNull(lines)) {
                QueueManager.add(lines);
                System.out.println(QueueManager.getQueueLenth());
                if (lines.size() < MAX_LINE_SIZE) break;
            } else break;
            pos = (Long) res.get("pos");
        }

    }

    private static void startQueueManager() {
        int queueThreadSize = 2;
        ExecutorService pool = Executors.newFixedThreadPool(queueThreadSize);
        for (int i = 0; i < queueThreadSize; i++) {
            Runnable r = new QueueImpl(i, "", "");
            pool.submit(r);
        }

        if(pool.isTerminated()) {
            pool.shutdown();
        }

    }

}
