package top.one.common;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import java.util.HashMap;
import java.util.Map;

@Component
public class ScheduleTestRabbitMqSender {

    @Autowired
    RabbitTemplate rabbitTemplate;

    @Scheduled(cron = "0/6 * * * * ?")
    public void SendRabbitmqMessage() {

        Map<String, Object> mapA = new HashMap<>();
        mapA.put("message", "mapA的数据,string格式");

        Map<String, Object> mapB = new HashMap<>();
        mapB.put("message", "mapB的数据，map格式");
		
		//发送string类型数据
        rabbitTemplate.convertAndSend("item_topic_exchange","item.A", mapA.toString());
        //发送map类型数据
        rabbitTemplate.convertAndSend("item_topic_exchange","item.B", mapB.toString());
        System.out.println("数据发送成功！！！");
    }
}

