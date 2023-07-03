package top.oneyi.factory;

import com.lmax.disruptor.EventFactory;
import top.oneyi.pojo.Order;

public class OrderFactory implements EventFactory {

    @Override
    public Object newInstance() {

        System.out.println("OrderFactory.newInstance");
        return new Order();
    }

}