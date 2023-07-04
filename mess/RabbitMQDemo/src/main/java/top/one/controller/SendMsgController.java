package top.one.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
@RequestMapping("/ttl")
@Api(tags = "消息生产者")
public class SendMsgController {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    @ApiOperation(value = "发送消息")
    @GetMapping("/sendMsg/{message}")
    public void sendMsg(@PathVariable String message) {
        System.out.println("当前时间：" + new Date() + "收到死信队列信息{}" + message);
        rabbitTemplate.convertAndSend("X", "XA", "消息来自 ttl 为 10S 的队列: " + message);
        rabbitTemplate.convertAndSend("X", "XB", "消息来自 ttl 为 40S 的队列: " + message);
    }

    /**
     * 看起来似乎没什么问题，但是在最开始的时候，就介绍过如果使用在消息属性上设置 TTL 的方式，消
     * 息可能并不会按时“死亡“，因为 RabbitMQ 只会检查第一个消息是否过期，如果过期则丢到死信队列，
     * 如果第一个消息的延时时长很长，而第二个消息的延时时长很短，第二个消息并不会优先得到执行。
     * @param message
     * @param ttlTime
     */
    @ApiOperation(value = "发送指定延时时间消息")
    @GetMapping("sendExpirationMsg/{message}/{ttlTime}")
    public void sendMsg(@PathVariable String message,@PathVariable String ttlTime) {
        rabbitTemplate.convertAndSend("X", "XC", message, correlationData ->{
            correlationData.getMessageProperties().setExpiration(ttlTime);
            return correlationData;
        });
        System.out.println("当前时间：" + new Date() + "发送一条时长 "+ttlTime + "毫秒 TTL 信息给队列C 发送的消息为:"+ message);

    }

}
