package top.one.controller;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.one.config.RabbitMQConfig;
import top.one.service.RabbitService;

@RequestMapping("/test")
@RestController
public class RabbitController {

    @Autowired
    private RabbitService rabbitService;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    /**
     * 消息生产者
     * @param msg
     * @param key
     * @return
     */
    @GetMapping("/sendMsg")
    public String sendMsg(String msg,String key){
        // 参数1 交换机名称  参数2 路由key  参数 3 消息本体
        rabbitTemplate.convertAndSend(RabbitMQConfig.ITEM_TOPIC_EXCHANGE,key,msg);
        return "发送成功";
    }


}
