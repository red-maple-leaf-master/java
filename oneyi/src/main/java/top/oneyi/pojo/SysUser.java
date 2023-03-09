package top.oneyi.pojo;

import lombok.Data;
import top.oneyi.common.BaseEntity;
/**
 * 用户
 * @author oneyi
 * @date 2023/3/9
 */

@Data
public class SysUser extends BaseEntity {
    /**
     * 用户id  自增
     */
    private Long userId;
    /**
     * 昵称
     */
    private String nickName;
    /**
     * 用户名称(就是登录名称)
     */
    private String userName;
    /**
     * 密码
     */
    private String password;
    /**
     * 用户头像
     */
    private String avatar;
    /**
     * 性别
     */
    private String sex;
    /**
     * 手机号码
     */
    private String phoneNumber;
    /**
     * 用户邮箱
     */
    private String email;
    /**
     * 状态  0 启用 1 停用
     */
    public String status;
    /**
     * 是否删除 0 未删除 1 删除
     */
    public String delFlag;
    /**
     * 盐加密
     */
    private String salt;

}
