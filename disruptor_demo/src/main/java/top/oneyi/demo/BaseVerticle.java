package top.oneyi.demo;

import io.vertx.core.AbstractVerticle;
import io.vertx.ext.web.Router;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public abstract class BaseVerticle extends AbstractVerticle {
    @Autowired
    public Router router;
}
