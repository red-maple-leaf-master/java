package top.oneyi.demo.store.api;

import java.util.Map;

public interface GameApi {

    void sendGift(String id, String gameName, Map<String,Object> maps);
}
