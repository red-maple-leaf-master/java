package top.oneyi.controller;

import org.springframework.web.bind.annotation.*;
import top.oneyi.pojo.Customer;
import top.oneyi.repository.CustomerRepository;
import top.oneyi.service.CustomerService;

import javax.annotation.Resource;
import java.util.List;


@RestController
@RequestMapping("/customer")
public class CustomerController {

    @Resource
    private CustomerService customerService;

    /**
     * 新增用户
     */
    @PostMapping("")
    public Customer addUser(@RequestBody Customer customer){
        return customerService.insertUser(customer);
    }

    /**
     * 删除用户
     */
    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable("id") Long id){
        customerService.deleteUser(id);
    }

    /**
     * 修改用户
     */
    @PutMapping("")
    public Customer updateUser(@RequestBody Customer customer){
        return customerService.updateUser(customer);
    }

    /**
     * 全查用户
     */
    @GetMapping("")
    public List<Customer> findAll(){
        return customerService.findAllUser();
    }

    /**
     * id查用户
     */
    @GetMapping("/{id}")
    public Customer findbyId(@PathVariable("id") Long id){
        return customerService.findUserById(id);
    }

    @Resource
    private CustomerRepository customerRepository;

    /**
     * 名字查用户
     */
    @GetMapping("/name")
    public List<Customer> findByName(String name){
        return customerRepository.findByName(name);
    }
}
