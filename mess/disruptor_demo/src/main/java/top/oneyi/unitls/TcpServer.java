package top.oneyi.unitls;

import io.vertx.core.Handler;
import io.vertx.core.Vertx;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.net.NetServer;
import io.vertx.core.net.NetSocket;
import io.vertx.core.parsetools.RecordParser;
import lombok.extern.slf4j.Slf4j;
import top.oneyi.handler.IMsgHandler;
import top.oneyi.handler.MsgHandler;
import top.oneyi.thirdpart.bean.CommonMsg;
import top.oneyi.thirdpart.checksum.ByteCheckSum;
import top.oneyi.thirdpart.codec.BodyCodec;

/**
 * 网关服务  监听端口 接收服务
 */
@Slf4j
public class TcpServer {
    // 开启服务  监听端口
    public void startServ() {
        Vertx vertx = Vertx.vertx();
        NetServer netServer = vertx.createNetServer();
        netServer.connectHandler(new ConnHandler());
        int port = 1010;

        netServer.listen(port, res -> {
            if (res.succeeded()) {
                log.info("开始服务,监听端口, {}", port);
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
//    包头[ 包体长度 int + 校验和 byte + src short+ dst short + 消息类型 short + 消息状态 byte + 包编号 long ]
        private static final int PACKET_HEADER_LENGTH = 4 + 1 + 2 + 2 + 2 + 1 + 8;


        @Override
        public void handle(NetSocket socket) {

            IMsgHandler msgHandler = new MsgHandler(new BodyCodec());
            msgHandler.onConnect(socket);

            // 自定义解析器
            final RecordParser parser = RecordParser.newFixed(PACKET_HEADER_LENGTH);
            parser.setOutput(new Handler<Buffer>() {

                //    包头[ 包体长度 int + 校验和 byte + src short+ dst short + 消息类型 short + 消息状态 byte + 包编号 long ]
                int bodyLength = -1;
                byte checksum = -1;
                short msgSrc = -1;
                short msgDst = -1;
                short msgType = -1;
                byte status = -1;
                long packetNo = -1;

                @Override
                public void handle(Buffer buffer) {
                    if (bodyLength == -1) {
                        bodyLength = buffer.getInt(0);
                        checksum = buffer.getByte(4);
                        msgSrc = buffer.getShort(5);
                        msgDst = buffer.getShort(7);
                        msgType = buffer.getShort(9);
                        status = buffer.getByte(11);
                        packetNo = buffer.getShort(12);
                        // 动态修改长度
                        parser.fixedSizeMode(bodyLength);
                    } else {
                        byte[] bodyBytes = buffer.getBytes();
                        CommonMsg msg;
                        if (checksum != new ByteCheckSum().getChecksum(bodyBytes)) {
                            // 校验和不通过  报错
                            log.error("checksum fail, Illegal byte body exist from client:{}", socket.remoteAddress());
                            return;
                        } else if (msgDst != 1001) {
                            log.error("recv error msgDst dst:{} from client:{}", msgDst, socket.remoteAddress());
                            return;
                        } else {
                            msg = new CommonMsg();
                            msg.setBody(bodyBytes);
                            msg.setChecksum(checksum);
                            msg.setMsgSrc(msgSrc);
                            msg.setMsgDst(msgDst);
                            msg.setMsgType(msgType);
                            msg.setStatus(status);
                            msg.setMsgNo(packetNo);
                            msg.setBody(bodyBytes);
                            msg.setTimestamp(System.currentTimeMillis());
                            // 通知 msgHandler
                            msgHandler.onCounterData(msg);
                            // 恢复现场
                            bodyLength = -1;
                            checksum = -1;
                            msgSrc = -1;
                            msgDst = -1;
                            msgType = -1;
                            status = -1;
                            packetNo = -1;
                            parser.fixedSizeMode(PACKET_HEADER_LENGTH);
                        }
                    }
                }
            });

            socket.handler(parser);
        }
    }
}
