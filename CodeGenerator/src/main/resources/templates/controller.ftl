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


    @PostMapping("/list")
    public R<List<${Domain}>> getAll(@RequestBody ${Domain} ${Domain}) {
        List<${Domain}> list = ${domain}Service.getAll(${Domain});
        return R.data(list);
    }


    @PostMapping("/save")
    public R<${Domain}> save(@RequestBody ${Domain} ${domain}) {
        return R.status(${domain}Service.save(${domain}));
    }

    @PostMapping("/update")
    public R<${Domain}> update(@RequestBody ${Domain} ${domain}) {
        return R.status(${domain}Service.updateById(${domain}));
    }

    @DeleteMapping("/delete/{id}")
    public R delete(@PathVariable String id) {
        return R.status(${domain}Service.deleteById(id));
    }

    @GetMapping("/info")
    public R<${Domain}> info(@PathVariable String id) {
        return R.data(${domain}Service.getById(id));
    }
}
