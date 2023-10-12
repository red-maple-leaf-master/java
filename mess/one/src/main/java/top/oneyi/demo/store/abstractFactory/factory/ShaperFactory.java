package top.oneyi.demo.store.abstractFactory.factory;

import top.oneyi.demo.store.abstractFactory.producerType.Color;
import top.oneyi.demo.store.abstractFactory.producerType.Impl.Circle;
import top.oneyi.demo.store.abstractFactory.producerType.Impl.Rectangle;
import top.oneyi.demo.store.abstractFactory.producerType.Impl.Square;
import top.oneyi.demo.store.abstractFactory.producerType.Shaper;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: wanyi
 * @Date: 2023/10/12/15:08
 */
public class ShaperFactory extends AbstractFactory {


    @Override
    public Shaper getShape(String shape) {
        if(shape == null){
            return null;
        }
        if(shape.equalsIgnoreCase("CIRCLE")){
            return new Circle();
        } else if(shape.equalsIgnoreCase("RECTANGLE")){
            return new Rectangle();
        } else if(shape.equalsIgnoreCase("SQUARE")){
            return new Square();
        }
        return null;
    }

    @Override
    public Color getColor(String color) {
        return null;
    }
}
