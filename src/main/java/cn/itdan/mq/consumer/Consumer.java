package cn.itdan.mq.consumer;


import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.MessageSelector;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.common.message.MessageExt;

import java.util.List;

/**
 * 消费者
 */
public class Consumer {

    public static void main(String [] args)throws  Exception{

        DefaultMQPushConsumer consumer=new DefaultMQPushConsumer("haoke_consumer");

        consumer.setNamesrvAddr("123.57.128.124:9876");
        //订阅消息(订阅所有)
     //   consumer.subscribe("my_topic","*");
      //订阅消息的方式，可以根据标签（tags）获取
        consumer.subscribe("my_topic_filter",MessageSelector.bySql("sex='man' and age>=10"));


        consumer.registerMessageListener(new MessageListenerConcurrently() {
            @Override
            public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> list, ConsumeConcurrentlyContext consumeConcurrentlyContext) {
                //输出获取到的消息
                try {
                    for (MessageExt messageExt:list){
                        System.out.println("消息："+new String(messageExt.getBody(),"UTF-8"));
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }
               // System.out.println(list);
                return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
            }
        });
        // 启动消费者
        consumer.start();
    }
}
