package top.oneyi;

import org.apache.ibatis.annotations.Mapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("top.oneyi.mapper")
public class MybatisGenerator {
    public static void main(String[] args) {
        SpringApplication.run(MybatisGenerator.class,args);
    }
}
