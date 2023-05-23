package top.oneyi.pojo;



import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;


/**
 * 业务状态实体对象 act_business_status
 *
 * @author gssong
 * @date 2021-10-10
 */
@Data
public class ActBusinessStatus{
    private static final long serialVersionUID = 1L;

    /**
     * 创建者
     */
    private String createBy;

    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    /**
     * 更新者
     */

    private String updateBy;

    /**
     * 更新时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;

    /**
     * 请求参数
     */
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private Map<String, Object> params = new HashMap<>();

    /**
     * ID
     */
    private String id;

    /**
     * 业务ID
     */
    private String businessKey;

    /**
     * 流程实例ID
     */
    private String processInstanceId;

    /**
     * 状态
     */
    private String status;

    /**
     * 全类名
     */
    private String classFullName;

    /**
     * 挂起流程原因
     */
    private String suspendedReason;

}
