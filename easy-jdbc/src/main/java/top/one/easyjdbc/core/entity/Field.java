package top.one.easyjdbc.core.entity;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class Field {
    /**
     * 数据库字段
     */
    private String name;
    /**
     * 小驼峰
     */
    private String nameHump;
    /**
     * 大驼峰
     */
    private String nameBigHump;
    /**
     * 字段注释
     */
    private String comment;
    /**
     * Java数据类型
     */
    private String javaType;
    /**
     * 数据库数据类型
     */
    private String type;
    /**
     * 注释中出现 | 进行去除 (中文名字)
     */
    private String nameCn;
    /**
     * 是否非空
     */
    private boolean nullAble;
    /**
     * 数据库字段长度
     */
    private Integer length;

}
