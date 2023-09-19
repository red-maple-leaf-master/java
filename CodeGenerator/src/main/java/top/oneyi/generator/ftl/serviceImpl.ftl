package top.oneyi.${module}.service.impl;

import org.springframework.stereotype.Service;
import top.oneyi.${module}.domain.${Domain};
import top.oneyi.${module}.mapper.${Domain}Mapper;
import top.oneyi.${module}.service.${Domain}Service;

import javax.annotation.Resource;
import java.util.List;

/**
* Created with IntelliJ IDEA.
*
* @Author: wanyi
* @Date: 2023/09/19/16:53
*/
@Service
public class ${Domain}ServiceImpl  implements ${Domain}Service {

    @Resource
    private ${Domain}Mapper ${domain}Mapper;

    @Override
    public List<${Domain}> getAll(${Domain} ${domain}) {
        return ${domain}Mapper.select(bladeUser);
    }

    @Override
    public Boolean save(${Domain} ${domain}) {
        return ${domain}Mapper.insert(${domain}) > 0;
    }

    @Override
    public Boolean updateById(${Domain} ${domain}) {
        return ${domain}Mapper.updateByPrimaryKey(${domain}) > 0;
    }

    @Override
    public Boolean deleteById(String id) {
        return ${domain}Mapper.deleteByPrimaryKey(id) > 0;
    }

    @Override
    public ${Domain} getById(String id) {
        return ${domain}Mapper.selectByPrimaryKey(id);
    }
}
