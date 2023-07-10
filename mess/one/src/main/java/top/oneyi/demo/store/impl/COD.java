package top.oneyi.demo.store.impl;

import top.oneyi.demo.store.api.GameApi;

import java.util.Map;

public class COD implements GameApi {
    @Override
    public void sendGift(String id, String gameName, Map<String, Object> maps) {
        System.out.println("id为" + id + "的玩家" + gameName + "获得了" + "淬火匕首");
    }
}
