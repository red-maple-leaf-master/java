package top.oneyi.demo;

import top.oneyi.pojo.Order;

import java.util.ArrayList;
import java.util.List;

public class Demo {
    public static void main(String[] args) {
        Order order = new Order();
        order.setPrice(10);

        Order order1 = new Order();
        order1.setPrice(14);

        Order order2 = new Order();
        order2.setPrice(2);
        List<Order> list = new ArrayList<>();
        list.add(order);
        list.add(order1);
        list.add(order2);
        list.sort((o1,o2)->{
            if(o1.getPrice() > o2.getPrice()){
                return -1;
            }else if(o1.getPrice() < o2.getPrice()){
                return 1;
            }else{
                return 0;
            }
        });
        System.out.println("list = " + list);
    }
}
