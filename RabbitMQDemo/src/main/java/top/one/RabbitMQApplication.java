package top.one;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling  // 定时任务
public class RabbitMQApplication {
    public static void main(String[] args) {
        SpringApplication.run(RabbitMQApplication.class,args);
    }
}
