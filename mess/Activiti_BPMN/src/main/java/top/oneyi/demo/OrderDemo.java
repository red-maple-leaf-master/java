package top.oneyi.demo;

import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 测试 订单定时过期
 *
 * @author oneyi
 * @date 2023/3/21
 */

public class OrderDemo {
    public static void main(String[] args) {
        DelayQueue<OrderDelayDto> queue = new DelayQueue<>();
        OrderDelayDto o1 = new OrderDelayDto();
        //第一个订单，过期时间设置为一分钟后
        o1.setOrderCode("1001");
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MINUTE, 1);
        o1.setExpirationTime(calendar.getTime());


        OrderDelayDto o2 = new OrderDelayDto();
        //第二个订单，过期时间设置为现在
        o2.setOrderCode("1002");
        o2.setExpirationTime(new Date());
        //运行线程
        ExecutorService exec = Executors.newFixedThreadPool(1);
        exec.execute(new OrderCheckScheduler(queue));
        //往队列中放入数据
        queue.offer(o1);
        queue.offer(o2);
        exec.shutdown();

    }
}
