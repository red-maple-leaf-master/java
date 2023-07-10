package top.oneyi.mapper;

import org.springframework.data.jpa.repository.JpaRepository;
import top.oneyi.pojo.po.Product;

public interface ProductMapper extends JpaRepository<Product, Integer> {
}
