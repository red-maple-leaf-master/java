package top.oneyi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import top.oneyi.pojo.Customer;

public interface CustomerRepository extends JpaRepository<Customer,Long> {
}
