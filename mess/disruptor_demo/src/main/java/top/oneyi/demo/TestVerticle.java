package top.oneyi.demo;


import io.vertx.ext.web.RoutingContext;
import top.oneyi.unitls.GatewayConn;

import javax.annotation.Resource;


//@Component
public class TestVerticle extends BaseVerticle {

    @Resource
    private GatewayConn gatewayConn;

    @Override
    public void start() throws Exception {
        router.get("/getTest").handler(this::getTest);
        router.get("/err").handler(this::err);
    }

    private void getTest(RoutingContext ctx) {
        gatewayConn.sendMsg("我是李四");
        gatewayConn.sendMsg(ctx.get("name"));
    }

    private void err(RoutingContext ctx) {
        int a = 1/0;
    }
}
