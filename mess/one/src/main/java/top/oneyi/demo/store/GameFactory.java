package top.oneyi.demo.store;

import top.oneyi.demo.store.api.GameApi;
import top.oneyi.demo.store.enumType.ShapeTypeEnum;
import top.oneyi.demo.store.impl.CF;
import top.oneyi.demo.store.impl.COD;
import top.oneyi.demo.store.impl.DNF;

public class GameFactory {

    public GameApi getGameApi(Integer type) {
        if (null == type) return null;
        if (1 == type) return new CF();
        if (2 == type) return new COD();
        if (3 == type) return new DNF();
        throw new RuntimeException("不存在的商品服务类型");
    }

    /**
     * 使用枚举和反射优化 if else
     * @param name
     * @return
     */
    public GameApi getGameApi(String name) {
        GameApi gameApi = null;
        try {
            ShapeTypeEnum shapeTypeEnum = ShapeTypeEnum.valueOf(name);
            String className = shapeTypeEnum.getClassName();

            Class clazz = Class.forName(className);
            gameApi = (GameApi) clazz.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return gameApi;
    }
}
