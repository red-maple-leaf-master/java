package top.oneyi;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tk.mybatis.spring.annotation.MapperScan;

@MapperScan("top.oneyi.mapper")
@SpringBootApplication
public class EsayExcelApplication {
    public static void main(String[] args) {
        SpringApplication.run(EsayExcelApplication.class,args);
    }
}
