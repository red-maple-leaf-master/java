package top.one.config;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.annotation.RabbitListenerConfigurer;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.RabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.listener.RabbitListenerEndpointRegistrar;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.messaging.converter.MappingJackson2MessageConverter;
import org.springframework.messaging.handler.annotation.support.DefaultMessageHandlerMethodFactory;
import org.springframework.messaging.handler.annotation.support.MessageHandlerMethodFactory;

/**
 * rabbitMQ队列和交换机绑定的配置类
 * @author oneyi
 * @date 2023/2/14
 */

@Configuration
public class RabbitMQConfig  {

    //交换机名称
    public static final String ITEM_TOPIC_EXCHANGE = "item_topic_exchange";
    //队列名称
    public static final String ITEM_QUEUEA = "item_queueA";
    public static final String ITEM_QUEUEB = "item_queueB";

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
    @Bean("itemQueueA")
    public Queue itemQueueA(){
        // 默认队列持久化 durable 为true是持久化
        return QueueBuilder.durable(ITEM_QUEUEA).build();
    }

    /**
     * 声明队列
     * @return
     */
    @Bean("itemQueueB")
    public Queue itemQueueB(){
        // 默认队列持久化 durable 为true是持久化
        return QueueBuilder.durable(ITEM_QUEUEB).build();
    }

    /**
     * 绑定队列A和交换机
     * @param queue
     * @param exchange
     * @return
     */
    @Bean
    public Binding itemQueueExchangeA(@Qualifier("itemQueueA") Queue queue,
                                     @Qualifier("itemTopicExchange") Exchange exchange){
        return BindingBuilder.bind(queue).to(exchange).with("item.#").noargs();
    }
    /**
     * 绑定队列B和交换机
     * @param queue
     * @param exchange
     * @return
     */
    @Bean
    public Binding itemQueueExchangeB(@Qualifier("itemQueueB") Queue queue,
                                      @Qualifier("itemTopicExchange") Exchange exchange){
        return BindingBuilder.bind(queue).to(exchange).with("item.#").noargs();
    }
}
