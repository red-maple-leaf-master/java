package top.oneyi.demo;


import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import top.oneyi.thirdpart.bean.CommonMsg;
import top.oneyi.thirdpart.codec.IBodyCodec;
import top.oneyi.thirdpart.order.OrderCmd;


@Log4j2
@AllArgsConstructor
public class MsgHandler implements IMsgHandler {

    private IBodyCodec bodyCodec;

    @Override
    public void onCounterData(CommonMsg msg) {
        String orderCmd;

        try {
            // 序列化得到 oerderCmd 对象
            orderCmd=bodyCodec.deserialize(msg.getBody(),String.class);
            // 比较敏感 需要使用debug日志  最好在加一个判断
               /*if(log.isDebugEnabled()){
                log.debug("");
            }*/
            log.info("recv  orderCmd cmd:{}",orderCmd);



        } catch (Exception e) {
            log.error("decode order cmd error",e);
            e.printStackTrace();
        }
    }
}
