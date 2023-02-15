package top.oneyi.demo.rabbitMQ.test02;

import com.rabbitmq.client.Channel;
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
            channel.queueDeclare(QUEUE_NAME,false,false,false,null);
            //从控制台当中接受信息
            Scanner scanner = new Scanner(System.in);
            while (scanner.hasNext()){
                String message = scanner.next();
                channel.basicPublish("",QUEUE_NAME,null,message.getBytes());
                System.out.println("发送消息完成:"+message);
            }
        }
    }
}
