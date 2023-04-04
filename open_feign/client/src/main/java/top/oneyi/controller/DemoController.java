package top.oneyi.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import top.oneyi.Api;


import javax.annotation.Resource;

/**
 * 服务消费方
 * @author oneyi
 * @date 2023/4/4
 */
@RestController
@RequestMapping("/demo")
public class DemoController {
    @Resource
    private Api api;

    @GetMapping("/list")
    public String list01(@RequestParam("str") String str){
        String list = api.list(str);

        return "收到数据===>("+list+")";
    }

}
