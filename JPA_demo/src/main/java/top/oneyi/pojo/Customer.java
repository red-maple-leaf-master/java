package top.oneyi.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;

/**
 * 用户类
 *
 *  注解 @EntityListeners(AuditingEntityListener.class) 主要是维护 创建时间和更新时间
 */
@Data
@Entity
@EntityListeners(AuditingEntityListener.class)
public class Customer {
    /**
     * id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    /**
     *  用户名
     */
    private String name;
    /**
     *  用户密码
     */
    private String password;
    /**
     *  版本控制  更新的需要传递过去
     */
    @Version
    private Long objectVersion;
    /**
     *  用户年龄
     */
    private String age;
    /**
     * 用户邮箱
     */
    private String email;
    /**
     * 创建时间
     */
    @CreatedDate
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;
    /**
     * 创建人
     */
    @CreatedBy
    private String createBy;
    /**
     * 更新时间
     */
    @LastModifiedDate
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date updateTime;
    /**
     * 更新人
     */
    @LastModifiedBy
    private String updateBy;


}
