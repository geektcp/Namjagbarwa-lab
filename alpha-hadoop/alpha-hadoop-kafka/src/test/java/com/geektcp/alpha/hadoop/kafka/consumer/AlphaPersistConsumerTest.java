package com.geektcp.alpha.hadoop.kafka.consumer;


import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;

/**
 * @author tanghaiyang on 2018/10/29.
 */
@Slf4j
@Component
public class AlphaPersistConsumerTest {

    @KafkaListener(id = "demo1", topics = "deltaQ_GPE_1Q")
    public void listen(ConsumerRecord<Object, Object> record, Acknowledgment ack) {
//        ack.acknowledge();
        log.info("partition: {}", record.partition());
        test(record);
    }

    private void test(ConsumerRecord<Object, Object> record) {
        try {
            log.info("topic: {} | value: {} | offset:{} | timestamp: {}",
                    record.topic(), record.value(),record.offset(), record.timestamp());

            String value = record.value().toString();
            log.info(value);

//            String ret2 = decode(value);
//            log.info("ret2: {}", ret2);
//
//            String ret = decodeUnicode(value);
//            log.info("ret: {}", ret);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String gbEncoding(final String gbString) {
        char[] utfBytes = gbString.toCharArray();
        String unicodeBytes = "";
        for (int i = 0; i < utfBytes.length; i++) {
            String hexB = Integer.toHexString(utfBytes[i]);
            if (hexB.length() <= 2) {
                hexB = "00" + hexB;
            }
            unicodeBytes = unicodeBytes + "\\u" + hexB;
        }
        return unicodeBytes;
    }

    /*
     * unicode编码转中文
     */
    public static String decodeUnicode(final String dataStr) {
        int start = 0;
        int end = 0;
        final StringBuffer buffer = new StringBuffer();
        while (start > -1) {
            end = dataStr.indexOf("\\u", start + 2);
            String charStr = "";
            if (end == -1) {
                charStr = dataStr.substring(start + 2, dataStr.length());
            } else {
                charStr = dataStr.substring(start + 2, end);
            }
            char letter = (char) Integer.parseInt(charStr, 16); // 16进制parse整形字符串。
            buffer.append(new Character(letter).toString());
            start = end;
        }
        return buffer.toString();
    }

    public static String decode(String src) {
        int t =  src.length() / 6;
        StringBuilder str = new StringBuilder();
        for (int i = 0; i < t; i++) {
            String s = src.substring(i * 6, (i + 1) * 6); // 每6位描述一个字节
            // 高位需要补上00再转
            String s1 = s.substring(2, 4) + "00";
            // 低位直接转
            String s2 = s.substring(4);
            // 将16进制的string转为int
            int n = Integer.valueOf(s1, 16) + Integer.valueOf(s2, 16);
            // 将int转换为字符
            char[] chars = Character.toChars(n);
            str.append(new String(chars));
        }
        return str.toString();
    }
}
