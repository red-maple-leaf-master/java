package top.oneyi.pojo;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data //lombok注解
@AllArgsConstructor
@NoArgsConstructor
@TableName("user") // 实体类和表明结合起来
public class User {
    /**
     * 标志为主键
     */
    @TableId
    private Long id;
    private String name;
    private Integer age;
    private String email;
}