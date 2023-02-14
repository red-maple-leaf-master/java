/*
package top.one.service.impl;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.QueueingConsumer;
import org.springframework.stereotype.Service;
import top.one.service.RabbitService;
import top.one.util.ConnectionUtil;
@Service
public class RabbitServicevImpl implements RabbitService {
    private final static String QUEUE_NAME = "test_channel";


    */
/**
     * 发送简单消息到队列
     * 生产消息
     *//*

    @Override
    public void sendSimpleMsg() throws Exception {
        // 获取到连接以及mq通道
        Connection connection = ConnectionUtil.getConnection();
        // 从连接中创建通道
        Channel channel = connection.createChannel();
        // 声明(创建) 队列
        channel.queueDeclare(QUEUE_NAME,false,false,false,null);
        // 消息内容  产生消息
        String msg="测试消息哦!";
        channel.basicPublish("",QUEUE_NAME,null,msg.getBytes());
        System.out.println(" Sent '" + msg +"'");
        // 关闭通道和连接
        channel.close();
        connection.close();
    }

    */
/**
     * 接收到简单消息队列的消息
     * 消费消息
     * @return
     *//*

    @Override
    public void recvSimpleMsg() throws Exception {

        // 获取到连接以及mq通道
        Connection connection = ConnectionUtil.getConnection();
        // 从连接中创建通道
        Channel channel = connection.createChannel();
        // 声明队列
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);

        // 定义队列的消费者
        QueueingConsumer consumer = new QueueingConsumer(channel);

        // 监听队列
        channel.basicConsume(QUEUE_NAME, true, consumer);

        // 获取消息
        while (true) {
            QueueingConsumer.Delivery delivery = consumer.nextDelivery();
            String message = new String(delivery.getBody());
            System.out.println(" [x] Received '" + message + "'");
        }
    }
}
*/
