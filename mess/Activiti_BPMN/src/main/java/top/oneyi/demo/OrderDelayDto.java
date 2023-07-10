package top.oneyi.demo;

import lombok.Data;

import java.util.Date;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

/**
 * @author mashu
 * Date 2020/5/17 16:25
 */
@Data
public class OrderDelayDto implements Delayed {
    /**
     * 订单编号
     */
    private String orderCode;
    /**
     * 过期时间
     */
    private Date expirationTime;

    /**
     * 判断过期的策略：过期时间大于等于当前时间就算过期
     *
     * @param unit
     * @return
     */
    @Override
    public long getDelay(TimeUnit unit) {
        return unit.convert(this.expirationTime.getTime() - System.currentTimeMillis(), TimeUnit.NANOSECONDS);
    }

    /**
     * 订单加入队列的排序规则
     *
     * @param o
     * @return
     */
    @Override
    public int compareTo(Delayed o) {
        OrderDelayDto orderDelayDto = (OrderDelayDto) o;
        long time = orderDelayDto.getExpirationTime().getTime();
        long time1 = this.getExpirationTime().getTime();
        return Long.compare(time1, time);
    }
}