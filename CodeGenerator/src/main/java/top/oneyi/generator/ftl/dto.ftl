package top.oneyi.${module}.dto;

import lombok.Data;
import lombok.ToString;
import java.util.Date;

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
