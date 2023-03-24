package top.oneyi;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class SpringBootJPA {
    public static void main(String[] args) {
        SpringApplication.run(SpringBootJPA.class,args);
    }
}
