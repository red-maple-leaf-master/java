package top.one.service;

import org.springframework.stereotype.Service;


public interface RabbitService {
    /**
     * 发送简单消息到队列
     */
    void sendSimpleMsg() throws Exception;

    /**
     * 接收到简单消息队列的消息
     * @return
     */
    void recvSimpleMsg() throws Exception;
}
