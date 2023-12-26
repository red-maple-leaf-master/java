package ${package}.${module}.controller;

import top.oneyi.generator.common.R;
import ${package}.${module}.domain.${Domain};
import ${package}.${module}.service.${Domain}Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import java.util.List;

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
    *  获取列表
    * @param SysUser
    * @return
    */
    @PostMapping("/list")
    public R&lt;List&lt;${Domain}&gt;&gt; getAll(@RequestBody ${Domain} ${domain}) {
        List<${Domain}> list = ${domain}Service.getAll(${Domain});
        return R.data(list);
    }

    /**
    *  新增
    * @param sysUser
    * @return
    */
    @PostMapping("/save")
    public R&lt;${Domain}&gt; save(@RequestBody ${Domain} ${domain}) {
        return R.status(${domain}Service.save(${domain}));
    }

    /**
    * 更新
    * @param sysUser
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
