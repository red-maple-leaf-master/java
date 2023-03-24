package top.oneyi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import top.oneyi.pojo.Customer;

import java.util.List;

public interface CustomerRepository extends JpaRepository<Customer,Long>, JpaSpecificationExecutor<Customer> {

    @Query(value = "from customer where name = ?1")
    List<Customer> findByName(String name);
}
