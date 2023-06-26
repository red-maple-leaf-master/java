package top.oneyi.demo;

import io.vertx.core.Vertx;
import io.vertx.core.eventbus.EventBus;

public class EventBusAlone {


    private static EventBus eb;

    private EventBusAlone(){

    }

    public static synchronized EventBus getInstance() {
        if (eb == null) {
            synchronized (EventBusAlone.class){
                eb = Vertx.vertx().eventBus();
            }
        }
        return eb;
    }

}
