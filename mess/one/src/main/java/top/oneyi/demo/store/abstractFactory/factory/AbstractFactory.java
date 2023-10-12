package top.oneyi.demo.store.abstractFactory.factory;

import top.oneyi.demo.store.abstractFactory.producerType.Color;
import top.oneyi.demo.store.abstractFactory.producerType.Shaper;

/**
 * Created with IntelliJ IDEA.
 * 抽象工厂
 *
 * @Author: wanyi
 * @Date: 2023/10/12/15:02
 */
public abstract class AbstractFactory {

    public abstract Shaper getShape(String shape);

    public abstract Color getColor(String color);

}
