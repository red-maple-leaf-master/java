package top.oneyi.controller;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import top.oneyi.api.ProductApi;
import top.oneyi.pojo.po.Order;
import top.oneyi.pojo.po.Product;
import top.oneyi.service.OrderService;

import javax.annotation.Resource;

@RestController
@Slf4j
public class OrderController {
    @Resource
    private RestTemplate restTemplate;
    @Resource
    private OrderService orderService;
    @Autowired
    private DiscoveryClient discoveryClient;
    @Autowired
    private ProductApi productApi;

    //准备买1件商品
    @GetMapping("/order/prod/{pid}")
    public Order order(@PathVariable("pid") Integer pid) {
/*        // 动态获取端口号和主机号访问对应微服务
        ServiceInstance serviceInstance = discoveryClient.getInstances("service-product").get(0);
        String urls = serviceInstance.getHost() + ":" + serviceInstance.getPort();

        log.info(">>客户下单，这时候要调用商品微服务查询商品信息");
        //通过restTemplate调用商品微服务
        Product product = restTemplate.getForObject(
                "http://"+ urls +"/product/" + pid, Product.class);*/
        // 使用nacos 和 openFeign 去动态调用接口
        Product product = productApi.findByPid(pid);
        log.info(">>商品信息,查询结果:" + JSON.toJSONString(product));
        Order order = new Order();
        order.setUid(1);
        order.setUsername("测试用户");
        order.setPid(product.getPid());
        order.setPname(product.getPname());
        order.setPprice(product.getPprice());
        order.setNumber(1);
        orderService.save(order);
        return order;
    }
}
