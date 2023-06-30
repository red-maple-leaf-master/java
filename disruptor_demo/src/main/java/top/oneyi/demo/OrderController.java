package top.oneyi.demo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.oneyi.config.GatewayConn;

import javax.annotation.Resource;

@RestController
@RequestMapping("/order")
public class OrderController {
    @Resource
    private GatewayConn gatewayConn;

    @GetMapping("/test")
    public String test() {
    	NoticeWebsocket.sendMessage("你好，WebSocket");
        return "发送成功";
    }

	@GetMapping("/test01")
    public String test01(String name) {
        gatewayConn.sendMsg(name);
        return "发送成功";
    }
}
