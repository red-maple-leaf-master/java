package top.oneyi.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.oneyi.mapper.ProductMapper;
import top.oneyi.pojo.po.Product;
import top.oneyi.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductMapper productMapper;

    @Override
    public Product findByPid(Integer pid) {
        return productMapper.findById(pid).get();
    }
}
