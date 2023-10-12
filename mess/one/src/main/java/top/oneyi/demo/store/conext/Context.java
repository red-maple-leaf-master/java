package top.oneyi.demo.store.conext;

import top.oneyi.demo.store.api.GameApi;

import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * 策略模式
 *
 * @Author: wanyi
 * @Date: 2023/10/12/14:43
 */
public class Context {
    /**
     * 工厂接口 可以有多个
     */
    private GameApi gameApi;

    public Context(GameApi gameApi){
        this.gameApi=gameApi;
    }

    public void sendCommonGift(String id, String gameName, Map<String, Object> maps){
        gameApi.sendGift(id,gameName,maps);
    }
}
