package top.oneyi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import top.oneyi.pojo.Customer;

import java.util.List;


public interface CustomerRepository extends JpaRepository<Customer,Long>, JpaSpecificationExecutor<Customer> {

    /**
     * nativeQuery 属性为 true 时 是原生sql 为false 则是 实体类    ?1 是占位符  代表第一个参数
     * @param name
     * @return
     */
//    @Query(value = "select * from customer where name = ?1",nativeQuery = true)
    List<Customer> findByName(String name);
}
