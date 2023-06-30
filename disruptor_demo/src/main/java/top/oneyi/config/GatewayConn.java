package top.oneyi.config;

import io.vertx.core.buffer.Buffer;
import javax.annotation.PostConstruct;


import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import top.oneyi.thirdpart.bean.CommonMsg;
import top.oneyi.thirdpart.checksum.ByteCheckSum;
import top.oneyi.thirdpart.codec.BodyCodec;
import top.oneyi.thirdpart.codec.IBodyCodec;
import top.oneyi.thirdpart.codec.MsgCodec;
import top.oneyi.thirdpart.uuid.GudyUuid;

@Slf4j
@Component
public class GatewayConn {

    private ConnectionConfig connectionConfig;

    @PostConstruct
    private void init(){
        connectionConfig = new ConnectionConfig("127.0.0.1", 1010);
        connectionConfig.start();
        log.info("初始化客户端成功");
    }


    public void sendMsg(String name){
        byte[] data = null;
        try{
            // 对象  序列化 字节数组
            data=new BodyCodec().serialize(name);
        } catch (Exception e) {
            log.error("encode error for ordercmd:{}",name);
            return;
        }
        int length = data.length;
        CommonMsg msg = new CommonMsg();
        msg.setBodyLength(length); // 包长度
        msg.setBody(data); // 数据包

        msg.setChecksum(new ByteCheckSum().getChecksum(data));  // 校检编码
        msg.setMsgSrc((short) 1000);  // 起始地址  柜台的id
        msg.setMsgDst((short) 1001);  // 目标地址 网关id
        msg.setMsgNo(GudyUuid.getInstance().getUUID());  // 包编号
        msg.setStatus((byte) 0); // 正常状态  0  正常  1 异常
        msg.setMsgType((short) 0);  // 类型  0 新委托 1 取消委托
        connectionConfig.send(new MsgCodec().encodeToBuffer(msg));
    }

}
