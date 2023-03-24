package top.oneyi;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.query.Jpa21Utils;
import org.springframework.test.context.junit4.SpringRunner;
import top.oneyi.pojo.Customer;
import top.oneyi.repository.CustomerRepository;
import top.oneyi.utils.JPAUtil;


import javax.persistence.criteria.*;


@RunWith(SpringRunner.class)//当前类为 springBoot 的测试类
@SpringBootTest(classes = SpringBootJPA.class)//加载 SpringBoot 启动类
public class JPATest {

    @Autowired
    private CustomerRepository customerRepository;

    @Test
    public void findAll(){
        Specification<Customer> spec = (root,query,cb)->{
            //1.获取比较的属性
            Path<Object> name = root.get("name");
            //2.构造查询条件
            Predicate predicate = cb.equal(name, "海之蓝");//进行精准匹配
            return predicate;
        };
        Customer one = customerRepository.findOne(spec).orElse(null);
        System.out.println(one);
    }

}
