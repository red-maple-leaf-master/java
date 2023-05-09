package top.oneyi.controller;

import org.springframework.web.bind.annotation.*;
import top.oneyi.pojo.dto.UserDto;
import top.oneyi.util.RsaUtil;

import javax.servlet.http.HttpSession;

@RestController
public class AxiosController {

    private final static String publicKey =  RsaUtil.getPublicKey();

    @PostMapping("/base")
    public String base(){
//        String publicKey = RsaUtil.getPublicKey();
        System.out.println("publicKey = " + publicKey);
        // 公钥
        return publicKey;
    }
    @PostMapping("/baseurl")
    public String base02(String msg) throws Exception {
        // 前端发过来的加密数据
        System.out.println(msg);
        byte[] bytes = RsaUtil.decryptByPrivateKey(msg.getBytes(), publicKey);
        System.out.println(new String(bytes));
        return "接收公钥成功";
    }
    @GetMapping("/api/a1")
    public String a1() {
        return "get request";
    }

    @PostMapping("/api/a2")
    public String a2(@RequestBody UserDto dto) {
        System.out.println(dto);
        for (String list : dto.getLists()) {
            System.out.println("list = " + list);
        }
        return "post request";
    }

    @PostMapping("/api/a3")
    public String a3(@RequestHeader("Authorization") String authorization) {
        System.out.println("authorization 头 " + authorization);
        return "post request";
    }

    @PostMapping("/api/a4")
    public String a4(String name, Integer age) {
        System.out.println("name: " + name + " age:" + age);
        return "post request";
    }

    @PostMapping("/api/a6set")
    public String a6set(HttpSession session) {
        System.out.println("========== a6 set ==========");
        System.out.println(session.getId());
        session.setAttribute("name", "张三");
        return "post request";
    }

    @PostMapping("/api/a6get")
    public String a6get(HttpSession session) {
        System.out.println("========== a6 get ==========");
        System.out.println(session.getId());
        System.out.println("name: " + session.getAttribute("name"));
        return "post request";
    }


}
