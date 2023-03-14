package top.oneyi.controller;

import org.activiti.engine.*;
import org.activiti.engine.repository.ProcessDefinition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/workflow/processDefinition")
@RestController
public class ProcessDefinitionController {

    @Autowired
    private RuntimeService runtimeService;
    @Autowired
    private RepositoryService repositoryService;
    @Autowired
    private TaskService taskService;
    @Autowired
    private ManagementService managementService;
    @Autowired
    private HistoryService historyService;

    /**
     * 查询流程实例定义
     * @return
     */
    @RequestMapping("/list")
    public List<ProcessDefinition> list(){
        List<ProcessDefinition> list = repositoryService.createProcessDefinitionQuery().list();
        return list;
    }
}
