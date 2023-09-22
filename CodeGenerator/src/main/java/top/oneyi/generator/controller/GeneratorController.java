package top.oneyi.generator.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created with IntelliJ IDEA.
 * 代码生成器
 * @Author: wanyi
 * @Date: 2023/09/20/10:23
 */
@Controller
@RequestMapping("/generator")
public class GeneratorController {
    /**
     * 需要注意的是不能使用 rescontroller 因为这样返回的就是一个json字符串 导致无法使用模板
     * @param model
     * @return
     */
    @GetMapping("/hello")
    public String hello(ModelMap model){
        model.addAttribute("name","的方法");
        model.addAttribute("age","12");
        return "controller";
    }

}
