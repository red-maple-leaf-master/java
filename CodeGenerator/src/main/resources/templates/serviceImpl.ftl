package ${package}.${module}.service.impl;

import org.springframework.stereotype.Service;
import ${package}.${module}.domain.${Domain};
import ${package}.${module}.mapper.${Domain}Mapper;
import ${package}.${module}.service.${Domain}Service;

import javax.annotation.Resource;
import java.util.List;

/**
*
* ${Domain}ServiceImpl
* @Author: ${author}
* @Date: ${date}
*/
@Service
public class ${Domain}ServiceImpl  implements ${Domain}Service {

    @Resource
    private ${Domain}Mapper ${domain}Mapper;

    /**
    *  获取列表
    * @param SysUser
    * @return
    */
    @Override
    public List&lt;${Domain}&gt; getAll(${Domain} ${domain}) {
        return ${domain}Mapper.select(${domain});
    }

    /**
    *  新增
    * @param sysUser
    * @return
    */
    @Override
    public Boolean save(${Domain} ${domain}) {
        return ${domain}Mapper.insert(${domain}) > 0;
    }

    /**
    * 更新
    * @param sysUser
    * @return
    */
    @Override
    public Boolean updateById(${Domain} ${domain}) {
        return ${domain}Mapper.updateByPrimaryKey(${domain}) > 0;
    }

    /**
    * 根据id获取信息
    * @param id
    * @return
    */
    @Override
    public Boolean deleteById(String id) {
        return ${domain}Mapper.deleteByPrimaryKey(id) > 0;
    }

    /**
    * 根据id获取信息
    * @param id
    * @return
    */
    @Override
    public ${Domain} getById(String id) {
        return ${domain}Mapper.selectByPrimaryKey(id);
    }
}
