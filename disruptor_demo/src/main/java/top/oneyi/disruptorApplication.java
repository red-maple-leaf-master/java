package top.oneyi;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import top.oneyi.config.ConnectionConfig;
import top.oneyi.demo.TcpServer;

@SpringBootApplication
public class disruptorApplication {
    public static void main(String[] args) throws InterruptedException {
        SpringApplication.run(disruptorApplication.class, args);
        new TcpServer().startServ();
    }
}
