package top.oneyi.pojo.po;

import lombok.Data;
import top.oneyi.common.Tree;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import java.util.Date;


/**
 * 菜单
 *
 * @author Administrator
 */
@Data
public class SysMenu extends Tree {
    /**
     * 主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "SELECT LAST_INSERT_ID()")
    private Long id;

    /**
     * 父级菜单
     */
    private Long parentId;

    /**
     * 父菜单名称
     */
    @Transient
    private String parentName;
    /**
     * 菜单路径
     */
    @NotNull
    private String url;

    /**
     * 菜单类型
     */
    @NotNull
    private Integer type;

    /**
     * 授权
     */
    private String perms;
    /**
     * 菜单名称
     */
    @NotNull
    private String name;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 0废除1启用
     */
    private Integer state;
    /**
     * 菜单图标
     */
    private String icon;
    /**
     * 排序
     */
    private Integer sort;
}
