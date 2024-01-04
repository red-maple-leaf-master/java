package ${package}.${module}.domain;

import lombok.Data;
import lombok.ToString;
import java.util.Date;
/**
*
* ${Domain}
* @Author: ${author}
* @Date: ${date}
*/
@Data
@ToString
public class ${Domain}{

<#list fieldList as field>
    /**
    *  ${field.comment}
    */
    <#if field_index == 0>
    @javax.persistence.Id
    </#if>
    private ${field.javaType} ${field.nameHump};
</#list>

}
