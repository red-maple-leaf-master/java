package top.oneyi.vertx.demo;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Vertx;
import io.vertx.core.eventbus.DeliveryOptions;
import io.vertx.core.http.HttpServer;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.core.json.JsonObject;
import top.oneyi.vertx.ServerVertice;

public class FirstVerticle extends AbstractVerticle {
    public static void main(String[] args) {
        FirstVerticle verticle = new FirstVerticle();

        Vertx vertx = Vertx.vertx();

        // 部署verticle，这里会调用start方法
        vertx.deployVerticle(verticle);
    }

    @Override
    public void start() throws Exception {
        HttpServer httpServer = vertx.createHttpServer();

        httpServer.requestHandler(request -> {

            // 获取到response对象
            HttpServerResponse response = request.response();

            // 设置响应头
            response.putHeader("Content-type", "text/html;charset=utf-8");

            // 通过配置action参数，指定要走哪一个方法
            DeliveryOptions options = new DeliveryOptions();
            options.addHeader("action", "sayHello");

            // 这个是给方法传入的参数
            JsonObject config = new JsonObject();
            config.put("name", "xiaozhang");

            // 通过eventBus调用方法
            vertx.eventBus().<JsonObject>send("service.demo.firstverticle", config, options, res -> {
                // 响应数据
                response.end(res.result().body().getString("msg"));
            });

        });

        httpServer.listen(1234);
    }

    @Override
    public void stop() throws Exception {
        super.stop();
    }
}
