package top.oneyi.generator.controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import top.oneyi.generator.domain.Generator;
import top.oneyi.generator.service.GeneratorService;
import top.oneyi.generator.utils.DbUtil;
import top.oneyi.generator.utils.Field;

import javax.annotation.Resource;
import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * 代码生成器
 * @Author: wanyi
 * @Date: 2023/09/20/10:23
 */
@CrossOrigin
@Controller
@RequestMapping("/generator")
public class GeneratorController {

    private static final Logger LOG = LoggerFactory.getLogger(GeneratorController.class);

    @Resource
    private GeneratorService generatorService;


    /**
     * 需要注意的是不能使用  @rescontroller 因为这样返回的就是一个json字符串 导致无法使用模板
     * @param model
     * @return
     */
    @GetMapping("/hello")
    public String hello(ModelMap model,Generator generator) throws Exception {

        Map<String, Object> map = DbUtil.getData(generator);
        model.addAllAttributes(map);
        return "controller";
    }

    /**
     * 当返回其他数据类型给前端的时候需要加上 @ResponseBody 返回视图则不需要
     *  返回生成的代码
     *
     *  预览代码
     * @param generator        生成代码所需作者包名等数据
     * @return 生成好的代码
     */
    @GetMapping("/preview")
    @ResponseBody
    public  Map<String,Object> preview(Generator generator){
        return generatorService.preview(generator);
    }
}
