package top.oneyi.demo;

import com.alibaba.fastjson.JSONObject;
import io.vertx.ext.web.RoutingContext;
import org.springframework.stereotype.Component;
import top.oneyi.pojo.ResultDTO;

@Component
public class TestVerticle extends BaseVerticle {

    @Override
    public void start() throws Exception {
        router.get("/getTest").handler(this::getTest);
        router.get("/err").handler(this::err);
    }

    private void getTest(RoutingContext ctx) {
        ctx.response().end(JSONObject.toJSONString(ResultDTO.ok()));
    }

    private void err(RoutingContext ctx) {
        int a = 1/0;
    }
}
