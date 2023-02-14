package top.oneyi;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.QueueingConsumer;
import org.junit.Test;
import top.one.util.ConnectionUtil;

/**
 * 订阅模式 一个生产者 多个消费者,生产者把消息发送给交换机,交换机再把消息发送给队列
 *
 * @author oneyi
 * @date 2023/2/14
 */

public class ExchangeTest {
    /**
     * 叫环节名称
     */
    private final static String EXCHANGE_NAME = "test_exchange_fanout";
    /**
     * 队列名称
     */
    private final static String QUEUE_NAME = "test_queue_work1";

    /**
     * 生产者
     * 需要注意的是叫环节没有存储消息的能力,消息只能存在队列中
     */
    @Test
    public void test01() throws Exception {
        // 获取连接 创建通道
        Connection connection = ConnectionUtil.getConnection();
        Channel channel = connection.createChannel();
        // 声明交换机(exchange)
        channel.exchangeDeclare(EXCHANGE_NAME, "fanout");
        //消息内容
        String message = "hello world!!!";
        channel.basicPublish(EXCHANGE_NAME, "", null, message.getBytes());
        //关闭连接和通道
        System.out.println(" [x] Sent '" + message + "'");
        channel.close();
        connection.close();
    }

    /**
     * 消费者 1
     */
    @Test
    public void test02() throws Exception {
        //创建连接和通道
        Connection connection = ConnectionUtil.getConnection();
        Channel channel = connection.createChannel();
        // 声明队列
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);
        // 绑定队列到交换机
        channel.queueBind(QUEUE_NAME, EXCHANGE_NAME, "");
        // 同一时刻服务器只能发送一条消息给消费者
        channel.basicQos(1);
        //定义队列的消费者
        QueueingConsumer queueingConsumer = new QueueingConsumer(channel);
        // 监听队列 手动返回完成
        channel.basicConsume(QUEUE_NAME, false, queueingConsumer);
        // 获取消息
        while (true) {
            QueueingConsumer.Delivery delivery = queueingConsumer.nextDelivery();
            String message = new String(delivery.getBody());
            System.out.println(" [Recv] Received '" + message + "'");
            Thread.sleep(10);
            channel.basicAck(delivery.getEnvelope().getDeliveryTag(), false);
        }
    }

    /**
     * 消费者2
     */
    @Test
    public void test03() throws Exception {
      // 创建连接和通道
        Connection connection = ConnectionUtil.getConnection();
        Channel channel = connection.createChannel();
        // 声明队列
        channel.queueDeclare("test_queue_work2",false,false,false,null);
        // 绑定队列到交换机
        channel.queueBind("test_queue_work2",EXCHANGE_NAME,"");
        // 同一时刻服务器只会发一条消息给消费者
        channel.basicQos(1);
        // 定义队列的消费者
        QueueingConsumer queueingConsumer = new QueueingConsumer(channel);
        // 监听队列,手动返回完成
        channel.basicConsume("test_queue_work2",false,queueingConsumer);
        // 获取消息
        while (true) {
            QueueingConsumer.Delivery delivery = queueingConsumer.nextDelivery();
            String message = new String(delivery.getBody());
            System.out.println(" [Recv] Received '" + message + "'");
            Thread.sleep(10);
            channel.basicAck(delivery.getEnvelope().getDeliveryTag(), false);
        }
    }
}
