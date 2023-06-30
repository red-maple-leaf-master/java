package top.oneyi;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import top.oneyi.config.ConnectionConfig;
import top.oneyi.demo.TcpServer;
import top.oneyi.thirdpart.uuid.GudyUuid;

import javax.annotation.PostConstruct;

@SpringBootApplication
public class disruptorApplication {


    /**
     * 初始化 uuid生成类  填入的数字位 机房  id 和 机柜id
     */
    @PostConstruct
    private void init(){
        GudyUuid.getInstance().init(0,0);
    }


    public static void main(String[] args) throws InterruptedException {
        SpringApplication.run(disruptorApplication.class, args);
        new TcpServer().startServ();
    }
}
