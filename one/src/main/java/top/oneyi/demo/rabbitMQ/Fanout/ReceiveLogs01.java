package top.oneyi.demo.rabbitMQ.Fanout;

import com.rabbitmq.client.Channel;

import com.rabbitmq.client.DeliverCallback;
import top.oneyi.util.RabbitMqUtils;

/**
 * 消费者1 把日志输出在控制台
 * @author oneyi
 * @date 2023/2/16
 */

public class ReceiveLogs01 {
    private static final String EXCHANGE_NAME = "logs";
    public static void main(String[] args) throws Exception {
        Channel channel = RabbitMqUtils.getChannel();
        // 声明一个交换机
        channel.exchangeDeclare(EXCHANGE_NAME,"fanout");
        /**
         * 生成一个临时的队列 队列名称是随机的
         * 当消费者断开和该队列的连接时 队列自动删除
         */
        String queueName = channel.queueDeclare().getQueue();
        // 绑定交换机  1 队列名称 2 交换机名称  3 routingKey 为空
        channel.queueBind(queueName,EXCHANGE_NAME,"");
        System.out.println("等待接收消息,把接收到的消息打印在屏幕.....");
        DeliverCallback callback = (consumerTag, delivery) -> {
            String message = new String(delivery.getBody(), "UTF-8");
            System.out.println("控制台打印的消息为:" + message);
        };
        channel.basicConsume(queueName,true,callback,consumerTag ->{

        });

    }
}
