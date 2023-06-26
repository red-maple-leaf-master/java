package top.oneyi.demo;

import io.vertx.core.Vertx;
import io.vertx.core.eventbus.EventBus;

public class sendClient {
    public static void main(String[] args) {
/*        Vertx vertx = Vertx.vertx();
        EventBus eb = vertx.eventBus();*/
        EventBus eb = EventBusAlone.getInstance();
        // 发送消息
        System.out.println("开始发送消息");
        eb.send("news.uk.sport", "Yay! Someone kicked a ball");
    }
}
