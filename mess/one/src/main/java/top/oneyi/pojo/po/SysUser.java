package top.oneyi.pojo.po;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

/**
 * 用户
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SysUser {
    /**
     * 主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "SELECT LAST_INSERT_ID()")
    private Long id;

    /**
     * 用户名
     */
    @NotNull
    private String userName;

    /**
     * 用户密码
     */
    @NotNull
    private String userPassword;

    /**
     * 用户创建时间
     */
    private Date createTime;

    /**
     * 用户更新时间
     */
    private Date updateTime;

    /**
     * 用户状态0废除，1启用
     */
    private Integer state;
    /**
     * 真实名称
     */
    private String realName;
    /**
     * 仓库编号
     */
    private String storehouseNum;
    /**
     * 0 出现提示弹框   1 不出现提示弹框
     */
    private Integer isShow;
    /**
     * 首页路径
     */
    private String url;

    private List<SysRole> newParts;
}
