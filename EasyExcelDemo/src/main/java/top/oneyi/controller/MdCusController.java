package top.oneyi.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.oneyi.pojo.MdCus;
import top.oneyi.service.MdCusService;

import java.util.List;


@RestController
public class MdCusController {

    @Autowired
    private MdCusService mdCusService;

    @RequestMapping("/test")
    public List<MdCus> find(){
        return mdCusService.list();
    }

    @RequestMapping("/one")
    public String one(){
        return "hhhhhhhh  你终于成功了  === 成功更新代码=====一键部署代码成功了====的房间里开始";
    }
}
