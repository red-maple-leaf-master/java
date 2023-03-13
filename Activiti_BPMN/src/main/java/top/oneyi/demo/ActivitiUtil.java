package top.oneyi.demo;


import org.activiti.bpmn.model.BpmnModel;
import org.activiti.bpmn.model.FlowNode;
import org.activiti.engine.*;
import org.activiti.engine.history.HistoricTaskInstance;
import org.activiti.engine.history.HistoricTaskInstanceQuery;
import org.activiti.engine.impl.identity.Authentication;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Comment;
import org.activiti.engine.task.Task;
import org.activiti.bpmn.model.Process;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import top.oneyi.mapper.ActBusinessStatusMapper;
import top.oneyi.pojo.ActBusinessStatus;

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

        List<ProcessInstance> processInstance = runtimeService.createProcessInstanceQuery()
                .processInstanceBusinessKey(businessKey)
                .processDefinitionKey(key)
                .list();
        if(processInstance.size() > 1){
            return null;
        }
        if(processInstance.size() == 0){
            return null;
        }
        return processInstance.get(0);
    }

    @Autowired
    private ActBusinessStatusMapper actBusinessStatusMapper;

    /**
     * 创建流程实例
     *
     * @param businessKey
     * @param key
     * @param variables
     */
    public void createProcessInstance(String businessKey, String key, Map<String, Object> variables) throws Exception {
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        TaskService taskService = processEngine.getTaskService();
        RuntimeService runtimeService = processEngine.getRuntimeService();
        // 业务key判空
        if(StringUtils.isBlank(businessKey)){
            // 抛出异常 或者返回错误代码给前端
            throw new Exception("业务key为null");
        }
        // 判断该业务是否已经开启流程实例
        ProcessInstance processInstance = this.findProcessInstance(businessKey, key);
        if(processInstance != null){
            // 返回该业务 key 已经绑定了流程实例
            throw new Exception(businessKey+"已经绑定了流程实例");
        }
        //在流程实例中设置必要参数
        // 设置启动人
        String roleUserId = "5";
        Authentication.setAuthenticatedUserId(roleUserId);
        // 启动流程实例（提交申请）
/*        variables.put("common", "5");
        variables.put("khjl", "6");
        variables.put("bmjl", "7");
        variables.put("zxfzr", "8");
        variables.put("zjl", "9");*/
        ProcessInstance pi = runtimeService.startProcessInstanceByKey(key, businessKey, variables);
        // 将流程定义名称 作为 流程实例名称
        // 申请人执行流程
        List<Task> taskList = taskService.createTaskQuery().processInstanceId(pi.getId()).list();
        if (taskList.size() > 1) {
            throw new Exception("检查第一个环节是否为申请人");
        }
        // 设置流程定义名称
        runtimeService.setProcessInstanceName(pi.getProcessInstanceId(), pi.getProcessDefinitionName());
        // 设置流程发起人
        taskService.setAssignee(taskList.get(0).getId(),"5");
        taskService.setVariable(taskList.get(0).getId(),"processInstanceId", pi.getProcessInstanceId());
        // 插入业务状态
        ActBusinessStatus actBusinessStatus = new ActBusinessStatus();
        actBusinessStatus.setId(businessKey);
        actBusinessStatus.setBusinessKey(businessKey);
        actBusinessStatus.setProcessInstanceId(pi.getProcessInstanceId());
        actBusinessStatus.setCreateTime(new Date());
        actBusinessStatus.setUpdateTime(new Date());
        actBusinessStatus.setCreateBy("管理员");
        actBusinessStatus.setUpdateBy("管理员");
        actBusinessStatusMapper.insert(actBusinessStatus);

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
     * getTaskDefinitionKey()
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
     * 查询历史任务,返回集合,去重之后获得任务集合,根据每个任务名称来获取对应的任务id去跳转到指定流程
     * getTaskDefinitionKey()
     *
     * @param processInstanceId
     * @return
     */
    public List<HistoricTaskInstance> assignHistoryTasks(String processInstanceId,String assignee) {
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        HistoryService historyService = processEngine.getHistoryService();
        List<HistoricTaskInstance> list = historyService
                .createHistoricTaskInstanceQuery()
                .processInstanceId(processInstanceId)
                .taskAssignee(assignee)
                .orderByHistoricTaskInstanceStartTime()
                .desc()
                .list();
        return list;
    }

    /**
     * 根据实例定义id查询当前的任务
     *
     * @param processInstanceId
     * @return
     */
    public Task findTask(String processInstanceId) {
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        TaskService taskService = processEngine.getTaskService();
        return taskService.createTaskQuery().processInstanceId(processInstanceId).singleResult();
    }

    /**
     * 根据实例定义id查询当前的任务
     *
     * @param businessKey
     * @param key
     * @return
     */
    public Task findTask(String businessKey, String key) {
        ProcessInstance processInstance = this.findProcessInstance(businessKey, key);
        return this.findTask(processInstance.getProcessInstanceId());
    }

    /**
     * 根据流程定义key 和 任务审批人来查找任务
     *
     * @param key
     * @param assinge
     * @return
     */
    public List<Task> findTasks(String key, String assinge) {
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        TaskService taskService = processEngine.getTaskService();
        List<Task> list = taskService
                .createTaskQuery()
                .processDefinitionKey(key)
                .list();
        return list.stream().filter(s -> assinge.equals(s.getAssignee())).collect(Collectors.toList());

    }
    /**
     * 根据流程定义key 和 任务审批人来查找 该审批人的历史任务
     *
     * @param key
     * @param assinge
     * @return
     */
    public List<HistoricTaskInstance> findByassigneeHistoryTasks(String key, String assinge) {
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        HistoryService historyService = processEngine.getHistoryService();
        List<HistoricTaskInstance> list = historyService
                .createHistoricTaskInstanceQuery()
                .processDefinitionKey(key)
                .taskAssignee(assinge)
                .orderByTaskCreateTime()
                .desc()
                .list();
        return list.stream().filter(s -> assinge.equals(s.getAssignee())).collect(Collectors.toList());
    }

    /**
     * 删除流程定义
     * @param businessKey
     * @param key
     */
    public void deleteProcessInstance(String businessKey, String key){
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        RepositoryService repositoryService = processEngine.getRepositoryService();
        // 通过 key  和业务id删除
        ProcessInstance processInstance = this.findProcessInstance(businessKey, key);
        repositoryService.deleteDeployment(processInstance.getId());
    }



    /**
     * 根据流程定义key 和 任务审批人来查找任务批注信息
     *
     * @param key
     * @param key
     * @return
     */
    public List<Comment> findTaskNodes(String businessKey, String key){
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        TaskService taskService = processEngine.getTaskService();
        ProcessInstance processInstance = this.findProcessInstance(businessKey, key);
        List<Comment> comments = taskService.getProcessInstanceComments(processInstance.getProcessInstanceId());//参数为是流程实例ID
        return comments;
    }





}
