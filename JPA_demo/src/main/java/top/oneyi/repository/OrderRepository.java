package top.oneyi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import top.oneyi.pojo.Order;

import java.util.List;


public interface OrderRepository extends JpaRepository<Order,Long>, JpaSpecificationExecutor<Order> {


    List<Order> findByBookName(String bookName);
}
