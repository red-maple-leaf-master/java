package ${package}.${module}.service;

import ${package}.${module}.domain.BladeUser;

import java.util.List;
/**
*
* ${Domain}Service
* @Author: ${author}
* @Date: ${date}
*/
public interface ${Domain}Service{

    List&lt${Domain}&gt; getAll(${Domain} ${domain});

    Boolean save(${Domain} ${domain});

    Boolean updateById(${Domain} ${domain});

    Boolean deleteById(String id);

    ${Domain} getById(String id);

}
