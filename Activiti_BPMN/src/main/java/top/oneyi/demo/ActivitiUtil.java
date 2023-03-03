package top.oneyi.demo;


import org.activiti.bpmn.model.BpmnModel;
import org.activiti.bpmn.model.FlowNode;
import org.activiti.engine.*;
import org.activiti.engine.history.HistoricTaskInstance;
import org.activiti.engine.history.HistoricTaskInstanceQuery;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.activiti.bpmn.model.Process;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;

/**
 * activiti 工具类
 * 如果和springboot整合之后可以直接注入那几个常用的对象
 *
 * @author oneyi
 * @date 2023/3/3
 */
@Component
public class ActivitiUtil {


    /**
     * 跳转指定任务节点
     * 保证一个业务key对应上一个流程实例
     *
     * @param processInstance 流程实例对象
     * @param goalNode        目标任务节点的 TASK_DEF_KEY_ 任务历史表的值
     * @param businessKey     业务id
     */
    public void jumpTask(ProcessInstance processInstance, String goalNode, String businessKey) {

        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        TaskService taskService = processEngine.getTaskService();
        RepositoryService repositoryService = processEngine.getRepositoryService();
        ManagementService managementService = processEngine.getManagementService();

        // 获取当前任务
        Task currentTask = taskService.createTaskQuery()
                .processInstanceBusinessKey(businessKey)
                .processInstanceId(processInstance.getId())
                .singleResult();
        BpmnModel bpmnModel = repositoryService.getBpmnModel(currentTask.getProcessDefinitionId());
        // 获取流程定义
        Process process = bpmnModel.getMainProcess();
        // 获取目标节点定义
        FlowNode targetNode = (FlowNode) process.getFlowElement(goalNode);
        // 删除当前运行任务，同时返回执行id，该id在并发情况下也是唯一的
        String executionEntityId = managementService.executeCommand(new DeleteTaskCmd(currentTask.getId()));
        // 流程执行到来源节点
        managementService.executeCommand(new SetFLowNodeAndGoCmd(targetNode, executionEntityId));
    }

    /**
     * 获取流程实例
     *
     * @param businessKey
     * @param key
     * @return
     */
    public ProcessInstance findProcessInstance(String businessKey, String key) {
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        RuntimeService runtimeService = processEngine.getRuntimeService();

        ProcessInstance processInstance = runtimeService.createProcessInstanceQuery()
                .processInstanceBusinessKey(businessKey)
                .processDefinitionKey(key)
                .singleResult();
        return processInstance;
    }

    /**
     * 创建流程实例
     *
     * @param businessKey
     * @param key
     * @param variables
     */
    public void createProcessInstance(String businessKey, String key, Map<String, Object> variables) {
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        RuntimeService runtimeService = processEngine.getRuntimeService();
        ProcessInstance processInstance = this.findProcessInstance(businessKey, key);
        // 防止同一个业务key创建多个流程实例
        if (processInstance == null) {
            runtimeService.startProcessInstanceByKey(key, businessKey, variables);
        }
    }

    /**
     * 创建流程实例
     *
     * @param businessKey
     * @param key
     */
    public void createProcessInstance(String businessKey, String key) {
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        RuntimeService runtimeService = processEngine.getRuntimeService();
        ProcessInstance processInstance = this.findProcessInstance(businessKey, key);
        // 防止同一个业务key创建多个流程实例
        if (processInstance == null) {
            runtimeService.startProcessInstanceByKey(key, businessKey);
        }
    }

    /**
     * 查询历史任务,返回集合,去重之后获得任务集合,根据每个任务名称来获取对应的任务id去跳转到指定流程
     *  getTaskDefinitionKey()
     *
     * @param processInstanceId
     * @return
     */
    public List<HistoricTaskInstance> historyTasks(String processInstanceId) {
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        HistoryService historyService = processEngine.getHistoryService();
        List<HistoricTaskInstance> list = historyService.createHistoricTaskInstanceQuery().processInstanceId(processInstanceId).list();
        list = list.stream().collect(Collectors.collectingAndThen(
                Collectors.toCollection(() -> new TreeSet<>(
                        Comparator.comparing(HistoricTaskInstance::getTaskDefinitionKey))), ArrayList::new));
        return list;
    }

    /**
     * 根据实例定义id查询当前的任务
     * @param processInstanceId
     * @return
     */
    public Task findTask(String processInstanceId){
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        TaskService taskService = processEngine.getTaskService();
        return taskService.createTaskQuery().processInstanceId(processInstanceId).singleResult();
    }

    /**
     * 根据流程定义key 和 任务审批人来查找任务
     * @param key
     * @param assinge
     * @return
     */
    public List<Task> findTasks(String key,String assinge){
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        TaskService taskService = processEngine.getTaskService();
        List<Task> list = taskService.createTaskQuery().processDefinitionKey(key).list();
        return list.stream().filter(s->assinge.equals(s.getAssignee())).collect(Collectors.toList());

    }


}
