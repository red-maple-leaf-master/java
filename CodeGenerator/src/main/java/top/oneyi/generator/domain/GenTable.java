package top.oneyi.generator.domain;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: wanyi
 * @Date: 2023/11/24/9:48
 */
@Data
public class GenTable extends BaseEntity{
    private static final long serialVersionUID = 1L;

    /** 编号 */
    private Long tableId;

    /** 表名称 */
    @NotBlank(message = "表名称不能为空")
    private String tableName;

    /** 表描述 */
    @NotBlank(message = "表描述不能为空")
    private String tableComment;

    /** 实体类名称(首字母大写) */
    @NotBlank(message = "实体类名称不能为空")
    private String className;

    /** 数据库名 */
    @NotBlank(message = "数据库名称不能为空")
    private String dataBaseName;

}
