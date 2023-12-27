package ${package}.${module}.dto;

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
public class ${Domain}Dto {

<#list fieldList as field>
  /**
   *  ${field.comment}
   */
   private ${field.javaType} ${field.nameHump};
</#list>

}
