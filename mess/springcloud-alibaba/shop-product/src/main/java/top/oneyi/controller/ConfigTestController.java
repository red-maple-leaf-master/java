package top.oneyi.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RefreshScope  // 需要动态获取配置的类上加上这个注解
public class ConfigTestController {

    
    @Value("${activitySwitch.val:false}")
    private Boolean activitySwitch;

    @GetMapping("/configTest")
    public String configTest() {
        if (activitySwitch) {
            return "活动页面渲染所需数据";
        } else {
            return "活动未开启";
        }
    }
}
