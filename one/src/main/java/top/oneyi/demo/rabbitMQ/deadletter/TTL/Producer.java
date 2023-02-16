package top.oneyi.demo.rabbitMQ.deadletter.TTL;


import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import top.oneyi.util.RabbitMqUtils;

/**
 * 生产者代码 TTL 过期
 *
 * @author oneyi
 * @date 2023/2/16
 */

public class Producer {
    private static final String NORMAL_EXCHANGE = "normal_exchange";

    public static void main(String[] args) throws Exception {
        Channel channel = RabbitMqUtils.getChannel();
        //声明交换机
        channel.exchangeDeclare(NORMAL_EXCHANGE, BuiltinExchangeType.DIRECT);
        // 设置消息的TTL 时间
//      AMQP.BasicProperties properties =   new AMQP.BasicProperties.Builder().expiration("10000").build();
        AMQP.BasicProperties properties = null;
        //该信息是用作演示队列个数限制
        for (int i = 1; i < 11; i++) {
            String message = "info" + i;
            channel.basicPublish(NORMAL_EXCHANGE, "zhangsan", properties,
                    message.getBytes());
            System.out.println("生产者发送消息:" + message);
        }

    }
}
