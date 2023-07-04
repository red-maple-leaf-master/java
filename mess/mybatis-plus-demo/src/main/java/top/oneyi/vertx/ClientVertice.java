package top.oneyi.vertx;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Vertx;

import io.vertx.core.buffer.Buffer;
import io.vertx.core.net.NetClient;
import io.vertx.core.net.NetSocket;

import javax.validation.constraints.NotNull;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;


public class ClientVertice extends AbstractVerticle {

    @NotNull
    private volatile NetSocket socket;

    private final BlockingQueue<String> sendCache = new LinkedBlockingQueue<>();

    // 往队列塞东西
    public void setCache(String massage) {
        sendCache.offer(massage);
    }


    @Override
    public void start() throws Exception {
        // 在这里可以通过this.vertx获取到当前的Vertx
        Vertx vertx = this.vertx;
        // 创建一个HttpClient
        NetClient client = vertx.createNetClient();
        // 连接server
        client.connect(8080, "localhost", res -> {
            if (res.succeeded()) {
                System.out.println("Connected!");
                socket = res.result();
            } else {
                System.out.println("Failed to connect: " + res.cause().getMessage());
            }
        });

        new Thread(() -> {
            while (true) {
                if (socket != null && sendCache.size() > 0) {
                    socket.write(sendCache.poll());
                }
            }
        }).start();


    }

    @Override
    public void stop() throws Exception {
        super.stop();
    }
}
