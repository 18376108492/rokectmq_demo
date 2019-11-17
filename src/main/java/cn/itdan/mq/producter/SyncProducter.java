package cn.itdan.mq.producter;

import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;

/**
 * 同步发送
 */
public class SyncProducter {

    public static void main(String [] args) throws  Exception {
        DefaultMQProducer producer = new DefaultMQProducer("haoke");
        //获取nameServer地址
        producer.setNamesrvAddr("123.57.128.124:9876");
        //启动生产者
         producer.start();
         //发送消息
        String msg ="hello world";
        Message message =new Message("my_topic","myTag",msg.getBytes("UTF-8"));
        SendResult sendResult= producer.send( message);
        System.out.println("ID:"+sendResult.getMsgId());
        System.out.println("队列:"+sendResult.getMessageQueue());
        System.out.println("QueueOffset:"+sendResult.getQueueOffset());

        producer.shutdown();
    }
}
