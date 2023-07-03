package top.oneyi.handler;


import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import top.oneyi.unitls.CmdContainer;
import top.oneyi.thirdpart.bean.CommonMsg;
import top.oneyi.thirdpart.codec.IBodyCodec;


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

            if(!CmdContainer.getInstance().cache(orderCmd)){
                //插入失败  打印日志  最好的打印一些业务信息 来排错 队列的长度
                log.error("gateway queue insert fail,queue length:{},order:{}",
                        CmdContainer.getInstance().size(),orderCmd);
            }


        } catch (Exception e) {
            log.error("decode order cmd error",e);
            e.printStackTrace();
        }
    }
}
