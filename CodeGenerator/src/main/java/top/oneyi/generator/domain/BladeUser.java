package top.oneyi.generator.domain;

import lombok.Data;
import lombok.ToString;
import java.util.Date;

@Data
@ToString
public class BladeUser{

    /**
    *  主键
    */
    private Integer id;
    /**
    *  租户ID
    */
    private String tenantId;
    /**
    *  用户编号
    */
    private String code;
    /**
    *  账号
    */
    private String account;
    /**
    *  密码
    */
    private String password;
    /**
    *  昵称
    */
    private String name;
    /**
    *  真名
    */
    private String realName;
    /**
    *  头像
    */
    private String avatar;
    /**
    *  邮箱
    */
    private String email;
    /**
    *  手机
    */
    private String phone;
    /**
    *  生日
    */
    private Date birthday;
    /**
    *  性别
    */
    private Integer sex;
    /**
    *  角色id
    */
    private String roleId;
    /**
    *  部门id
    */
    private String deptId;
    /**
    *  岗位id
    */
    private String postId;
    /**
    *  创建人
    */
    private Integer createUser;
    /**
    *  创建部门
    */
    private Integer createDept;
    /**
    *  创建时间
    */
    private Date createTime;
    /**
    *  修改人
    */
    private Integer updateUser;
    /**
    *  修改时间
    */
    private Date updateTime;
    /**
    *  状态
    */
    private Integer status;
    /**
    *  是否已删除
    */
    private Integer isDeleted;

}
