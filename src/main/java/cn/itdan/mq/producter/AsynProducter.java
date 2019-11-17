package cn.itdan.mq.producter;

import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;

/**
 * 异步发送
 */
public class AsynProducter {

    public static void main(String [] args) throws Exception{
        DefaultMQProducer producer = new DefaultMQProducer("haoke");
        //获取nameServer地址
        producer.setNamesrvAddr("123.57.128.124:9876");
        //启动生产者
        producer.start();
        //发送消息
        String msg ="hello world(异步)";

        Message message=new Message("my_topic",msg.getBytes("UTF-8"));
        //发送
        producer.send(message, new SendCallback() {
            @Override
            public void onSuccess(SendResult sendResult) {
                System.out.println("消息发送成功");
                System.out.println("ID:"+sendResult.getMsgId());
                System.out.println("队列:"+sendResult.getMessageQueue());
                System.out.println("QueueOffset:"+sendResult.getQueueOffset());
            }

            @Override
            public void onException(Throwable throwable) {
                System.out.println("消息发送失败");
            }
        });
       // producer.shutdown();要等到消息发送完才能shutdown
    }
}
