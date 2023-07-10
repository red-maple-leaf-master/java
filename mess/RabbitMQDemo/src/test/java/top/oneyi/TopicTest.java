/*
package top.oneyi;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.QueueingConsumer;
import org.junit.Test;
import top.one.util.ConnectionUtil;

*/
/**
 * 主题模式
 *
 * @author oneyi
 * @date 2023/2/14
 * <p>
 * 主题交换机 名称
 * <p>
 * 队列 1
 * <p>
 * 队列 2
 * <p>
 * 生产者
 * <p>
 * 消费者 1
 * <p>
 * 消费者2
 * <p>
 * 主题交换机 名称
 * <p>
 * 队列 1
 * <p>
 * 队列 2
 * <p>
 * 生产者
 * <p>
 * 消费者 1
 * <p>
 * 消费者2
 *//*


public class TopicTest {
    */
/**
 * 主题交换机 名称
 *//*

    private final static String EXCHANGE_NAME = "test_exchange_topic";

    */
/**
 * 队列 1
 *//*

    private final static String QUEUE_NAME_1 = "test_queue_topic_work_1";

    */
/**
 * 队列 2
 *//*

    private final static String QUEUE_NAME_2 = "test_queue_topic_work_2";

    */
/**
 * 生产者
 *//*

    @Test
    public void topicExchange() throws Exception {
        // 创建连接 和通道
        Connection connection = ConnectionUtil.getConnection();
        Channel channel = connection.createChannel();
        // 声明主题交换机
        channel.exchangeDeclare(EXCHANGE_NAME,"topic");
        //消息内容
        String message ="hello world ! TopicExchange";
        channel.basicPublish(EXCHANGE_NAME,"routeKey.l",null,message.getBytes());
        System.out.println(" [x] Sent '" + message + "'");

        channel.close();
        connection.close();
    }

    */
/**
 * 消费者 1
 *//*

    @Test
    public void topicQueue_1() throws Exception {
        // 获取连接 获取通道
        Connection connection = ConnectionUtil.getConnection();
        Channel channel = connection.createChannel();
        // 声明队列
        channel.queueDeclare(QUEUE_NAME_1,false,false,false,null);
        //绑定队列到交换机
        channel.queueBind(QUEUE_NAME_1,EXCHANGE_NAME,"routeKey.*");
        // 同一时刻服务器只会发一条消息给消费者
        channel.basicQos(1);
        // 定义队列的消费者
        QueueingConsumer queueingConsumer = new QueueingConsumer(channel);
        // 监听队列,手动完成返回
        channel.basicConsume(QUEUE_NAME_1,false,queueingConsumer);
        // 获取消息
        while (true) {
            QueueingConsumer.Delivery delivery = queueingConsumer.nextDelivery();
            String message = new String(delivery.getBody());
            System.out.println(" [Recv_x] Received '" + message + "'");
            Thread.sleep(10);
            channel.basicAck(delivery.getEnvelope().getDeliveryTag(), false);
        }
    }

    */
/**
 * 消费者2
 *//*

    @Test
    public void topicQueue_2() throws Exception {
        // 获取连接 获取通道
        Connection connection = ConnectionUtil.getConnection();
        Channel channel = connection.createChannel();
        // 声明队列
        channel.queueDeclare(QUEUE_NAME_2,false,false,false,null);
        //绑定队列到交换机
        channel.queueBind(QUEUE_NAME_2,EXCHANGE_NAME,"*.*");
        // 同一时刻服务器只会发一条消息给消费者
        channel.basicQos(1);
        // 定义队列的消费者
        QueueingConsumer queueingConsumer = new QueueingConsumer(channel);
        // 监听队列,手动完成返回
        channel.basicConsume(QUEUE_NAME_2,false,queueingConsumer);
        // 获取消息
        while (true) {
            QueueingConsumer.Delivery delivery = queueingConsumer.nextDelivery();
            String message = new String(delivery.getBody());
            System.out.println(" [Recv_x] Received '" + message + "'");
            Thread.sleep(10);
            channel.basicAck(delivery.getEnvelope().getDeliveryTag(), false);
        }
    }

}
*/
