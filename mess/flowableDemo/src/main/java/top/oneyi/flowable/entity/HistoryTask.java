package top.oneyi.flowable.entity;

import lombok.Data;

import java.util.Date;

@Data
public class HistoryTask {
    /**
     * 任务id
     */
    private String taskId;
    /**
     * 任务节点key
     */
    private String taskDefKey;
    /**
     * 任务名称
     */
    private String taskName;
    /**
     * 委派人
     */
    private String assignee;
    /**
     * 流程实例id
     */
    private String processInstanceId;

    /**
     * 创建时间
     */
    private Date createTime;


    /**
     * 更新时间
     */
    private Date endTime;


}
