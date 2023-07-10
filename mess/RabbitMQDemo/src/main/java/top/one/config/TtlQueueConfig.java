package top.one.config;

import com.alibaba.druid.sql.visitor.functions.Bin;
import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class TtlQueueConfig {
    // 正常交换机
    public static final String X_EXCHANGE = "X";
    //正常队列
    public static final String QUEUE_A = "QA";
    //正常队列
    public static final String QUEUE_B = "QB";
    // 新加正常 队列
    public static final String QUEUE_C = "QC";
    // 死信交换机
    public static final String Y_DEAD_LETTER_EXCHANGE = "Y";
    // 死信队列
    public static final String DEAD_LETTER_QUEUE = "QD";


    // 声明xExchange
    @Bean("xExchange")
    public DirectExchange xExchange() {
        return new DirectExchange(X_EXCHANGE);
    }

    // 声明yExchange
    @Bean("yExchange")
    public DirectExchange yExchange() {
        return new DirectExchange(Y_DEAD_LETTER_EXCHANGE);
    }

    // 声明队列A ttl 为10s 并绑定到对应的死信交换机
    @Bean("queueA")
    public Queue queueA() {
        Map<String, Object> args = new HashMap<>(3);
        // 声明当前队列绑定的死信交换机
        args.put("x-dead-letter-exchange", Y_DEAD_LETTER_EXCHANGE);
        // 声明当前队列的死信路由key
        args.put("x-dead-letter-routing-key", "YD");
        // 声明队列的TTL
        args.put("x-message-ttl", 10000);
        return QueueBuilder.durable(QUEUE_A).withArguments(args).build();
    }

    // 声明队列A 绑定 X  交换机
    @Bean
    public Binding queueaBindX(@Qualifier("queueA") Queue queue,
                               @Qualifier("xExchange") DirectExchange xExchange) {
        return BindingBuilder.bind(queue).to(xExchange).with("XA");
    }

    // 声明队列A ttl 为40s 并绑定到对应的死信交换机
    @Bean("queueB")
    public Queue queueB() {
        Map<String, Object> args = new HashMap<>(3);
        // 声明当前队列绑定的死信交换机
        args.put("x-dead-letter-exchange", Y_DEAD_LETTER_EXCHANGE);
        // 声明当前队列的死信路由key
        args.put("x-dead-letter-routing-key", "YD");
        // 声明队列的TTL
        args.put("x-message-ttl", 40000);
        return QueueBuilder.durable(QUEUE_B).withArguments(args).build();
    }

    // 声明队列B绑定 X 交换机
    @Bean
    public Binding queuebBindX(@Qualifier("queueB") Queue queue,
                               @Qualifier("xExchange") DirectExchange xExchange) {
        return BindingBuilder.bind(queue).to(xExchange).with("XB");
    }

    //声明队列 C 死信交换机
    @Bean("queueC")
    public Queue queueC() {
        Map<String, Object> args = new HashMap<>(3);
        //声明当前队列绑定的死信交换机
        args.put("x-dead-letter-exchange", Y_DEAD_LETTER_EXCHANGE);
        //声明当前队列的死信路由 key
        args.put("x-dead-letter-routing-key", "YD");
        //没有声明 TTL 属性  因为声明ttl 属性硬编码太严重就让 用户传递过来
        return QueueBuilder.durable(QUEUE_C).withArguments(args).build();
    }

    //声明队列 C 绑定 X 交换机
    @Bean
    public Binding queuecBindingX(@Qualifier("queueC") Queue queueC,
                                  @Qualifier("xExchange") DirectExchange xExchange) {
        return BindingBuilder.bind(queueC).to(xExchange).with("XC");
    }


    // 声明死信队列QD
    @Bean("queueD")
    public Queue queue() {
        return QueueBuilder.durable(DEAD_LETTER_QUEUE).build();
    }

    //声明死信队列 QD 绑定关系
    @Bean
    public Binding deadLetterBindingQAD(@Qualifier("queueD") Queue queueD,
                                        @Qualifier("yExchange") DirectExchange yExchange) {
        return BindingBuilder.bind(queueD).to(yExchange).with("YD");
    }
}
