package top.one.config;

import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
/**
 * rabbitMQ队列和交换机绑定的配置类
 * @author oneyi
 * @date 2023/2/14
 */

@Configuration
public class RabbitMQConfig {

    //交换机名称
    public static final String ITEM_TOPIC_EXCHANGE = "item_topic_exchange";
    //队列名称
    public static final String ITEM_QUEUE = "item_queue";

    /**
     * 声明交换机
     * @return
     */
    @Bean("itemTopicExchange")
    public Exchange topticExchange(){
        return ExchangeBuilder.topicExchange(ITEM_TOPIC_EXCHANGE).durable(true).build();
    }

    /**
     * 声明队列
     * @return
     */
    @Bean("itemQueue")
    public Queue itemQueue(){
        return QueueBuilder.durable(ITEM_QUEUE).build();
    }

    /**
     * 绑定队列和交换机
     * @param queue
     * @param exchange
     * @return
     */
    @Bean
    public Binding itemQueueExchange(@Qualifier("itemQueue") Queue queue,
                                     @Qualifier("itemTopicExchange") Exchange exchange){
        return BindingBuilder.bind(queue).to(exchange).with("item.#").noargs();
    }
}
