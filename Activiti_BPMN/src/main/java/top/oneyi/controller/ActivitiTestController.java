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

import java.util.List;

@RequestMapping("/test")
@RestController
public class ActivitiTestController {

    private static final Logger logger = LoggerFactory.getLogger(ActivitiTestController.class);

    @Autowired
    RuntimeService runtimeService;

    @Autowired
    private TaskService taskService;

    @RequestMapping("/test1")
    public void test1() {
        logger.info("Start.........");
        ProcessInstance pi = runtimeService.startProcessInstanceByKey("test");
        logger.info("流程启动成功，流程id:{}", pi.getId());
    }


    @RequestMapping("/test2")
    public void test2() {
        //        任务负责人
        String assignee = "zhangsan";
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
//        创建TaskService
        TaskService taskService = processEngine.getTaskService();
//        根据流程key 和 任务负责人 查询任务
        List<Task> list = taskService.createTaskQuery()
                .processDefinitionKey("wan") //流程Key
                .taskAssignee(assignee)//只查询该任务负责人的任务
                .list();
        logger.info("任务列表：{}", list);
    }

}

