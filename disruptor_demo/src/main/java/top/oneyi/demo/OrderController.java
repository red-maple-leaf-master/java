package top.oneyi.demo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/order")
public class OrderController {
	@GetMapping("/test")
    public String test() {
    	NoticeWebsocket.sendMessage("你好，WebSocket");
        return "发送成功";
    }
}
