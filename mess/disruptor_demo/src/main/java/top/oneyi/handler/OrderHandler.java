package top.oneyi.handler;

import com.lmax.disruptor.EventHandler;
import com.lmax.disruptor.WorkHandler;
import top.oneyi.pojo.Order;

public class OrderHandler implements EventHandler<Order>, WorkHandler<Order> {

    @Override
    public void onEvent(Order order, long l, boolean b) throws Exception {

        System.out.println(Thread.currentThread().getName() + " 消费者处理中:" + l + "我是一号消费者");
        order.setInfo("info" + order.getId());
        order.setPrice(Math.random());
        Thread.sleep(1000);
    }

    @Override
    public void onEvent(Order order) throws Exception {
        System.out.println(Thread.currentThread().getName() + " 消费者处理中:" + order.toString());
        order.setInfo("info" + order.getId());
        order.setPrice(Math.random());

        Thread.sleep(1000);
    }
}