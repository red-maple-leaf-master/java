package top.oneyi.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.oneyi.mapper.OrderMapper;
import top.oneyi.pojo.po.Order;
import top.oneyi.service.OrderService;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderMapper orderMapper;

    @Override
    public void save(Order order) {
        orderMapper.save(order);
    }
}
