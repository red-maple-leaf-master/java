package top.oneyi.thirdpart.hq;

import lombok.Builder;
import lombok.ToString;
import top.oneyi.thirdpart.order.OrderStatus;


import java.io.Serializable;

/**
 * MatchData和MacthEvent两个类处在不同的业务逻辑当中，MatchData是为了丢到总线上去给柜台或者其他的订阅的终端来看的，
 这个类对所有的服务都公开，MacthEvent是在disruptor整个运转的过程当中内部的一个event，在MacthEvent当中会有一个copy方法，
 就是把一个MacthEvent转换成一个MatchData，这个MatchData可以直接丢到总线上去给所有人用
 */
@ToString
@Builder
public class MatchData implements Serializable {
    public long timestamp;

    public short mid;

    public long oid;

    public OrderStatus status;

    public long tid;

    //撤单数量 成交数量
    public long volume;

    public long price;
}
