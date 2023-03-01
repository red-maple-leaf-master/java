package top.oneyi.controller;

import org.activiti.engine.*;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.repository.ProcessDefinitionQuery;
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
    @Resource
    private RepositoryService repositoryService;

    /**
     *  创建申请 传递流程key
     */
    @RequestMapping("/create")
    public void create(String key) {
        //根据 流程定义Id 启动流程
        runtimeService.startProcessInstanceByKey(key);
        runtimeService.startProcessInstanceById(key,"id");
    }
    /**
     *  查询到所有的流程实例
     */
    @RequestMapping("/list")
    public void list() {
        ProcessDefinitionQuery processDefinitionQuery = repositoryService.createProcessDefinitionQuery();
        List<ProcessDefinition> list = processDefinitionQuery.list();
        for (ProcessDefinition processDefinition : list) {
            System.out.println("流程定义 id=" + processDefinition.getId());
            System.out.println("流程定义 name=" + processDefinition.getName());
            System.out.println("流程定义 key=" + processDefinition.getKey());
            System.out.println("流程定义 Version=" + processDefinition.getVersion());
            System.out.println("流程部署ID =" + processDefinition.getDeploymentId());
        }
    }


}

