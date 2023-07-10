package top.oneyi.demo.store;

import top.oneyi.demo.store.api.GameApi;

import java.util.HashMap;

public class Demo {
    public static void main(String[] args) {
        GameFactory factory = new GameFactory();
        GameApi gameApi = factory.getGameApi(3);
        gameApi.sendGift("10023231", "红枫叶主", new HashMap<>());
    }
}
