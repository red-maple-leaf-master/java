package top.oneyi.vertx.demo;

import io.vertx.core.Vertx;
import io.vertx.core.net.NetSocket;
import top.oneyi.vertx.ClientVertice;

public class SecondVerticle {
    public static void main(String[] args) throws InterruptedException {
        ClientVertice verticle = new ClientVertice();
        Vertx vertx = Vertx.vertx();
        // 部署verticle，这里会调用start方法
        vertx.deployVerticle(verticle);

        while (true) {
            Thread.sleep(2000L);
            verticle.setCache("你好啊");
        }
    }

}
