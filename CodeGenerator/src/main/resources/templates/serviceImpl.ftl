package ${package}.${module}.service.impl;

import org.springframework.stereotype.Service;
import ${package}.${module}.domain.${Domain};
import ${package}.${module}.mapper.${Domain}Mapper;
import ${package}.${module}.service.${Domain}Service;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
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
    *  分页查询
    * @param ${domain}
    * @param page
    * @param pageSize
    * @return
    */
    @Override
    public PageInfo&lt;${Domain}&gt; page(${Domain} ${domain}, Integer page, Integer pageSize){
        // 分页
        PageHelper.startPage(page,pageSize);
        // 设置排序字段
         PageHelper.orderBy("create_time");
        //查询数据构造返回值
        List&lt;${Domain}&gt; list = ${domain}Mapper.select(${domain});
        return new PageInfo<>(list);
    }
    /**
    *  获取列表
    * @param ${domain}
    * @return
    */
    @Override
    public List&lt;${Domain}&gt; getAll(${Domain} ${domain}) {
        return ${domain}Mapper.select(${domain});
    }

    /**
    *  新增
    * @param ${domain}
    * @return
    */
    @Override
    public Boolean save(${Domain} ${domain}) {
        return ${domain}Mapper.insert(${domain}) > 0;
    }

    /**
    * 更新
    * @param ${domain}
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
