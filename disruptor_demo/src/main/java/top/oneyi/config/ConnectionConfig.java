package top.oneyi.config;


import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;
import io.vertx.core.Vertx;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.net.NetSocket;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;


import javax.validation.constraints.NotNull;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

@Slf4j
@RequiredArgsConstructor

public class ConnectionConfig {
    /**
     * 连接ip
     */
    @NotNull
    private String ip;
    /**
     * 连接端口
     */
    @NotNull
    private int port;
    /**
     * vertx实例
     */
    @NotNull
    private Vertx vertx;
    //  volatile  保证去取最新的数据
    private volatile NetSocket socket;

    public ConnectionConfig(String ip, int port) {
        vertx = Vertx.vertx();
        this.port = port;
        this.ip = ip;
        log.info("config初始化成功");
    }

    public void start() {
        vertx.createNetClient().connect(port, ip, new ClientConnectHandler());
        log.info("创建客户端成功");
        CompletableFuture.runAsync(() -> {
            log.info("开始轮询发送数据");
            while (true) {
                try {
                    // 从缓存队列去拿数据发送
                    Buffer msgBuffer = sendCache.poll(5, TimeUnit.SECONDS);

                    if (msgBuffer != null && msgBuffer.length() > 0 && socket != null) {
                        // 发送到server
                        log.info("拿到数据===> {} <=====",new String(msgBuffer.getBytes()));
                        socket.write(msgBuffer);
                    }
                } catch (InterruptedException e) {
                    System.out.println("msg send fail, continue ");
                    e.printStackTrace();
                }
            }

        });

    }

    //在发送之前增加一个缓存，让socket自己去缓存中获取数据进行发送
    private final BlockingQueue<Buffer> sendCache = new LinkedBlockingQueue<>();

    public boolean send(Buffer bufferMsg) {
        return sendCache.offer(bufferMsg);
    }

    private class ClientConnectHandler implements Handler<AsyncResult<NetSocket>> {

        @Override
        public void handle(AsyncResult<NetSocket> result) {
            if (result.succeeded()) {
                //拿到连接
                socket = result.result();
                log.info("成功拿到socket");

                // 关闭连接处理器
                socket.closeHandler(c -> {
                    System.out.println("关闭连接");
                    reconnect();
                });

                // 异常处理器
                socket.exceptionHandler(ex -> {
                    System.out.println("出现异常");
                    System.out.println("ex.getMessage() = " + ex.getMessage());
                    reconnect();
                });

            } else {
                System.out.println("连接失败 请求重连");
            }


        }

        private void reconnect() {
            vertx.setTimer(1000 * 5, res -> {
                System.out.println("重连");
                vertx.createNetClient().connect(port, ip, new ClientConnectHandler());
            });
        }
    }
}
