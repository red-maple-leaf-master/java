package ${package}.${module}.service;

import ${package}.${module}.domain.BladeUser;

import java.util.List;

public interface ${Domain}Service{

    List<${Domain}> getAll(${Domain} ${domain});

    Boolean save(${Domain} ${domain});

    Boolean updateById(${Domain} ${domain});

    Boolean deleteById(String id);

    ${Domain} getById(String id);

}
