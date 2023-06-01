package top.oneyi.flowable.controller;

import org.flowable.engine.*;
import org.flowable.engine.runtime.ProcessInstance;
import org.flowable.task.api.Task;


import org.flowable.task.api.history.HistoricTaskInstance;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/flow")
public class FlowController {

    @Resource
    private RuntimeService runtimeService;
    @Resource
    private TaskService taskService;
    @Resource
    private RepositoryService repositoryService;
    @Resource
    private ProcessEngine processEngine;
    @Resource
    private HistoryService historyService;


    /**
     * 开启流程
     * @param businessKey
     * @param key
     * @return
     */
    @GetMapping("/startFlow")
    public String startFlow(String businessKey,String key){
        Map<String,Object> map = new HashMap<>();
        map.put("one","1");
        map.put("two","2");
        map.put("stree","3");
        runtimeService.startProcessInstanceById(key,businessKey,map);
        return "开启流程成功";
    }

    /**
     * 通过
     * @param businessKey
     * @return
     */
    @GetMapping("/pass")
    public String pass(String businessKey,String key){
        Task task = taskService.createTaskQuery().processInstanceBusinessKey(businessKey).processDefinitionKey(key).singleResult();
        //通过审核
        HashMap<String, Object> map = new HashMap<>();
        map.put("outcome", "通过");
        taskService.complete(task.getId(),map);
        return "通过";
    }

    /**
     * 驳回
     * @param businessKey
     * @param message
     * @return
     */
    @GetMapping("/reject")
    public String reject(String businessKey,String key,String message){
        ProcessInstance processInstance = runtimeService.createProcessInstanceQuery()
                .processInstanceBusinessKey(businessKey)
                .processDefinitionKey(key).singleResult();
        Task task = taskService.createTaskQuery().processInstanceBusinessKey(businessKey).processDefinitionKey(key).singleResult();
        //通过审核
        HashMap<String, Object> map = new HashMap<>();
        map.put("outcome", "驳回");
        taskService.addComment(task.getId(),processInstance.getId(),message);
        taskService.complete(task.getId(),map);
        return "不通过";
    }


    /**
     * 历史任务
     * @param businessKey
     * @param key
     * @return
     */
    @GetMapping("/historyTask")
    public String historyTask(String businessKey,String key){
        List<HistoricTaskInstance> list = historyService.createHistoricTaskInstanceQuery().processInstanceBusinessKey(businessKey).processDefinitionKey(key).list();
        for (HistoricTaskInstance historicTaskInstance : list) {

        }

        return "历史任务打印在控制台";
    }

}
