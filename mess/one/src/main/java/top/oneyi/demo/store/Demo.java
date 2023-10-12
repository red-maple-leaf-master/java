package top.oneyi.demo.store;

import top.oneyi.demo.store.api.GameApi;
import top.oneyi.demo.store.conext.Context;
import top.oneyi.demo.store.enumType.ShapeTypeEnum;

import java.util.HashMap;

public class Demo {
    public static void main(String[] args) {
        GameFactory factory = new GameFactory();
//        GameApi gameApi = factory.getGameApi(3);
        // 使用枚举优化if else 语句
//        GameApi gameApi = factory.getGameApi("DNF");
//        gameApi.sendGift(" 10023231 ", " 红枫叶主 ", new HashMap<>());

        // 策略模式封装
        Context context = new Context(factory.getGameApi("DNF"));
        context.sendCommonGift(" 10023231 ", " 红枫叶主 ", new HashMap<>());
    }
}
