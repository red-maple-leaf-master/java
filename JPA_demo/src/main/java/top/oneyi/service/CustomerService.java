package top.oneyi.service;

import top.oneyi.pojo.Customer;

import java.util.List;

public interface CustomerService {

    /**
     * 新增用户
     *
     * @param customer 用户对象
     */
    Customer insertUser(Customer customer);

    /**
     * 删除用户
     *
     * @param id 删除id
     */
    void deleteUser(Long id);

    /**
     * 修改用户
     *
     * @param customer 用户信息
     */
    Customer updateUser(Customer customer);

    /**
     * 查询所有用户
     */
    List<Customer> findAllUser();

    /**
     * 通过id查询用户
     *
     * @param id 用户id
     */
    Customer findUserById(Long id);


}
