package top.oneyi.demo.rabbitMQ.test02;

import com.rabbitmq.client.CancelCallback;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.DeliverCallback;
import top.oneyi.util.RabbitMqUtils;

/**
 * 消费者2
 * @author oneyi
 * @date 2023/2/15
 */


public class Consumer01 {
    private final static String QUEUE_NAME = "test_queue";

    public static void main(String[] args) throws Exception {
        Channel channel = RabbitMqUtils.getChannel();
        System.out.println("C2 等待接收消息处理时间比较长");
        DeliverCallback deliverCallback = (consumerTag, delivery) -> {
            String message = new String(delivery.getBody());
            try {
                Thread.sleep(40000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(message);
            // 1 消息标记tag 2 false代表只应答接收到的那个传递的消息 true为应答所有消息包括传递过来的消息
            channel.basicAck(delivery.getEnvelope().getDeliveryTag(),false);
        };

        CancelCallback cancelCallback = (consumerTag) -> {
            System.out.println(consumerTag + "消费者取消消费接口回调逻辑");
        };
        System.out.println("C2 消费者启动等待消费......");


        // 手动应答 设置false
        channel.basicConsume(QUEUE_NAME, false, deliverCallback, cancelCallback);
    }
}
