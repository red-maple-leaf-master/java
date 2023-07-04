package top.oneyi.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
/**
 * 服务提供方
 * @author oneyi
 * @date 2023/4/4
 */
@RestController
@RequestMapping("/test")
public class TestController {

    @GetMapping("/one")
    public String list(@RequestParam("str")String str) throws InterruptedException {

        System.out.println("我被调用了");
//        Thread.sleep(3000L);
        return "serve 服务端 的数据:......+  client传递的参数:"+ str;

    }
}
