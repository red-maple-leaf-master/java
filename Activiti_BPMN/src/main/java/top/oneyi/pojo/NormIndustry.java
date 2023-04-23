package top.oneyi.pojo;


import lombok.Data;

@Data
public class NormIndustry {
    /**
     * id
     */
    private Long id;
    /**
     * 行业编号
     */
    private String code;
    /**
     * 行业名称
     */
    private String name;
    /**
     * 父行业编号
     */
    private String parentCode;
    /**
     * 是否有子节点  1是 0 否
     */
    private Integer hasChild;

}
