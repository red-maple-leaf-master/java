package top.oneyi.generator;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: wanyi
 * @Date: 2023/09/19/17:51
 */
@SpringBootApplication
// 启用swagger
@EnableSwagger2
public class CodeGeneratorSpringbootApplication {
    public static void main(String[] args) {
        SpringApplication.run(CodeGeneratorSpringbootApplication.class, args);
    }
}
