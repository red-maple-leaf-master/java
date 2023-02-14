/*
package top.oneyi;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.QueueingConsumer;
import org.junit.Test;
import top.one.util.ConnectionUtil;

*/
/**
 * 路由模式
 *  根据key向指定的队列发送消息
 * @author oneyi
 * @date 2023/2/14
 *//*


public class DirectTest {
    */
/**
     * 路由交换机
     *//*

    private final static String EXCHANGE_NAME = "test_exchange_direct";
    */
/**
     *  队列1
     *//*

    private  final static String QUEUE_NAME_1 = "test_queue_direct_1";
    */
/**
     * 队列2
     *//*

    private  final static String QUEUE_NAME_2 = "test_queue_direct_2";

    */
/**
     * 生产者
     *//*

    @Test
    public void direct() throws Exception {
        // 创建链接和通道
        Connection connection = ConnectionUtil.getConnection();
        Channel channel = connection.createChannel();
        // 声明交换机   参数 1 交换机名称  参数 2  交换机类型
        channel.exchangeDeclare(EXCHANGE_NAME,"direct");
        // 消息内容
        String message = "预定酒店";
        // 参数1 交换机名称  参数2 消息 key 参数 4 发送的消息
        channel.basicPublish(EXCHANGE_NAME,"insert",null,message.getBytes());
        //关闭流
        channel.close();
        connection.close();
    }

    */
/**
     * 消费者 1
     *//*

    @Test
    public void direct_queue_1() throws Exception {
        // 创建连接 创建通道
        Connection connection = ConnectionUtil.getConnection();
        Channel channel = connection.createChannel();
        // 声明队列
        channel.queueDeclare(QUEUE_NAME_1,false,false,false,null);
        // 绑定队列到交换机
        channel.queueBind(QUEUE_NAME_1,EXCHANGE_NAME,"update");
        channel.queueBind(QUEUE_NAME_1,EXCHANGE_NAME,"delete");
        // 同一时刻服务器只会发送一条消息给消费者
        channel.basicQos(1);
        // 定义队列的消费者
        QueueingConsumer queueingConsumer = new QueueingConsumer(channel);
        // 监听队列手动返回
        channel.basicConsume(QUEUE_NAME_1,false,queueingConsumer);
        // 获取消息
        while (true) {
            QueueingConsumer.Delivery delivery = queueingConsumer.nextDelivery();
            String message = new String(delivery.getBody());
            System.out.println(" [Recv] Received '" + message + "'");
            Thread.sleep(10);
            channel.basicAck(delivery.getEnvelope().getDeliveryTag(), false);
        }
    }
    */
/**
     * 消费者 2
     *//*

    @Test
    public void direct_queue_2() throws Exception {
        // 创建连接 创建通道
        Connection connection = ConnectionUtil.getConnection();
        Channel channel = connection.createChannel();
        // 声明队列
        channel.queueDeclare(QUEUE_NAME_2,false,false,false,null);
        // 绑定队列到交换机
        channel.queueBind(QUEUE_NAME_2,EXCHANGE_NAME,"update");
        channel.queueBind(QUEUE_NAME_2,EXCHANGE_NAME,"delete");
        channel.queueBind(QUEUE_NAME_2,EXCHANGE_NAME,"insert");
        // 同一时刻服务器只会发送一条消息给消费者
        channel.basicQos(1);
        // 定义队列的消费者
        QueueingConsumer queueingConsumer = new QueueingConsumer(channel);
        // 监听队列手动返回
        channel.basicConsume(QUEUE_NAME_2,false,queueingConsumer);
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
*/
