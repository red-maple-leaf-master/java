package top.oneyi.pojo;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 用户表
 */
@Data //lombok注解
@AllArgsConstructor
@NoArgsConstructor
@TableName("user") // 实体类和表明结合起来
public class User extends BaseEntity {
    /**
     * 标志为主键
     */
    @TableId
    private Long id;
    /**
     * 名字
     */
    private String name;
    /**
     * 年龄
     */
    private Integer age;
    /**
     * 邮箱
     */
    private String email;
    /**
     * 用户名
     */
//    private String username;
    /**
     * 用户密码
     */
//    private String password;


}
