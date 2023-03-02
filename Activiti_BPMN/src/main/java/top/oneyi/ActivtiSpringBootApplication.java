package top.oneyi;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@MapperScan("top.oneyi.mapper")
public class ActivtiSpringBootApplication {
    public static void main(String[] args) {
        SpringApplication.run(ActivtiSpringBootApplication.class,args);
    }
}
