package top.oneyi;


import org.aspectj.weaver.ast.Or;
import org.junit.Test;
import org.junit.platform.commons.util.StringUtils;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.test.context.junit4.SpringRunner;
import top.oneyi.pojo.Book;
import top.oneyi.pojo.Customer;
import top.oneyi.repository.BookRepository;
import top.oneyi.repository.CustomerRepository;
import top.oneyi.pojo.Order;
import top.oneyi.repository.OrderRepository;


import javax.persistence.criteria.*;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@RunWith(SpringRunner.class)//当前类为 springBoot 的测试类
@SpringBootTest(classes = SpringBootJPA.class)//加载 SpringBoot 启动类
public class JPATest {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Test
    public void findAll() {
        List<Customer> all = customerRepository.findAll();

        Customer customer = customerRepository.findOne(getSpecificationForTask(all.get(0))).orElse(null);
        System.out.println("customer = " + customer);
    }


    private Specification<Customer> getSpecificationForTask(Customer customer) {
        return (root, query, builder) -> {
            // for params
            List<Predicate> predicates = new ArrayList<>();
            if(customer.getId() != null){
                // 精准匹配
                predicates.add(builder.equal(root.get("id"),customer.getId()));
            }
            // 模糊查询 + or条件
            if(!StringUtils.isBlank(customer.getName())){
                predicates.add(builder.like(root.get("name"),"%"+customer.getName()+"%"));
            }

            if(customer.getAge() != null){
                predicates.add(builder.greaterThanOrEqualTo(root.get("age"),customer.getAge()));
            }
            return  query.where(predicates.toArray(new Predicate[predicates.size()])).getRestriction();
        };
    }


    @Test
    public void insertBook() {
        Book book = new Book();
        book.setId(1L);
        book.setBookAuthor("里程");
        book.setBookName("平凡的世界");
        book.setBookPrice("34");
        List<Order> orders = orderRepository.findByBookName("平凡的世界");
        book.setOrderList(orders);
        bookRepository.save(book);
    }

    @Test
    public void insertOrder() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        String format = sdf.format(new Date());
        Order order = new Order();
        order.setBookName("平凡的世界");
        List<Customer> admin1 = customerRepository.findByName("admin2");
        // 手动设置 用户
        order.setCustomer(admin1.get(0));
        order.setOrderNum(Long.valueOf(format + String.format("%04d", 1)));
        order.setCustomerName(admin1.get(0).getName());
        List<Book> all = bookRepository.findAll();
        order.setBookList(all);


        orderRepository.save(order);
    }

    @Test
    public void deleteOrder(){
        orderRepository.deleteById(8L);
    }

    public String getNo(Class obs) {
        String guaranteeNumber = null;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
            String format = sdf.format(new Date());
            Constructor declaredConstructor = obs.getDeclaredConstructor();
            Object o = declaredConstructor.newInstance();

            Field id = obs.getDeclaredField("id");
            id.setAccessible(true);
            Object o1 = id.get(o);
            guaranteeNumber = format + String.format("%04d", o1);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return guaranteeNumber;

    }

}
