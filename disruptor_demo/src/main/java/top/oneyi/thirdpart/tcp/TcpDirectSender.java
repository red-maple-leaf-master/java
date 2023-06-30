package top.oneyi.thirdpart.tcp;

import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;
import io.vertx.core.Vertx;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.net.NetSocket;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

@Log4j2
@RequiredArgsConstructor
public class TcpDirectSender {

    @NonNull
    private String ip;

    @NonNull
    private int port;

    @NonNull
    private Vertx vertx;
    //  volatile  保证去取最新的数据
    private volatile NetSocket socket;

    /*------------------------------*/
    public void startup() {
        // 创建客户端
        vertx.createNetClient().connect(port, ip, new ClientConnHandler());
        // 单独开一个线程发送数据
        CompletableFuture.runAsync(() -> {
            // 死循环
            while (true) {
                try {
                    // 从缓存队列去拿数据发送
                    Buffer msgBuffer = sendCache.poll(5, TimeUnit.SECONDS);

                    if (msgBuffer != null && msgBuffer.length() > 0 && socket != null) {
                        // 发送到网关
                        socket.write(msgBuffer);
                    }

                } catch (Exception e) {
                    log.error("msg send fail,continue");
                }
            }
        });
    }


    //在发送之前增加一个缓存，让socket自己去缓存中获取数据进行发送
    private final BlockingQueue<Buffer> sendCache = new LinkedBlockingQueue<>();

    public boolean send(Buffer bufferMsg) {
        return sendCache.offer(bufferMsg);
    }

    /**
     * 连接处理器
     */
    private class ClientConnHandler implements Handler<AsyncResult<NetSocket>> {
        // 尝试重连
        private void reconnect() {
            vertx.setTimer(1000 * 5, r -> {
                log.info("try reconnect to server {}:{}", ip, port);
                vertx.createNetClient().connect(port, ip, new ClientConnHandler());
            });
        }


        @Override
        public void handle(AsyncResult<NetSocket> result) {
            if (result.succeeded()) {
                log.info("connect success to remote {}:{}", ip, port);
                // 拿到连接  为了去其他地方写数据  做成成员变量
                socket = result.result();

                // 结束处理器
                socket.closeHandler(close -> {
                    log.info("connect to remote {} closed", socket.remoteAddress());
                    reconnect();
                });

                // 异常处理器
                socket.exceptionHandler(e -> {
                    log.error("error exist", e.getCause());
                });
            }
        }


    }
}
