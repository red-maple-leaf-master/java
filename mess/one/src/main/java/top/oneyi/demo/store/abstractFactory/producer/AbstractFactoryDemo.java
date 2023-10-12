package top.oneyi.demo.store.abstractFactory.producer;

import top.oneyi.demo.store.abstractFactory.factory.AbstractFactory;
import top.oneyi.demo.store.abstractFactory.producerType.Color;
import top.oneyi.demo.store.abstractFactory.producerType.Shaper;

/**
 * Created with IntelliJ IDEA.
 *  抽象工厂测试
 *
 * @Author: wanyi
 * @Date: 2023/10/12/15:27
 */
public class AbstractFactoryDemo {
    public static void main(String[] args) {
       AbstractFactory shaperFactory =  FactoryProducer.getFactory("SHAPE");
        Shaper circle = shaperFactory.getShape("CIRCLE");
        circle.draw();
        //获取颜色工厂
        AbstractFactory colorFactory = FactoryProducer.getFactory("COLOR");

        //获取颜色为 Red 的对象
        Color color = colorFactory.getColor("RED");
        color.fill();
    }
}
