package top.oneyi.generator.controller;

import top.oneyi.generator.common.R;
import top.oneyi.generator.domain.BladeUser;
import top.oneyi.generator.service.BladeUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import java.util.List;

import javax.annotation.Resource;

/**
* Created with IntelliJ IDEA.
*
* @Author: wanyi
* @Date: 2023/09/19/13:44
*/
@RestController
@RequestMapping("/bladeUser")
public class BladeUserController {

private static final Logger LOG = LoggerFactory.getLogger(BladeUserController.class);
public static final String BUSINESS_NAME = "用户表";

    @Resource
    private BladeUserService bladeUserService;


    @PostMapping("/list")
    public R<List<BladeUser>> getAll(@RequestBody BladeUser BladeUser) {
        List<BladeUser> list = bladeUserService.getAll(BladeUser);
        return R.data(list);
    }


    @PostMapping("/save")
    public R<BladeUser> save(@RequestBody BladeUser bladeUser) {
        return R.status(bladeUserService.save(bladeUser));
    }

    @PostMapping("/update")
    public R<BladeUser> update(@RequestBody BladeUser bladeUser) {
        return R.status(bladeUserService.updateById(bladeUser));
    }

    @DeleteMapping("/delete/{id}")
    public R delete(@PathVariable String id) {
        return R.status(bladeUserService.deleteById(id));
    }

    @GetMapping("/info")
    public R<BladeUser> info(@PathVariable String id) {
        return R.data(bladeUserService.getById(id));
    }
}
