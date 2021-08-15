package com.geektcp.alpha.hadoop.kafka.consumer;


import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;

/**
 * @author tanghaiyang on 2018/10/29.
 */
@Slf4j
//@Component
public class AlphaPersistConsumer {
    @KafkaListener(id = "demo2", topics = "mytest1", group = "alpha")
    public void listen(ConsumerRecord<Object, Object> record, Acknowledgment ack) {
//        System.out.println("111111111");
        log.info("records: {}", record.partition());
        test(record);
    }

    private void test(ConsumerRecord<Object, Object> record){
        log.info("topic: {} | value: {} | timestamp: {}", record.topic(), record.value(), record.timestamp());

    }

}
