package top.one.listener;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import sun.plugin2.message.Message;

import java.util.Map;
import java.util.Objects;

import static org.apache.logging.log4j.message.MapMessage.MapFormat.JSON;

/**
 * 监听某个队列的消息
 *
 * @author oneyi
 * @date 2023/2/14
 */

@Component
@RabbitListener(queues = {"item_queueA", "item_queueB"})
public class MyListener {


    @RabbitHandler
    public void myListener(String message) {
        System.out.println("收到的消息为: " + message);
    }

    @RabbitHandler
    public void myListener(Map message) {
        System.out.println("收到的消息为: " + message);
    }
}
