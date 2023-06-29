package top.oneyi.demo;

import io.vertx.core.Vertx;
import io.vertx.core.eventbus.EventBus;

public class sendClient {
    private final static EventBus eb = EventBusAlone.getInstance();
    public static void main(String[] args) {
/*        Vertx vertx = Vertx.vertx();
        EventBus eb = vertx.eventBus();*/

        // 发送消息
        System.out.println("开始发送消息");


        eb.consumer("news.uk.sport", c->{
            System.out.println("c.body() = " + c.body());
        });
    }


}
