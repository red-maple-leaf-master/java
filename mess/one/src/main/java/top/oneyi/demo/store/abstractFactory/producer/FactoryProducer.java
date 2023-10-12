package top.oneyi.demo.store.abstractFactory.producer;

import top.oneyi.demo.store.abstractFactory.factory.AbstractFactory;
import top.oneyi.demo.store.abstractFactory.factory.ColorFactory;
import top.oneyi.demo.store.abstractFactory.factory.ShaperFactory;

/**
 * Created with IntelliJ IDEA.
 *  产品选择器
 * @Author: wanyi
 * @Date: 2023/10/12/15:13
 */
public class FactoryProducer {
    /**
     * 根据不同类型选择创建的工厂
     * @param choice
     * @return
     */
    public static AbstractFactory getFactory(String choice){
        if(choice.equalsIgnoreCase("SHAPE")){
            return new ShaperFactory();
        } else if(choice.equalsIgnoreCase("COLOR")){
            return new ColorFactory();
        }
        return null;
    }
}
