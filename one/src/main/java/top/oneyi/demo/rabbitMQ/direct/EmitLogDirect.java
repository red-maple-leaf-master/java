package top.oneyi.demo.rabbitMQ.direct;

import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import top.oneyi.util.RabbitMqUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * 生产者
 * @author oneyi
 * @date 2023/2/16
 */

public class EmitLogDirect {
    private static final String EXCHANGE_NAME = "direct_logs";

    public static void main(String[] args) throws Exception {
        final Channel channel = RabbitMqUtils.getChannel();
        // 声明交换机
        channel.exchangeDeclare(EXCHANGE_NAME, BuiltinExchangeType.DIRECT);
        //创建多个 bindingKey
        Map<String, String> bindingKeyMap = new HashMap<>();
        bindingKeyMap.put("info","普通 info 信息");
        bindingKeyMap.put("warning","警告 warning 信息");
        bindingKeyMap.put("error","错误 error 信息");
        //debug 没有消费这接收这个消息 所有就丢失了
        bindingKeyMap.put("debug","调试 debug 信息");
        for (Map.Entry<String, String> stringStringEntry : bindingKeyMap.entrySet()) {
             String key = stringStringEntry.getKey();
             String value = stringStringEntry.getValue();
            channel.basicPublish(EXCHANGE_NAME,key,null,value.getBytes());
            System.out.println("生产者发出 消息: "+value);
        }
    }

}
