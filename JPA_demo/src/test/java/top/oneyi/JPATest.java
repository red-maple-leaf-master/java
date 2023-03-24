package top.oneyi;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.jpa.repository.query.Jpa21Utils;
import org.springframework.test.context.junit4.SpringRunner;
import top.oneyi.pojo.Customer;
import top.oneyi.repository.CustomerRepository;
import top.oneyi.utils.JPAUtil;


import java.util.List;

@RunWith(SpringRunner.class)//当前类为 springBoot 的测试类
@SpringBootTest(classes = SpringBootJPA.class)//加载 SpringBoot 启动类
public class JPATest {

    @Autowired
    private CustomerRepository customerRepository;

    @Test
    public void findAll(){
        List<Customer> admin = customerRepository.findByName("admin");
        for (Customer customer : admin) {
            System.out.println("customer = " + customer);
        }
    }

}
