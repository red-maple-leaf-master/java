package top.oneyi.vertx;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Vertx;
import io.vertx.core.http.HttpServer;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.core.net.NetServer;
import io.vertx.core.net.NetServerOptions;
import top.oneyi.vertx.demo.FirstVerticle;

public class ServerVertice extends AbstractVerticle {
    public static void main(String[] args) {
        ServerVertice verticle = new ServerVertice();

        Vertx vertx = Vertx.vertx();

        // 部署verticle，这里会调用start方法
        vertx.deployVerticle(verticle);
    }

    @Override
    public void start() throws Exception {
        // 在这里可以通过this.vertx获取到当前的Vertx
        Vertx vertx = this.vertx;

        NetServerOptions options = new NetServerOptions()
                .setLogActivity(true);

        // 创建一个HttpServer
        NetServer server = vertx.createNetServer(options);
        // 连接监听收到的通知
        server.connectHandler(netSocket -> {
            // 读取到数据
            netSocket.handler(buffer -> {
                System.out.println("I received some bytes: " + buffer.length());
                System.out.println("I received message " + buffer.toString());
            });
        });
        // 建立server监听
        server.listen(8080, res -> {
            if (res.succeeded()) {
                System.out.println("连接成功");
            } else {
                System.out.println("连接失败");
            }
        });

    }

    @Override
    public void stop() throws Exception {
        super.stop();
    }
}
