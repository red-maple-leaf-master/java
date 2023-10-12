package top.oneyi.demo.store.abstractFactory.producerType.Impl;

import top.oneyi.demo.store.abstractFactory.producerType.Color;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: wanyi
 * @Date: 2023/10/12/15:17
 */
public class Blue implements Color {
    @Override
    public void fill() {
        System.out.println("涂上了蓝色");
    }
}
