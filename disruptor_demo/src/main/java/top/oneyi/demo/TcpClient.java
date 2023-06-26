package top.oneyi.demo;

import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;
import io.vertx.core.Vertx;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.net.NetSocket;

public class TcpClient {

    public static void main(String[] args) {
        new TcpClient().startClient();
    }


    private Vertx vertx;

    public void startClient() {
        vertx = Vertx.vertx();
        vertx.createNetClient().connect(1010, "127.0.0.1", new ClientConnectHandler());
    }


    private class ClientConnectHandler implements Handler<AsyncResult<NetSocket>> {


        @Override
        public void handle(AsyncResult<NetSocket> result) {
            if (result.succeeded()) {
                //发送消息
                NetSocket socket = result.result();

                // 关闭连接处理器
                socket.closeHandler(c -> {
                    System.out.println("关闭连接");
                    reconnect();
                });


                // 异常处理器
                socket.exceptionHandler(ex -> {
                    System.out.println("出现异常");
                    System.out.println("ex.getMessage() = " + ex.getMessage());
                });

                // 发送消息
                byte[] bytes = "你好  我我是加快进度".getBytes();
                int length = bytes.length;
                Buffer buffer = Buffer.buffer().appendInt(length).appendBytes(bytes);

                socket.write(buffer);

            } else {
                System.out.println("连接失败 请求重连");
                reconnect();
            }
        }

        private void reconnect() {
            vertx.setTimer(1000 * 5, res -> {
                System.out.println("重连");
                vertx.createNetClient().connect(1010, "127.0.0.1", new ClientConnectHandler());
            });
        }
    }
}
