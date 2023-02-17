package top.oneyi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.oneyi.mapper.MdGoodsMapper;
import top.oneyi.pojo.MdGoods;


import java.util.List;

@RestController
@RequestMapping("/test")
public class MdCusController {

    @Autowired
    private MdGoodsMapper mdCusMapper;

    @GetMapping("/mdCus")
    public List<MdGoods> list(){
        return mdCusMapper.selectAll();
    }

}
