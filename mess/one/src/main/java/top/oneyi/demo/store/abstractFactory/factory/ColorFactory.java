package top.oneyi.demo.store.abstractFactory.factory;

import top.oneyi.demo.store.abstractFactory.producerType.Color;
import top.oneyi.demo.store.abstractFactory.producerType.Impl.Blue;
import top.oneyi.demo.store.abstractFactory.producerType.Impl.Green;
import top.oneyi.demo.store.abstractFactory.producerType.Impl.Red;
import top.oneyi.demo.store.abstractFactory.producerType.Shaper;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: wanyi
 * @Date: 2023/10/12/15:09
 */
public class ColorFactory extends AbstractFactory {

    @Override
    public Shaper getShape(String shape) {
        return null;
    }

    @Override
    public Color getColor(String color) {
        if(color == null){
            return null;
        }
        if(color.equalsIgnoreCase("RED")){
            return new Red();
        } else if(color.equalsIgnoreCase("GREEN")){
            return new Green();
        } else if(color.equalsIgnoreCase("BLUE")){
            return new Blue();
        }
        return null;
    }
}
