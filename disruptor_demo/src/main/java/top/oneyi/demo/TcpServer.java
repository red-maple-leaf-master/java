package top.oneyi.demo;

import io.vertx.core.Handler;
import io.vertx.core.Vertx;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.net.NetClient;
import io.vertx.core.net.NetServer;
import io.vertx.core.net.NetSocket;
import io.vertx.core.parsetools.RecordParser;


public class TcpServer {
    public static void main(String[] args) {
        new TcpServer().startServ();
    }

    public void startServ() {
        Vertx vertx = Vertx.vertx();
        NetServer netServer = vertx.createNetServer();
        netServer.connectHandler(new ConnHandler());
        netServer.listen(1010, res -> {
            if (res.succeeded()) {

                System.out.println("连接成功");
            } else {
                System.out.println("连接失败");
            }
        });
    }

    private class ConnHandler implements Handler<NetSocket> {
        // 报文
        // 报头 [int 报文长度]
        // 报体 [byte []  数据]

        private static final int PACTET_HEADER_LENGTH = 4;

        @Override
        public void handle(NetSocket netSocket) {
            // 自定义解析器
            final RecordParser parser = RecordParser.newFixed(PACTET_HEADER_LENGTH);
            parser.setOutput(new Handler<Buffer>() {

                int bodyLength = -1;

                @Override
                public void handle(Buffer buffer) {
                    if (bodyLength == -1) {
                        // 读取包头
                        bodyLength = buffer.getInt(0);
                        parser.fixedSizeMode(bodyLength);
                    } else {
                        // 读取数据
                        byte[] bodyBytes = buffer.getBytes();
                        System.out.println("读到的数据" + new String(bodyBytes));
                        // 恢复现场
                        parser.fixedSizeMode(PACTET_HEADER_LENGTH);
                        bodyLength = -1;

                    }
                }
            });

            netSocket.handler(parser);
        }
    }
}
