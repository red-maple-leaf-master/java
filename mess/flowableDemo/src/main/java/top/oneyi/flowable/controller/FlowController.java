package top.oneyi.flowable.controller;

import org.apache.commons.lang3.StringUtils;
import org.flowable.bpmn.model.BpmnModel;
import org.flowable.bpmn.model.FlowElement;
import org.flowable.bpmn.model.Process;
import org.flowable.engine.*;
import org.flowable.engine.common.impl.identity.Authentication;
import org.flowable.engine.repository.Deployment;
import org.flowable.engine.runtime.ProcessInstance;
import org.flowable.task.api.Task;


import org.flowable.task.api.history.HistoricTaskInstance;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.oneyi.flowable.entity.HistoryTask;
import top.oneyi.flowable.entity.ProcessInstRunningVo;

import javax.annotation.Resource;
import java.util.*;
import java.util.stream.Collectors;

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
     * 部署
     * @return
     */
    @GetMapping("/deploy")
    public String deploy(){
        Deployment deployment = repositoryService.createDeployment()
                .addClasspathResource("flowable/eon.bpmn20.xml")
                .name( "请假流程" )
                .deploy();
        return "部署成功";
    }


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
        // 设置启动人
        Authentication.setAuthenticatedUserId("admin");
        runtimeService.startProcessInstanceById(key,businessKey,map);
        return "开启流程成功";
    }
    /**
     * 查看正在运行的流程实例
     * @param businessKey
     * @param key
     * @return
     */
    @GetMapping("/processInstanceList")
    public List<ProcessInstRunningVo> processInstanceList(String businessKey,String key){
        List<ProcessInstance> processInstances = runtimeService.createProcessInstanceQuery()
                .processDefinitionKey(key) // 暂时只看指定流程定义的流程实例
//                .processInstanceBusinessKey(businessKey)
                .list();
        List<ProcessInstRunningVo> vos = new ArrayList<>();
        for (ProcessInstance processInstance : processInstances) {
            ProcessInstRunningVo runningVo = new ProcessInstRunningVo();
            BeanUtils.copyProperties(processInstance,runningVo);
            // 设置流程定义名称
            runningVo.setName(processInstance.getProcessDefinitionName());
            // 设置流程实例状态
            runningVo.setIsSuspended(processInstance.isSuspended() ? "挂起" : "激活");
            // 设置开启流程实例的发起人
            runningVo.setStartUserId(processInstance.getStartUserId());
            runningVo.setStartUserNickName(processInstance.getStartUserId());

            // 查询流程实例的任务
            List<Task> taskList = taskService.createTaskQuery().processInstanceId(processInstance.getProcessInstanceId()).list();
            //办理人
            StringBuilder currTaskInfo = new StringBuilder();
            //办理人id
            StringBuilder currTaskInfoId = new StringBuilder();
            //办理人集合
            List<String> nickNameList = null;
            for (Task task : taskList.stream().filter(e -> StringUtils.isBlank(e.getParentTaskId())).collect(Collectors.toList())) {
                // 暂时 使用id  后续使用user
                List<Long> nickNameLongList = new ArrayList<>();
                if (StringUtils.isNotBlank(task.getAssignee())) {
                    String[] split = task.getAssignee().split(",");
                    List<Long> userIds = new ArrayList<>();
                    for (String userId : split) {
                        userIds.add(Long.valueOf(userId));
                    }
                    //办理人
/*                    List<SysUser> sysUsers = iUserService.selectListUserByIds(userIds);
                    if (CollectionUtil.isNotEmpty(sysUsers)) {
                        nickNameList = sysUsers.stream().map(SysUser::getNickName).collect(Collectors.toList());
                    }*/
                    nickNameLongList.addAll(userIds);
                }


//                currTaskInfo.append("任务名【").append(task.getName()).append("】，办理人【").append(StringUtils.join(nickNameList, ",")).append("】");
                currTaskInfo.append("任务名【").append(task.getName()).append("】，办理人【").append(StringUtils.join(nickNameLongList, ",")).append("】");
                currTaskInfoId.append(task.getAssignee());
            }
            // 设置当前任务详情信息和当前任务办理人id
            runningVo.setCurrTaskInfo(currTaskInfo.toString());
            runningVo.setCurrTaskInfoId(currTaskInfoId.toString());
            vos.add(runningVo);
        }
        return vos;
    }

    /**
     * 通过
     * @param businessKey
     * @return
     */
    @GetMapping("/pass")
    public String pass(String businessKey,String key){
//        Task task = taskService.createTaskQuery().processInstanceBusinessKey(businessKey).processDefinitionKey(key).singleResult();
        List<Task> list = taskService.createTaskQuery().processInstanceBusinessKey(businessKey).processDefinitionKey(key).list();
        if(list.size() == 0){
            return "流程实例已结束";
        }
        for (Task task1 : list) {
            //通过审核
            HashMap<String, Object> map = new HashMap<>();
            map.put("outcome", "通过");
            taskService.complete(task1.getId(),map);
        }

        return "通过";
    }
    /**
     * 详细信息
     * @param businessKey
     * @return
     */
    @GetMapping("/passInfo")
    public String passInfo(String businessKey,String key){
        ProcessInstance processInstance = runtimeService.createProcessInstanceQuery()
                .processDefinitionKey(key) // 暂时只看指定流程定义的流程实例
                .processInstanceBusinessKey(businessKey)
                .singleResult();
        BpmnModel bpmnModel = repositoryService.getBpmnModel(processInstance.getProcessDefinitionId());
        List<Process> processes = bpmnModel.getProcesses();
        for (Process process : processes) {
            Collection<FlowElement> flowElements = process.getFlowElements();
            for (FlowElement flowElement : flowElements) {
                System.out.println("flowElement = " + flowElement);
            }
        }


//        Task task = taskService.createTaskQuery().processInstanceBusinessKey(businessKey).processDefinitionKey(key).singleResult();
        List<Task> list = taskService.createTaskQuery().processInstanceBusinessKey(businessKey).processDefinitionKey(key).list();
        if(list.size() == 0){
            return "流程实例已结束";
        }
        for (Task task1 : list) {
            //通过审核
            taskService.setVariable(task1.getId(),"one","12315646");
            taskService.setVariable(task1.getId(),"stree","333333");
            taskService.setVariable(task1.getId(),"two","222222222");

            Map<String, Object> variables = taskService.getVariables(task1.getId());
            System.out.println("variables = " + variables);
//            HashMap<String, Object> map = new HashMap<>();
//            map.put("outcome", "通过");
//            taskService.complete(task1.getId(),map);
        }

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
    public List<HistoryTask> historyTask(String businessKey,String key){
        List<HistoricTaskInstance> list = historyService.createHistoricTaskInstanceQuery().processInstanceBusinessKey(businessKey).processDefinitionKey(key).list();

        List<HistoryTask> tasks = new ArrayList<>();
        for (HistoricTaskInstance historicTaskInstance : list) {
            HistoryTask historyTask = new HistoryTask();
            historyTask.setTaskId(historicTaskInstance.getId());
            historyTask.setTaskDefKey(historicTaskInstance.getTaskDefinitionKey());
            historyTask.setTaskName(historicTaskInstance.getName());
            historyTask.setProcessInstanceId(historicTaskInstance.getProcessInstanceId());
            historyTask.setAssignee(historicTaskInstance.getAssignee());
            historyTask.setCreateTime(historicTaskInstance.getStartTime());
            historyTask.setEndTime(historicTaskInstance.getEndTime());
            tasks.add(historyTask);
        }
        for (HistoryTask task : tasks) {
            System.out.println(task);
        }
        return tasks;
    }

}
