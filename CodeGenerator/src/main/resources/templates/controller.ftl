package ${package}.${module}.controller;

import com.github.pagehelper.PageInfo;
import top.oneyi.generator.common.R;
import ${package}.${module}.domain.${Domain};
import ${package}.${module}.service.${Domain}Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import top.oneyi.common.R;

import javax.annotation.Resource;

/**
*
* ${Domain}Controller
* @Author: ${author}
* @Date: ${date}
*/
@RestController
@RequestMapping("/${domain}")
public class ${Domain}Controller {

    private static final Logger LOG = LoggerFactory.getLogger(${Domain}Controller.class);
    public static final String BUSINESS_NAME = "${tableNameCn}";

    @Resource
    private ${Domain}Service ${domain}Service;



    /**
    *  获取分页列表
    * @param ${domain}
    * @param page
    * @param pageSize
    * @return
    */
    @GetMapping("/page")
    public R&lt;PageInfo&lt;${Domain}&gt;&gt; page(${Domain} ${domain}, Integer page, Integer pageSize) {
        PageInfo&lt;${Domain}&gt;list = ${domain}Service.page(${domain},page,pageSize);
        return R.data(list);
    }

    /**
    *  获取列表
    * @param ${domain}
    * @return
    */
    @GetMapping("/list")
    public R&lt;List&lt;${Domain}&gt;&gt; getAll(@RequestBody ${Domain} ${domain}) {
        List&lt;${Domain}&gt; list = ${domain}Service.getAll(${domain});
        return R.data(list);
    }

    /**
    *  新增
    * @param ${domain}
    * @return
    */
    @PostMapping("/save")
    public R&lt;${Domain}&gt; save(@RequestBody ${Domain} ${domain}) {
        return R.status(${domain}Service.save(${domain}));
    }

    /**
    * 更新
    * @param ${domain}
    * @return
    */
    @PostMapping("/update")
    public R&lt;String&gt; update(@RequestBody ${Domain} ${domain}) {
        return R.status(${domain}Service.updateById(${domain}));
    }

    /**
    *  根据id删除
    * @param id
    * @return
    */
    @DeleteMapping("/delete/{id}")
    public R&lt;String&gt; delete(@PathVariable String id) {
        return R.status(${domain}Service.deleteById(id));
    }

    /**
    * 根据id获取信息
    * @param id
    * @return
    */
    @GetMapping("/info")
    public R&lt;${Domain}&gt; info(@PathVariable String id) {
        return R.data(${domain}Service.getById(id));
    }
}
