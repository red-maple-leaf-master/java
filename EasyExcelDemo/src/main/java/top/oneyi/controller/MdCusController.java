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
}