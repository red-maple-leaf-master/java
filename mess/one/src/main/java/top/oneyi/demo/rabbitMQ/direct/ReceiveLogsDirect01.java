package top.oneyi.demo.rabbitMQ.direct;

import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.DeliverCallback;
import org.apache.commons.io.FileUtils;
import top.oneyi.util.RabbitMqUtils;

import java.io.File;
import java.nio.charset.StandardCharsets;

/**
 * 消费者1
 *
 * @author oneyi
 * @date 2023/2/16
 */

public class ReceiveLogsDirect01 {
    private static final String EXCHANGE_NAME = "direct_logs";

    public static void main(String[] args) throws Exception {
        final Channel channel = RabbitMqUtils.getChannel();
        // 声明交换机
        channel.exchangeDeclare(EXCHANGE_NAME, BuiltinExchangeType.DIRECT);
        // 声明队列
        String queueName = "disk";
        channel.queueDeclare(queueName, false, false, false, null);
        // 队列绑定交换机
        channel.queueBind(queueName, EXCHANGE_NAME, "error");
        System.out.println("等待接收消息.....");
        DeliverCallback callback = (consumerTag, delivery) -> {
            String message = new String(delivery.getBody(), StandardCharsets.UTF_8);
            message = "接收绑定键:" + delivery.getEnvelope().getRoutingKey() + ",消息" + message;
            File file = new File("E:\\Desktop\\rabbitMQ_info_logs.txt");
            FileUtils.writeStringToFile(file, message, "UTF-8", true);
            System.out.println("错误日志生成成功!");
        };
        channel.basicConsume(queueName, true, callback, consumerTag -> {
        });
    }
}
