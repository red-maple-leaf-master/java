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
import java.util.List;

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
     * 用户编码
     */
    private String customerNumber;
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
     * 订单的集合
     * cascade=CascadeType.ALL, 级联保存、更新、删除、刷新; fetch=FetchType.LAZY延迟加载。
     * 当删除用户，会级联删除该用户的所有书籍
     *  拥有mappedBy="customer"(customer 是在 order 中的 customer 属性) 注解的实体类为关系被维护端
     */
    @OneToMany(mappedBy = "customer",cascade=CascadeType.ALL)
    private List<Order> orderList;

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


    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", customerNumber='" + customerNumber + '\'' +
                ", password='" + password + '\'' +
                ", objectVersion=" + objectVersion +
                ", age='" + age + '\'' +
                ", email='" + email + '\'' +
                ", createTime=" + createTime +
                ", createBy='" + createBy + '\'' +
                ", updateTime=" + updateTime +
                ", updateBy='" + updateBy + '\'' +
                '}';
    }
}
