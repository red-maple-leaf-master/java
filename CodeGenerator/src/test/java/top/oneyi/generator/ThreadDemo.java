package top.oneyi.generator;

import com.alibaba.druid.util.StringUtils;
import lombok.extern.java.Log;
import org.junit.jupiter.api.Test;

import java.util.concurrent.*;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: wanyi
 * @Date: 2023/12/14/9:38
 */
@Log
public class ThreadDemo {
    /**
     * 用id查询订单表数据
     * @param orderId 订单id
     * @return
     */
    private OrderInfo getOrderById(String orderId){
        OrderInfo orderInfo =new OrderInfo();
        orderInfo.setUserId("111");
        orderInfo.setOrderId(orderId);
        orderInfo.setGoodId("222");
        //模拟查询数据库业务 200ms
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return orderInfo;
    }

    /**
     * 查询用户信息
     * @param userId 用户id
     * @return
     */
    private String getUserInfo(String userId){
        //模拟rpc查询用户数据 900ms
        try {
            Thread.sleep(900);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return "张三";
    }

    /**
     * 查询商品信息
     * @param goodsId 商品id
     * @return
     */
    private String getGoodsInfo(String goodsId){
        //模拟rpc查询商品数据 2000ms
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return "苹果";
    }

    /**
     * 获取订单数据
     * @param orderId 订单id
     * @return
     */
    public OrderInfo getOrderInfo(String orderId){
        //开始时间
        long startTime = System.currentTimeMillis();
        log.info("查询订单数据，订单id: "+ orderId);
        //获取到订单数据
        OrderInfo orderInfo =getOrderById(orderId);


        System.out.println("获取到订单表数据为: "+ orderInfo.toString()+",接口耗时为："+ (System.currentTimeMillis()-startTime));
        //创建一个核心线程数10，最大线程容量20 ，60s存活，有界容量是20的队列
        ThreadPoolExecutor executor = new ThreadPoolExecutor(10,20,
                60, TimeUnit.SECONDS,new ArrayBlockingQueue<>(20));

        //查询订单的用户相关信息
        Future<String> userFuture = executor.submit(() -> {
            //获取用户信息
            return getUserInfo(orderInfo.getUserId());
        });

        //查询订单的商品相关信息
        Future<String> goodsFuture = executor.submit(() -> {
            //获取商品信息
            return getGoodsInfo(orderInfo.getGoodId());
        });

        try {
            //用户信息
            String userName = userFuture.get();
            orderInfo.setUserName(userName);
            //商品信息
            String goodName = goodsFuture.get();
            orderInfo.setGoodName(goodName);
        } catch (Exception e){
            //其他处理异常逻辑
            System.out.println("查询订单数据异常，订单id： " + orderId + " 抛出的异常为:  "+e);
        } finally {
            //关闭线程池
            executor.shutdown();
        }
        System.out.println("订单的详细信息："+ orderInfo.toString() +",接口耗时为： " + (System.currentTimeMillis()-startTime) +" ms");
        return orderInfo;
    }

    @Test
    public void test(){
        OrderInfo orderInfo = getOrderInfo("1");

    }

}
