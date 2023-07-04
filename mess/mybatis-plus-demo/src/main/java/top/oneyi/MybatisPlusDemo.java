package top.oneyi;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
@MapperScan("top.oneyi.mapper")
public class MybatisPlusDemo {
    public static void main(String[] args) {
        SpringApplication.run(MybatisPlusDemo.class, args);
    }

}
