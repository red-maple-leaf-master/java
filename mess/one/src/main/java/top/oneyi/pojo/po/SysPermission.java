package top.oneyi.pojo.po;

import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**权限
 * @author xjh
 */
@Data
public class SysPermission {
    /**
     * 主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "SELECT LAST_INSERT_ID()")
    private Long id;

    /**
     *菜单代码
     */
    @NotNull
    private String menuCode;
    /**
     *菜单名
     */
    @NotNull
    private String menuName;
    /**
     *权限代码
     */
    @NotNull
    private String permissionCode;

    /**
     *权限名称
     */
    @NotNull
    private String permissionName;
    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 角色状态0废除1启用
     */
    private Integer state;

    /**
     *备注
     */

    private String remarks;
}
