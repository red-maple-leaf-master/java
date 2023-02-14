package top.one.listener;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
/**
 * 监听某个队列的消息
 * @author oneyi
 * @date 2023/2/14
 */

@Component
public class MyListener {

    @RabbitListener(queues = "item_queue")
    public void myListener1(String message){
        System.out.println("收到的消息为: "+message);
    }
}
