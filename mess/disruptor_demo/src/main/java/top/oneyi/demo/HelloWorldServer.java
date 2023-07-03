package top.oneyi.demo;

import io.vertx.core.Vertx;
import io.vertx.core.VertxOptions;
import io.vertx.core.eventbus.EventBus;



public class HelloWorldServer {
    public static void main(String[] args) {
      /*  System.out.print("Hello ");
        Vertx.vertx()
                .createHttpServer()
                .requestHandler(req ->
                        req.response().end("Hello World!")).listen(8080);
        System.out.println("Your World!");*/

        //  Event Bus  发布 订阅
/*        Vertx vertx = Vertx.vertx();
        EventBus eb = vertx.eventBus();*/
        EventBus eb = EventBusAlone.getInstance();
        // 注册处理器
/*        System.out.println("注册处理器");
        eb.consumer("news.uk.sport", message -> {
            System.out.println("I have received a message: " + message.body());
        });*/

        while (0 == 0){

            eb.send("news.uk.sport", "Yay! Someone kicked a ball");
        }
    }
}
