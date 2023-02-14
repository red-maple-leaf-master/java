package top.one.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.one.service.RabbitService;

@RequestMapping("/test")
@RestController
public class RabbitController {

    @Autowired
    private RabbitService rabbitService;

    /**
     * 简单消息发送
     *
     * @return
     */
    @GetMapping("/sendSimple")
    public String sendSimpleMsg() {
        try {
            rabbitService.sendSimpleMsg();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "发送成功!";
    }

    /**
     * 简单消息接收
     *
     * @return
     */
    @GetMapping("/recvSimple")
    public String recvSimpleMsg() {
        String messge = null;
        try {
            rabbitService.recvSimpleMsg();
            messge = "简单消息接收关闭";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return messge;
    }


}
