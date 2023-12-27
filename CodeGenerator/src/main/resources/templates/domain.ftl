package ${package}.${module}.domain;

import lombok.Data;
import lombok.ToString;
import java.util.Date;
<#if field.nameHump?contains("id")>
import javax.persistence.Id;
</#if>
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
    <#if field.nameHump?contains("id")>
    @Id
    </#if>
    private ${field.javaType} ${field.nameHump};
</#list>

}
