package top.oneyi.MQTT;

import com.google.common.collect.Maps;
import io.netty.handler.codec.mqtt.MqttQoS;
import io.vertx.core.Vertx;
import io.vertx.mqtt.MqttClient;

import java.util.Map;

public class client {
    public static void main(String[] args) {

    }


    public void start(Vertx vertx, int busPort, String busIp) {
        MqttClient mqttClient = MqttClient.create(vertx);
        mqttClient.connect(busPort, busIp, res -> {
            // 订阅消息总线
            System.out.println("连接上mqtt总线");
            // 存储消息的map
            Map<String, Integer> topic = Maps.newHashMap();
            // 监听  柜台 id
            topic.put("1", MqttQoS.AT_LEAST_ONCE.value());
            mqttClient.subscribe(topic).publishHandler(
                    h -> {
                        // 打印数据
                        System.out.println("h.payload() = " + h.payload());
                    });
        });
    }
}
