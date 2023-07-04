package top.one.listener;


import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;


import java.io.IOException;
import java.util.Date;

@Component
public class DeadLetterQueueConsumer {


    @RabbitListener(queues = "QD")
    public void receiveD(Message message, Channel channel) throws IOException {
        String msg = new String(message.getBody());
        System.out.println("当前时间：" + new Date() + "收到死信队列信息{}" + msg);
    }
}