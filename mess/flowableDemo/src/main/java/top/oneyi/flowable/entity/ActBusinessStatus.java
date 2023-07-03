package top.oneyi.flowable.entity;


import lombok.Data;

import java.util.Date;

/**
 * 业务状态实体对象 act_business_status
 *
 * @author gssong
 * @date 2021-10-10
 */
@Data
public class ActBusinessStatus  {

    private static final long serialVersionUID=1L;

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
    /**
     * 创建者
     */
    private String createBy;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新者
     */
    private String updateBy;

    /**
     * 更新时间
     */
    private Date updateTime;
}
