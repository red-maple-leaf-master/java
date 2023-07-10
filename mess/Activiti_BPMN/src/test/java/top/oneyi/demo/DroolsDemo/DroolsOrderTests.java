package top.oneyi.demo.DroolsDemo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import top.oneyi.ActivtiSpringBootApplication;
import top.oneyi.pojo.Order;
import top.oneyi.pojo.Person;

import javax.annotation.Resource;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * 需求
 * 计算额外积分金额 规则如下: 订单原价金额
 * 100以下, 不加分
 * 100-500 加100分
 * 500-1000 加500分
 * 1000 以上 加1000分
 */
@RunWith(SpringRunner.class)//当前类为 springBoot 的测试类
@SpringBootTest(classes = ActivtiSpringBootApplication.class)//加载 SpringBoot 启动类
public class DroolsOrderTests {
    @Resource
    private KieContainer kieContainer;

    /**
     * 未用规则引擎
     *
     * @throws Exception
     */
    @Test
    public void Test() throws Exception {
        List<Order> orderList = getInitData();
        for (Order order : orderList) {
            if (order.getAmout() <= 100) {
                order.setScore(0);
                addScore(order);
            } else if (order.getAmout() > 100 && order.getAmout() <= 500) {
                order.setScore(100);
                addScore(order);
            } else if (order.getAmout() > 500 && order.getAmout() <= 1000) {
                order.setScore(500);
                addScore(order);
            } else {
                order.setScore(1000);
                addScore(order);
            }

        }
    }

    /**
     * 使用规则引擎
     *
     * @throws Exception
     */
    @Test
    public void droolsOrderTest() throws Exception {
        KieSession kieSession = kieContainer.newKieSession();
        List<Order> orderList = getInitData();
        for (Order order : orderList) {
            // 1-规则引擎处理逻辑
            kieSession.insert(order);
            kieSession.fireAllRules();
            // 2-执行完规则后, 执行相关的逻辑
            addScore(order);
        }
        kieSession.dispose();
    }

    @Test
    public void droolsPersonTest() {
        KieSession kieSession = kieContainer.newKieSession();
        Person person = new Person();

        person.setAge(55);
        person.setName("bob");

        kieSession.insert(person);
        kieSession.fireAllRules();
        kieSession.dispose();
    }


    private static void addScore(Order o) {
        System.out.println("用户" + o.getUser() + "享受额外增加积分: " + o.getScore());
    }

    private static List<Order> getInitData() throws Exception {
        List<Order> orderList = new ArrayList<>();
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        {
            Order order = new Order();
            order.setAmout(80);
            order.setBookingDate(df.parse("2015-07-01"));
            order.setUser("name1");
            order.setScore(111);
            orderList.add(order);
        }
        {
            Order order = new Order();
            order.setAmout(200);
            order.setBookingDate(df.parse("2015-07-02"));

            order.setUser("name2");
            orderList.add(order);
        }
        {
            Order order = new Order();
            order.setAmout(800);
            order.setBookingDate(df.parse("2015-07-03"));
            order.setUser("name3");
            orderList.add(order);
        }
        {
            Order order = new Order();
            order.setAmout(1500);
            order.setBookingDate(df.parse("2015-07-04"));
            order.setUser("name4");
            orderList.add(order);
        }
        return orderList;
    }
}
