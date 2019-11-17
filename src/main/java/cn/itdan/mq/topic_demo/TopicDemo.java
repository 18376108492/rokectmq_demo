package cn.itdan.mq.topic_demo;

import org.apache.rocketmq.client.producer.DefaultMQProducer;

/**
 * 手动创建Topic
 */
public class TopicDemo {

    public static void main(String [] args) throws  Exception{
        DefaultMQProducer producer=new DefaultMQProducer("haoke");
      //获取nameServer地址
         producer.setNamesrvAddr("123.57.128.124:9876");
         //启动生产者
        producer.start();
        //创建Topic
        producer.createTopic("broker_haoke_im","my_topic",6);

        System.out.println("创建成功");
        producer.shutdown();

    }
}
