package top.oneyi.demo.rabbitMQ.Fanout;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.DeliverCallback;
import org.apache.commons.io.FileUtils;
import top.oneyi.util.RabbitMqUtils;

import java.io.File;
import java.nio.charset.StandardCharsets;

/**
 * 消费者 2 把日志存储在磁盘
 *
 * @author oneyi
 * @date 2023/2/16
 */

public class ReceiverLog02 {
    private static final String EXCHANGE_NAME = "logs";

    public static void main(String[] args) throws Exception {
        // 创建通道
        Channel channel = RabbitMqUtils.getChannel();
        // 声明交换机
        channel.exchangeDeclare(EXCHANGE_NAME, "fanout");
        // 声明随机队列
        String queueName = channel.queueDeclare().getQueue();
        // 绑定队列和交换机
        channel.queueBind(queueName, EXCHANGE_NAME, "");
        //接收消息
        DeliverCallback confirmCallback = (consumerTag, delivery) -> {
            String message = new String(delivery.getBody(), StandardCharsets.UTF_8);
            File file = new File("E:\\Desktop\\rabbitMQ_logs.txt");
            FileUtils.writeStringToFile(file, message, "UTF-8", true);
            System.out.println("数据写入文件成功");
        };
        channel.basicConsume(queueName, true, confirmCallback, cancelCallback -> {
        });
    }
}
