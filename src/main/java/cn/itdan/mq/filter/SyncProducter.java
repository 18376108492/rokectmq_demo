package cn.itdan.mq.filter;

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
        //需要去配置文件中开启过滤
        //加入到broker的配置文件中
        // enablePropertyFilter=true
        String msg ="条件过滤测试";
        Message message =new Message("my_topic_filter","myTag",msg.getBytes("UTF-8"));
         message.putUserProperty("username ","xiaoming");
         message.putUserProperty("age ","18");
         message.putUserProperty("sex ","man");
        SendResult sendResult= producer.send( message);
        System.out.println("ID:"+sendResult.getMsgId());
        System.out.println("队列:"+sendResult.getMessageQueue());
        System.out.println("QueueOffset:"+sendResult.getQueueOffset());

        producer.shutdown();
    }
}
