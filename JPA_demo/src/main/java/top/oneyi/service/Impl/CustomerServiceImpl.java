package top.oneyi.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.oneyi.pojo.Customer;
import top.oneyi.repository.CustomerRepository;
import top.oneyi.service.CustomerService;

import javax.annotation.Resource;
import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Resource
    private CustomerRepository customerRepository;

    /**
     * 新增用户
     *
     * @param customer 用户对象
     */
    @Override
    public Customer insertUser(Customer customer) {
        return customerRepository.save(customer);
    }

    /**
     * 删除用户
     *
     * @param id 删除id
     */
    @Override
    public void deleteUser(Long id) {
        customerRepository.deleteById(id);
    }

    /**
     * 修改用户 这里也使用save方法 判断是否是修改是通过是否有id
     *
     * @param customer 用户信息
     */
    @Override
    public Customer updateUser(Customer customer) {
        return customerRepository.save(customer);
    }

    /**
     * 查询所有用户
     */
    @Override
    public List<Customer> findAllUser() {
        return customerRepository.findAll();
    }

    /**
     * 通过id查询用户
     *
     * @param id 用户id
     */
    @Override
    public Customer findUserById(Long id) {
        return customerRepository.findById(id).orElse(null);
    }
}
