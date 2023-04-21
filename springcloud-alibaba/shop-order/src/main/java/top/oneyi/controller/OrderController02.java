package top.oneyi.controller;


import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import top.oneyi.api.ProductApi;
import top.oneyi.pojo.po.Order;
import top.oneyi.pojo.po.Product;
import top.oneyi.service.OrderService;

import javax.annotation.Resource;

@RestController
@Slf4j
public class OrderController02 {

    @Resource
    private OrderService orderService;

    @Resource
    private ProductApi productApi;


    @RequestMapping("/order/prod/{pid}")
    public Order order(@PathVariable("pid") Integer pid) {
        log.info("接收到{}号商品的下单请求,接下来调用商品微服务查询此商品信息", pid);

        //调用商品微服务,查询商品信息
        Product product = productApi.findByPid(pid);
        log.info("查询到{}号商品的信息,内容是:{}", pid, JSON.toJSONString(product));
        //模拟一次网络延时
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //下单(创建订单)
        Order order = new Order();
        order.setUid(1);
        order.setUsername("测试用户");
        order.setPid(pid);
        order.setPname(product.getPname());
        order.setPprice(product.getPprice());
        order.setNumber(1);
        //为了不产生太多垃圾数据,暂时不做订单保存
        //orderService.createOrder(order);
        log.info("创建订单成功,订单信息为{}", JSON.toJSONString(order));
        return order;
    }

    @RequestMapping("/order/message")
    public String message() {
        return "高并发下的问题测试";
    }
}
