package top.oneyi.controller;

import org.springframework.web.bind.annotation.*;
import top.oneyi.pojo.User;

@RestController
@RequestMapping
public class UserController {


    @PostMapping(value = "api/login")
    @ResponseBody
    public Integer login(@RequestBody User user) {
        if ("admin".equals(user.getName()) || "123456".equals(user.getPassword())) {
            return 200;
        } else {
            String message = "账号密码错误";
            System.out.println("test");
            return 400;
        }
    }
}
