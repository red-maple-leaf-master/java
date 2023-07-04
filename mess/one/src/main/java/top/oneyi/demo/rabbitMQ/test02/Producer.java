package top.oneyi.demo.rabbitMQ.test02;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.MessageProperties;
import top.oneyi.util.RabbitMqUtils;

import java.util.Scanner;
/**
 * 生产者
 * @author oneyi
 * @date 2023/2/15
 */

public class Producer {
    private static final String QUEUE_NAME="test_queue";
    public static void main(String[] args) throws Exception {
        try(Channel channel= RabbitMqUtils.getChannel();) {
            // 1 队列名称  2 队列是否持久化
            boolean durable = true;
            channel.queueDeclare(QUEUE_NAME,durable,false,false,null);
            //从控制台当中接受信息
            Scanner scanner = new Scanner(System.in);
            while (scanner.hasNext()){
                String message = scanner.next();

                channel.basicPublish("",QUEUE_NAME, MessageProperties.PERSISTENT_TEXT_PLAIN,message.getBytes());
                System.out.println("发送消息完成:"+message);
            }
        }
    }
}
