package top.oneyi.demo;

import com.lmax.disruptor.*;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.dsl.EventHandlerGroup;
import com.lmax.disruptor.dsl.ProducerType;
import top.oneyi.factory.OrderFactory;
import top.oneyi.handler.OrderHandler;
import top.oneyi.handler.OrderHandler2;
import top.oneyi.handler.OrderHandler3;
import top.oneyi.pojo.Order;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {

    public static void main(String[] args) throws InterruptedException {
//        test01();
//        test02();
        test03();
    }

    public static void test01() throws InterruptedException {

        //创建订单工厂
        OrderFactory orderFactory = new OrderFactory();

        //ringbuffer的大小
        int RINGBUFFER_SIZE = 1024;

        //创建disruptor
        Disruptor<Order> disruptor = new Disruptor<Order>(orderFactory, RINGBUFFER_SIZE, Executors.defaultThreadFactory());

        //设置事件处理器 即消费者
        disruptor.handleEventsWith(new OrderHandler());

        disruptor.start();

        RingBuffer<Order> ringBuffer = disruptor.getRingBuffer();

        //-------------生产数据
        for (int i = 0; i < 30; i++) {

            long sequence = ringBuffer.next();

            Order order = ringBuffer.get(sequence);

            order.setId(i);

            ringBuffer.publish(sequence);
            System.out.println(Thread.currentThread().getName() + " 生产者发布一条数据:" + sequence + " 订单ID：" + i);
            Thread.sleep(500);
        }

        Thread.sleep(1000);

        disruptor.shutdown();
    }

    /**
     * 场景比较简单的话 就单独使用RingBuffer WorkerPool
     *
     * @throws InterruptedException
     */
    public static void test02() throws InterruptedException {
       /* ExecutorService executor = Executors.newFixedThreadPool(3);
        RingBuffer<Order> ringBuffer = RingBuffer.create(ProducerType.SINGLE,new OrderFactory(),1024,new YieldingWaitStrategy());
        WorkerPool<Order> workerPool = new WorkerPool<Order>(ringBuffer,ringBuffer.newBarrier(),new IgnoreExceptionHandler(), new OrderHandler());

        workerPool.start(executor);

        //-------------生产数据
        for(int i = 0 ; i < 30 ; i++){

            long sequence = ringBuffer.next();

            Order order = ringBuffer.get(sequence);
            order.setId(i);

            ringBuffer.publish(sequence);

            System.out.println(Thread.currentThread().getName() + " 生产者发布一条数据:" + sequence + " 订单ID：" + i);

            Thread.sleep(500);
        }

        Thread.sleep(1000);

        workerPool.halt();
        executor.shutdown();*/
    }

    /**
     * 一个生产者 对应  多个消费者
     * @throws InterruptedException
     */
    public static void test03() throws InterruptedException {
        //创建订单工厂
        OrderFactory orderFactory = new OrderFactory();

        //ringbuffer的大小
        int RINGBUFFER_SIZE = 1024;

        //创建disruptor
        Disruptor<Order> disruptor = new Disruptor<Order>(orderFactory,RINGBUFFER_SIZE,Executors.defaultThreadFactory());

        //设置事件处理器 即消费者
//        EventHandlerGroup<Order> eventHandlerGroup = disruptor.handleEventsWith(new OrderHandler(),new OrderHandler2());
//        eventHandlerGroup.then(new OrderHandler3());

        EventHandlerGroup<Order> eventHandlerGroup = disruptor.handleEventsWith(new OrderHandler(),new OrderHandler2(),new OrderHandler3());
//        eventHandlerGroup.then(new OrderHandler3());

        disruptor.start();

        RingBuffer<Order> ringBuffer = disruptor.getRingBuffer();

        //-------------生产数据
        for(int i = 0 ; i < 3 ; i++){

            long sequence = ringBuffer.next();

            Order order = ringBuffer.get(sequence);

            order.setId(i);

            ringBuffer.publish(sequence);
            System.out.println(Thread.currentThread().getName() + " 生产者发布一条数据:" + sequence + " 订单ID：" + i);

        }

        Thread.sleep(5000);
        disruptor.shutdown();
    }

}