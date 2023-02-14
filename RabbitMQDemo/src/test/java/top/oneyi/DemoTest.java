package top.oneyi;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.QueueingConsumer;
import org.junit.Test;
import top.one.util.ConnectionUtil;
/**
 * 消费者1 和消费者 2 收到的消息都是一样的 一个偶数 一个奇数
 * 消费者1 延时短应该消费者1 多消费 应该根据消费者的能力分配消息
 *
 * @author oneyi
 * @date 2023/2/13
 */



public class DemoTest {

    private final static String QUEUE_NAME = "test_queue_work";

    /**
     * 消费者 1
     */
    @Test
    public void test() throws Exception {
        // 获取连接 创建mq通道
        Connection connection = ConnectionUtil.getConnection();
        Channel channel = connection.createChannel();
        // 声明 队列
        channel.queueDeclare(QUEUE_NAME,false,false,false,null);
        //定义队列的消费者
        QueueingConsumer queueingConsumer = new QueueingConsumer(channel);
        // 监听队列 false 表示手动返回完成状态,true 表示自动
        channel.basicConsume(QUEUE_NAME,true,queueingConsumer);

        while(true){
            QueueingConsumer.Delivery delivery = queueingConsumer.nextDelivery();
            String message = new String(delivery.getBody());
            System.out.println(" [y] Received '" + message + "'");
            //休眠
            Thread.sleep(10);
        }
    }

    /**
     * 消费者2
     */
    @Test
    public void test01() throws Exception {
        // 获取连接 创建mq通道
        Connection connection = ConnectionUtil.getConnection();
        Channel channel = connection.createChannel();
        // 声明 队列
        channel.queueDeclare(QUEUE_NAME,false,false,false,null);
        //定义队列的消费者
        QueueingConsumer queueingConsumer = new QueueingConsumer(channel);
        // 监听队列 false 表示手动返回完成状态,true 表示自动
        channel.basicConsume(QUEUE_NAME,true,queueingConsumer);

        while(true){
            QueueingConsumer.Delivery delivery = queueingConsumer.nextDelivery();
            String message = new String(delivery.getBody());
            System.out.println(" [y] Received '" + message + "'");
            //休眠
            Thread.sleep(1000);
        }
    }

    /**
     * 生产者
     */
    @Test
    public void test02() throws Exception {
        // 获取连接和通道
        Connection connection = ConnectionUtil.getConnection();
        Channel channel = connection.createChannel();
        //声明队列
        channel.queueDeclare(QUEUE_NAME,false,false,false,null);
        // 发送100条消息
        for (int i = 0; i < 100; i++) {
            // 消息内容
            String message = " " +i;
            channel.basicPublish("",QUEUE_NAME,null,message.getBytes());
            Thread.sleep(i * 10);
        }
        channel.close();
        connection.close();
    }
}
