package top.oneyi.pojo;

import lombok.Data;
/**
 *  生成配置类
 * @author oneyi
 * @date 2023/5/19
 */
@Data
public class Generator {
    /**
     * 作者
     */
    private String author;
    /**
     * 包名
     */
    private String packageName;
    /**
     * 模块名字
     */
    private String moduleName;
    /**
     * 表名字
     */
    private String tableName;
    /**
     * 忽略表前缀
     */
    private String tablePrefix;

}
