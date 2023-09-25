package com.example.mqtt_client2.config;

import org.eclipse.paho.client.mqttv3.MqttException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 1. @author WXY
 2. @date 2022/6/29 20:42
 */
@Configuration
public class MqttConfiguration {

    private static final Logger log = LoggerFactory.getLogger(MqttConfiguration.class);
    @Value("${mqtt.host}")
    String host;
    @Value("${mqtt.username}")
    String username;
    @Value("${mqtt.password}")
    String password;
    @Value("${mqtt.clientId}")
    String clientId;
    @Value("${mqtt.timeout}")
    int timeOut;
    @Value("${mqtt.keepalive}")
    int keepAlive;
    @Value("${mqtt.topic1}")
    String topic1;


    @Bean//注入spring
    public MyMQTTClient myMQTTClient() {
        MyMQTTClient myMQTTClient = new MyMQTTClient(host, username, password, clientId, timeOut, keepAlive);
        for (int i = 0; i < 10; i++) {
            try {
                myMQTTClient.connect();
                //不同的主题
             //   myMQTTClient.subscribe(topic1, 1);
             //   myMQTTClient.subscribe(topic2, 1);
             //   myMQTTClient.subscribe(topic3, 1);
             //   myMQTTClient.subscribe(topic4, 1);
                return myMQTTClient;
            } catch (MqttException e) {
                log.error("MQTT connect exception,connect time = " + i);
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e1) {
                    e1.printStackTrace();
                }
            }
        }
        return myMQTTClient;
    }
     public String getTopic1() {
        return topic1;
    }

    public void setTopic1(String topic1) {
        this.topic1 = topic1;
    }
}
