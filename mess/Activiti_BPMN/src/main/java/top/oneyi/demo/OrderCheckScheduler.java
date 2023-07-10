package top.oneyi.demo;

import java.util.concurrent.DelayQueue;

/**
 * @author mashu
 * Date 2020/5/17 14:27
 */
public class OrderCheckScheduler implements Runnable {

    /**
     * 延时队列
     */

    private DelayQueue<OrderDelayDto> queue;

    public OrderCheckScheduler(DelayQueue<OrderDelayDto> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        while (true) {
            try {
                OrderDelayDto take = queue.take();
                System.out.println("订单编号：" + take.getOrderCode() + " 过期时间：" + take.getExpirationTime());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}