package top.oneyi.vertx.demo;

import io.vertx.core.AsyncResult;
import io.vertx.core.Future;
import io.vertx.core.Handler;
import io.vertx.core.json.JsonObject;

public class ServciceImpl implements Service{
    /**
     * 测试接口方法
     *
     * @param name
     * @param resultHandle
     */
    @Override
    public void sayHello(String name, Handler<AsyncResult<JsonObject>> resultHandle) {
        resultHandle.handle(Future.succeededFuture(new JsonObject().put("msg", "SUCCESS")));
    }
}
