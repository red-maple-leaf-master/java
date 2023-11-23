package top.oneyi.generator.domain;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * Created with IntelliJ IDEA.
 *  代码生成实体类
 * @Author: wanyi
 * @Date: 2023/09/22/16:42
 */
@Data
public class Generator {

    /** 表名称 */
    @NotBlank(message = "表名称不能为空")
    private String tableName;

    /** 数据库名 */
    @NotBlank(message = "表名称不能为空")
    private String dataBaseName;

    /** 生成包路径 */
    @NotBlank(message = "生成包路径不能为空")
    private String packageName;

    /** 生成作者 */
    @NotBlank(message = "作者不能为空")
    private String functionAuthor;

    /** 生成模块名 */
    @NotBlank(message = "生成模块名不能为空")
    private String moduleName;

}
