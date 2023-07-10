package top.oneyi.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 服务提供方
 *
 * @author oneyi
 * @date 2023/4/4
 */
@RestController
@RequestMapping("/test01")
public class Test01Controller {

    @GetMapping("/one")
    public String list(@RequestParam("i") Integer i) {

        System.out.println(" test01 我被调用了");
//        Thread.sleep(3000L);
        return "serve01 服务端 的数据:......+  client传递的参数:" + i;

    }
}
