package top.oneyi.mapper;

import org.springframework.data.jpa.repository.JpaRepository;
import top.oneyi.pojo.po.Order;

public interface OrderMapper extends JpaRepository<Order, Long> {
}