package top.oneyi.config;

import com.alibaba.fastjson.JSONObject;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.Vertx;
import io.vertx.core.VertxOptions;
import io.vertx.core.http.HttpMethod;
import io.vertx.core.http.HttpServer;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.handler.CorsHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import top.oneyi.pojo.ResultDTO;

import java.util.Map;

@Slf4j
@Configuration
public class VertxConfig {

    @Autowired
    Environment environment;

    @Bean
    public Vertx vertx() {
        VertxOptions options = new VertxOptions()
                .setEventLoopPoolSize(20)
                .setWorkerPoolSize(20);
        return Vertx.vertx(options);
    }

    @Bean
    public Router router(Vertx vertx) {
        Router router = Router.router(vertx);
        globalIntercept(router);
        globalError(router);
        cors(router);
        return router;
    }

    private Integer port() {
        return environment.getProperty("server.port", Integer.class, 1010);
    }

    @Bean
    public HttpServer httpServer(Vertx vertx, Router router, ApplicationContext applicationContext) {
        //获取所有子类
        Map<String, AbstractVerticle> beansOfType = applicationContext.getBeansOfType(AbstractVerticle.class);
        beansOfType.forEach((k, verticle) -> {
            vertx.deployVerticle(verticle);
        });
        return vertx.createHttpServer().requestHandler(router).listen(port(), "0.0.0.0", res -> {
            if (res.succeeded()) {
                log.info("vert.x success to listen： " + port());
            } else {
                log.info("vert.x fail:" + res.cause().getMessage());
            }
        });
    }

    //跨域处理
    private void cors(Router router) {
        router.route().handler(CorsHandler.create()
                .addOrigin("*")
                .allowedHeader(" x-www-form-urlencoded, Content-Type,x-requested-with")
                .allowedMethod(HttpMethod.GET)
                .allowedMethod(HttpMethod.POST)
                .allowedMethod(HttpMethod.PUT)
                .allowedMethod(HttpMethod.DELETE));

    }

    //全局异常返回
    private void globalError(Router router) {
        router.route().failureHandler(ctx -> {
            ctx.response().end(JSONObject.toJSONString(ResultDTO.err(ctx.failure().getMessage())));
        });
    }

    //全局拦截器
    private void globalIntercept(Router router) {
        router.route("/*").handler(ctx -> {
            ctx.response().putHeader("Content-Type", "application/json");
            ctx.next();
        });
    }
}
