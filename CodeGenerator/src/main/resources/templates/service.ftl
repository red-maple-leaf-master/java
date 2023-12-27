package ${package}.${module}.service;

import ${package}.${module}.domain.BladeUser;
import com.github.pagehelper.PageInfo;
import java.util.List;
/**
*
* ${Domain}Service
* @Author: ${author}
* @Date: ${date}
*/
public interface ${Domain}Service{

    /**
    *  分页查询
    * @param ${domain}
    * @param page
    * @param pageSize
    * @return
    */
    PageInfo&lt;${Domain}&gt; page(${Domain} ${domain}, Integer page, Integer pageSize);

    /**
    *  获取列表
    * @param ${domain}
    * @return
    */
    List&lt;${Domain}&gt; getAll(${Domain} ${domain});

    /**
    *  新增
    * @param ${domain}
    * @return
    */
    Boolean save(${Domain} ${domain});

    /**
    * 更新
    * @param ${domain}
    * @return
    */
    Boolean updateById(${Domain} ${domain});

    /**
    *  根据id删除
    * @param id
    * @return
    */
    Boolean deleteById(String id);

    /**
    * 根据id获取信息
    * @param id
    * @return
    */
    ${Domain} getById(String id);

}
