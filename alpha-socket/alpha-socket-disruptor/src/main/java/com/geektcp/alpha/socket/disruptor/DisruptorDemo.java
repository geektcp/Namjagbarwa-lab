package com.geektcp.alpha.socket.disruptor;

import com.lmax.disruptor.*;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.dsl.ProducerType;
import lombok.extern.slf4j.Slf4j;

import java.util.Scanner;
import java.util.concurrent.ThreadFactory;

/**
 * Created by HaiyangHome on 2019/4/17.
 */
@Slf4j
public class DisruptorDemo {

    /*
    * 从当前控制台console的输入中获取消息，由disruptor消费并处理
    * */
    public static void main(String[] args) {
        log.info("测试用例");
        int ringBufferSize = 1024;//必须是2的N次方
        Disruptor<MessageEvent> disruptor = new Disruptor<>(
                new MessageEventFactory(),
                ringBufferSize,
                new MessageThreadFactory(),
                ProducerType.SINGLE,
                new BlockingWaitStrategy());
        disruptor.handleEventsWith(new MessageEventHandler());
        disruptor.setDefaultExceptionHandler(new MessageExceptionHandler());
        RingBuffer<MessageEvent> ringBuffer = disruptor.start();
        MessageEventProducer producer = new MessageEventProducer(ringBuffer);

        while (true) {
            Scanner input = new Scanner(System.in);
            String message = "输入的内容是: " + input.next();
            producer.onData(message);

            if(message.length()==0){
                break;
            }
        }
    }


    /**
     * 消息事件类
     */
    public static class MessageEvent{
        /**
         * 原始消息
         */
        private String message;

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }
    }

    /**
     * 消息事件工厂类
     */
    public static class MessageEventFactory implements EventFactory<MessageEvent> {
        @Override
        public MessageEvent newInstance() {
            return new MessageEvent();
        }
    }

    /**
     * 消息转换类，负责将消息转换为事件
     */
    public static class MessageEventTranslator implements EventTranslatorOneArg<MessageEvent,String> {
        @Override
        public void translateTo(MessageEvent messageEvent, long l, String s) {
            messageEvent.setMessage(s);
        }
    }

    /**
     * 消费者线程工厂类
     */
    public static class MessageThreadFactory implements ThreadFactory {
        @Override
        public Thread newThread(Runnable r) {
            return new Thread(r,"Simple Disruptor Test Thread");
        }
    }

    /**
     * 消息事件处理类，这里只打印消息
     */
    public static class MessageEventHandler implements EventHandler<MessageEvent> {
        @Override
        public void onEvent(MessageEvent messageEvent, long l, boolean b) throws Exception {
            log.info(messageEvent.getMessage());
        }
    }

    /**
     * 异常处理类
     */
    public static class MessageExceptionHandler implements ExceptionHandler<MessageEvent> {
        @Override
        public void handleEventException(Throwable ex, long sequence, MessageEvent event) {
            ex.printStackTrace();
        }

        @Override
        public void handleOnStartException(Throwable ex) {
            ex.printStackTrace();

        }

        @Override
        public void handleOnShutdownException(Throwable ex) {
            ex.printStackTrace();

        }
    }

    /**
     * 消息生产者类
     */
    public static class MessageEventProducer{
        private RingBuffer<MessageEvent> ringBuffer;

        public MessageEventProducer(RingBuffer<MessageEvent> ringBuffer) {
            this.ringBuffer = ringBuffer;
        }

        /**
         * 将接收到的消息输出到ringBuffer
         * @param message
         */
        public void onData(String message){
            EventTranslatorOneArg<MessageEvent,String> translator = new MessageEventTranslator();
            ringBuffer.publishEvent(translator,message);
        }
    }




}

