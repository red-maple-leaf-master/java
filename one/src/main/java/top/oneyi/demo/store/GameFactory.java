package top.oneyi.demo.store;

import top.oneyi.demo.store.api.GameApi;
import top.oneyi.demo.store.impl.CF;
import top.oneyi.demo.store.impl.COD;
import top.oneyi.demo.store.impl.DNF;

public class GameFactory {

    public GameApi getGameApi(Integer type){
        if(null ==type ) return null;
        if(1 == type) return new CF();
        if(2 == type) return new COD();
        if(3 == type) return new DNF();
        throw new RuntimeException("不存在的商品服务类型");
    }
}
