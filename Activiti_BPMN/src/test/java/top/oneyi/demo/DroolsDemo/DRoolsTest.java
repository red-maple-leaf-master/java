package top.oneyi.demo.DroolsDemo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import top.oneyi.ActivtiSpringBootApplication;
import top.oneyi.pojo.Order;
import top.oneyi.pojo.Person;

@RunWith(SpringRunner.class)//当前类为 springBoot 的测试类
@SpringBootTest(classes = ActivtiSpringBootApplication.class)//加载 SpringBoot 启动类
public class DRoolsTest {
    private static KieContainer container = null;
    private KieSession statefulKieSession = null;

    @Test
    public void test01(){
        KieServices kieServices = KieServices.Factory.get();
        if(kieServices == null){
            System.out.println("未读取到规则");
            return;
        }
        container = kieServices.getKieClasspathContainer();
        statefulKieSession = container.newKieSession();
        Person person = new Person();

        person.setAge(12);
        person.setName("Test");

        statefulKieSession.insert(person);
        statefulKieSession.fireAllRules();
        statefulKieSession.dispose();
    }


    @Test
    public void test02(){
        String str = "2023-05-14T16:00:00.000Z";

    }

    @Test
    public void test03(){
        KieServices kieServices = KieServices.Factory.get();
        KieContainer kieClasspathContainer = kieServices.getKieClasspathContainer();
        //会话对象，用于和规则引擎交互
        KieSession kieSession = kieClasspathContainer.newKieSession();

        //构造订单对象，设置原始价格，由规则引擎根据优惠规则计算优惠后的价格
//        Order order = new Order();
//        order.setOriginalPrice(210D);
//
//        //将数据提供给规则引擎，规则引擎会根据提供的数据进行规则匹配
//        kieSession.insert(order);
//
//        //激活规则引擎，如果规则匹配成功则执行规则
//        kieSession.fireAllRules();
//        //关闭会话
//        kieSession.dispose();
//        System.out.println("优惠前原始价格：" + order.getOriginalPrice() +
//                "，优惠后价格：" + order.getRealPrice());
    }


}
