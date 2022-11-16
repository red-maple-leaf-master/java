package top.oneyi.controller;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class MdCusController {

    @RequestMapping("/test")
    public String find(){
        return "hello 你好";
    }
}
