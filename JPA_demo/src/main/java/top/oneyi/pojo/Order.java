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


@Data
@Entity
@Table(name = "sys_order")
@EntityListeners(AuditingEntityListener.class)
public class Order {
    /**
     * id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    /**
     * 订单号
     */
    private Long orderNum;
    /**
     * 书名
     */
    private String bookName;
    /**
     * 用户名字
     */
    private String CustomerName;

    /**
     * 和用户多对一
     *  设置为 级联 保存   级联刷新
     * optional=false 可选属性  false 代表该字段不能为空
     *
     *  cascade=CascadeType.ALL, 级联保存、更新、删除、刷新; fetch=FetchType.LAZY延迟加载。
     */
//    @ManyToOne(cascade={CascadeType.MERGE,CascadeType.REMOVE},optional=false)
    @ManyToOne
    @JoinColumn(name="customer_id")
    private Customer customer;
    /**
     * JoinTable注解指定中间表名字   指定其中的 关联的字段名称
     */
    @ManyToMany
    @JoinTable(name="book_order",joinColumns = @JoinColumn(name ="order_id"),inverseJoinColumns = @JoinColumn(name="book_id"))
    private List<Book> bookList;

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
