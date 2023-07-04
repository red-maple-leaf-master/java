package top.oneyi.vertx;

import io.vertx.core.Vertx;
import io.vertx.core.http.HttpServer;
import io.vertx.core.http.HttpServerOptions;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.core.net.JksOptions;

/**
 * 简略写法  Vertx官方建议 每个应用创建一个Verticle的模块
 */
public class MyHttpServer {
    public static void main(String[] args) {
        Vertx vertx = Vertx.vertx();

        // 创建一个HttpServer
        HttpServerOptions options = new HttpServerOptions()
                .setMaxWebsocketFrameSize(1000000);

        HttpServer httpServer = vertx.createHttpServer(options);

        httpServer.requestHandler(request  -> {
            // 获取响应对象
            HttpServerResponse response = request.response();

            // 设置响应头
            response.putHeader("Content-type","text/html;charset=utf-8");

            // 响应数据
            response.end("Hello World");
        });
        // 监听8080端口
        httpServer.listen(9090);
    }
}
