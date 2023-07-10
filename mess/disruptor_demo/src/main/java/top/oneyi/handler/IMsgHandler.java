package top.oneyi.handler;

import io.vertx.core.net.NetSocket;
import top.oneyi.thirdpart.bean.CommonMsg;


public interface IMsgHandler {

    // 连接的消息
    default void onConnect(NetSocket socket) {
    }

    ;

    // 关闭的消息
    default void onDisConnect(NetSocket socket) {
    }

    ;

    // 异常的消息
    default void onException(NetSocket socket, Throwable e) {
    }

    ;

    // 柜台发过来的消息
    void onCounterData(CommonMsg msg);
}
