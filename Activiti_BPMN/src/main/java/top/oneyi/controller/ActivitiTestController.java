package top.oneyi.controller;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * 审批流程
 *
 * @author oneyi
 * @date 2023/3/1
 */

@RequestMapping("/workflow")
@RestController
public class ActivitiTestController {

    private static final Logger logger = LoggerFactory.getLogger(ActivitiTestController.class);
//    流程id
    private static final String KRY = "wan";
    @Resource
    private RuntimeService runtimeService;

    @Resource
    private TaskService taskService;

    @RequestMapping("/create")
    public void create() {
        //根据 流程定义Id 启动流程
        ProcessInstance processInstance = runtimeService
                .startProcessInstanceByKey(KRY);
        // 输出内容
        System.out.println("流程定义id：" + processInstance.getProcessDefinitionId());
        System.out.println("流程实例id：" + processInstance.getId());
        System.out.println("当前活动Id：" + processInstance.getActivityId());
        // 任务负责人
        String assignee = "zhangsan";
        // 根据流程key 和 任务负责人 查询任务
        List<Task> list = taskService.createTaskQuery()
                .processDefinitionKey(KRY) //流程Key
                .taskAssignee(assignee)//只查询该任务负责人的任务
                .list();
        for (Task task : list) {
            System.out.println("流程实例id：" + task.getProcessInstanceId());
            System.out.println("任务id：" + task.getId());
            System.out.println("任务负责人：" + task.getAssignee());
            System.out.println("任务名称：" + task.getName());

        }
    }


}

