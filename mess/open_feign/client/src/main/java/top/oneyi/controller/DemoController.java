package top.oneyi.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import top.oneyi.serverApi.TestApi;


import javax.annotation.Resource;

/**
 * 服务消费方
 *
 * @author oneyi
 * @date 2023/4/4
 */
@RestController
@RequestMapping("/demo")
public class DemoController {

    @Resource
    private TestApi api;

    @GetMapping("/list")
    public String list01(@RequestParam("str") String str, Integer i) {
        String list = api.list(str);
        String list01 = api.list01(Integer.valueOf(i));
        System.out.println("client:我调用了服务端的接口");
        return "收到数据===>(" + list + list01 + ")";
    }

}
