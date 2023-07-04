package top.oneyi.demo.rabbitMQ.Fanout;

import com.rabbitmq.client.Channel;
import top.oneyi.util.RabbitMqUtils;

import java.util.Scanner;

/**
 * 生产者
 *
 * @author oneyi
 * @date 2023/2/16
 */

public class EmitLog {
    private static final String EXCHANGE_NAME = "logs";

    public static void main(String[] args) throws Exception {
        try (Channel channel = RabbitMqUtils.getChannel()) {
            /**
             * 声明一个 exchange
             * 1.exchange 的名称
             * 2.exchange 的类型
             */
            channel.exchangeDeclare(EXCHANGE_NAME, "fanout");
            Scanner scanner = new Scanner(System.in);
            System.out.println("请输入信息:");
            while (scanner.hasNext()){
                 String message = scanner.nextLine();
                 channel.basicPublish(EXCHANGE_NAME,"",null,message.getBytes());;
                System.out.println("生产者发出消息:" + message);

            }
        }

    }
}
