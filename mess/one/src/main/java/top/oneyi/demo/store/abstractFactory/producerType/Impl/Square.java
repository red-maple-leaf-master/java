package top.oneyi.demo.store.abstractFactory.producerType.Impl;

import top.oneyi.demo.store.abstractFactory.producerType.Shaper;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: wanyi
 * @Date: 2023/10/12/15:16
 */
public class Square implements Shaper {
    @Override
    public void draw() {
        System.out.println("我画了一个广场");
    }
}
