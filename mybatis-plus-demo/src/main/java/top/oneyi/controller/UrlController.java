package top.oneyi.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import top.oneyi.pojo.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * thymeleaf 测试
 * @author oneyi
 * @date 2023/4/19
 */
@Controller
public class UrlController {

    @GetMapping("/index")//页面的url地址
    public String getindex(Model model){//对应函数
        User user=new User();
        user.setName("bigsai");
        user.setAge(22);
        user.setUsername("一个幽默且热爱java的社会青年");
        List<String> userList=new ArrayList<>();
        userList.add("zhang san 66");
        userList.add("li si 66");
        userList.add("wang wu 66");
        Map<String ,String> map=new HashMap<>();
        map.put("place","博学谷");
        map.put("feeling","very well");
        //数据添加到model中
        model.addAttribute("name","伊万1234579121221111111111111");//普通字符串
        model.addAttribute("user",user);//储存javabean
        model.addAttribute("userList",userList);//储存List
        model.addAttribute("map",map);//储存Map
        return "index";//与templates中index.html对应
    }
}