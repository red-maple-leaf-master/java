package top.oneyi;

import org.activiti.bpmn.model.*;
import org.activiti.bpmn.model.Process;
import org.activiti.engine.*;
import org.activiti.engine.history.HistoricProcessInstance;
import org.activiti.engine.history.HistoricTaskInstance;
import org.activiti.engine.impl.util.CollectionUtil;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Comment;
import org.activiti.engine.task.DelegationState;
import org.activiti.engine.task.Task;
import org.activiti.engine.task.TaskQuery;
import org.assertj.core.util.DateUtil;
import org.junit.Test;
import org.junit.platform.commons.util.StringUtils;
import org.junit.runner.RunWith;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;
import top.oneyi.demo.ActivitiStartInstance;
import top.oneyi.demo.ActivitiUtil;
import top.oneyi.mapper.ActBusinessStatusMapper;
import top.oneyi.mapper.SysUserMapper02;
import top.oneyi.pojo.ActBusinessStatus;
import top.oneyi.pojo.ActHistoryInfoVo;
import top.oneyi.pojo.ActProcessNodeVo;
import top.oneyi.pojo.SysUser;
import top.oneyi.util.OneUtils;

import javax.annotation.Resource;
import java.util.*;
import java.util.stream.Collectors;

@RunWith(SpringRunner.class)//当前类为 springBoot 的测试类
@SpringBootTest(classes = ActivtiSpringBootApplication.class)//加载 SpringBoot 启动类
public class ActivitiDome {

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

    private static final String KEY = "financial";
    private static final String BussinessKey = "5";
    @Autowired
    private ActBusinessStatusMapper businessStatusMapper;

    @Autowired
    private ActivitiUtil activitiUtil;

    /**
     * 部署流程
     */
    @Test
    public void deploy() {
        Deployment deployment = repositoryService.createDeployment()
                .addClasspathResource("diagram/guaranty.bpmn")
                .addClasspathResource("diagram/guaranty.png")
                .name("流程定义测试")
                .deploy();
    }

    /**
     * 启动流程
     */
    public ProcessInstance startFlow(String businessKey, String key) {
        Map<String, Object> map = new HashMap<>();
        map.put("common", "5");
        map.put("khjl", "6");
        map.put("bmjl", "7");
        map.put("zxfzr", "8");
        map.put("zjl", "9");
        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey(key, businessKey, map);
        ActBusinessStatus actBusinessStatus = new ActBusinessStatus();
        actBusinessStatus.setId(businessKey);
        actBusinessStatus.setBusinessKey(businessKey);
        actBusinessStatus.setProcessInstanceId(processInstance.getProcessInstanceId());
        actBusinessStatus.setCreateTime(new Date());
        actBusinessStatus.setUpdateTime(new Date());
        actBusinessStatus.setCreateBy("管理员");
        actBusinessStatus.setUpdateBy("管理员");
        businessStatusMapper.insert(actBusinessStatus);
        // 启动流程实例 添加业务id
        return processInstance;
    }

    /**
     * 使用指定业务key开始流程实例
     */
    @Test
    public void start() {
        // id 为1 的业务
//        ProcessInstance processInstance = this.startFlow(BussinessKey, KEY);
//        ProcessInstance processInstance = this.startFlow("2", KEY);
//        ProcessInstance processInstance = this.startFlow("3", KEY);
//        ProcessInstance processInstance = this.startFlow("4", KEY);
//        ProcessInstance processInstance = this.startFlow("6", KEY);
        ProcessInstance processInstance = this.startFlow("10", KEY);
    }

    @Resource
    private OneUtils oneUtils;

    /**
     * 根据审批人查找任务
     */
    @Test
    public void findByassign() {
        List<HistoricTaskInstance> list = historyService.createHistoricTaskInstanceQuery()
                .processDefinitionKey(KEY)
                .orderByHistoricTaskInstanceStartTime()
                .desc()
                .taskAssignee("7")
                .list();
        list = list.stream().collect(Collectors.collectingAndThen(
                Collectors.toCollection(() -> new TreeSet<>(
                        Comparator.comparing(HistoricTaskInstance::getProcessInstanceId))), ArrayList::new));
        Map<String, String> map = new HashMap<>();
        for (HistoricTaskInstance historicTaskInstance : list) {
            String processInstanceId = historicTaskInstance.getProcessInstanceId();
            ActBusinessStatus actBusinessStatus = businessStatusMapper.selectByProcessInstanceId(processInstanceId);
            if (historicTaskInstance.getDeleteReason() != null && historicTaskInstance.getEndTime() != null) {
                map.put("业务key", actBusinessStatus.getBusinessKey());
                map.put("操作人", historicTaskInstance.getAssignee());
                map.put("操作状态 ", "已驳回");
            } else if (historicTaskInstance.getDeleteReason() == null && historicTaskInstance.getEndTime() != null) {
                map.put("业务key", actBusinessStatus.getBusinessKey());
                map.put("操作人", historicTaskInstance.getAssignee());
                map.put("操作状态 ", "已通过");
            } else {
                map.put("业务key", actBusinessStatus.getBusinessKey());
                map.put("操作人", historicTaskInstance.getAssignee());
                map.put("操作状态 ", "待审批");
            }
            System.out.println(map);

        }
        List<String> collect = list.stream().map(HistoricTaskInstance::getProcessInstanceId).collect(Collectors.toList());
        List<String> byProcessIds = oneUtils.findByProcessIds(businessStatusMapper, collect);
        for (String byProcessId : byProcessIds) {
            System.out.println("业务Key: == " + byProcessId);
        }

    }

    /**
     * 根据业务key查找到该数据的历史审批记录和
     */
    @Test
    public void test() {
        String businessKey = "6";
        ProcessInstance processInstance = activitiUtil.findProcessInstance(businessKey, KEY);
        List<Comment> taskNodes = activitiUtil.findTaskNodes(businessKey, KEY);
        for (Comment taskNode : taskNodes) {
            System.out.println("taskNode.getFullMessage() = " + taskNode.getFullMessage());
            System.out.println("taskNode.getType() = " + taskNode.getType());
            System.out.println("taskNode.getTaskId() = " + taskNode.getTaskId());
            System.out.println("taskNode.getUserId() = " + taskNode.getUserId());
        }

    }


    /**
     * 根据业务key找到任务进行到哪一步了
     */
    @Test
    public void findByBussiness() {
        Task task = activitiUtil.findTask("5", KEY);
        System.out.println("业务key:" + "5");
        System.out.println("操作状态: 待审批");
        System.out.println("当前操作人:" + task.getAssignee());
        String parentTaskId = task.getParentTaskId();
        TaskQuery taskQuery = taskService.createTaskQuery().taskParentTaskId(parentTaskId);
        List<Task> list = taskQuery.list();
        for (Task task1 : list) {
            System.out.println("task1.getId() = " + task1.getId());
            System.out.println("task1.getAssignee() = " + task1.getAssignee());
            System.out.println("task1.getParentTaskId() = " + task1.getParentTaskId());
            System.out.println("==========================================================");
        }


    }

    /**
     * 完成任务
     */
    @Test
    public void doTask() {
        ProcessInstance processInstance = activitiUtil.findProcessInstance("10", KEY);
        if (processInstance != null) {

            Task task = activitiUtil.findTask(processInstance.getProcessInstanceId());
            taskService.addComment(task.getId(), task.getProcessInstanceId(), task.getName() + ":同意:" + task.getAssignee(), "通过意见");
            taskService.complete(task.getId());
        } else {
            System.out.println("任务已终结");
        }

    }

    /**
     * 跳过任务
     */
    @Test
    public void jumpTest() {
        ProcessInstance processInstance = activitiUtil.findProcessInstance("6", KEY);
        List<HistoricTaskInstance> historicTaskInstances = activitiUtil.historyTasks(processInstance.getProcessInstanceId());
        for (HistoricTaskInstance historicTaskInstance : historicTaskInstances) {
            // 根据跳转指定节点名称确定
            if ("客户提交担保物".equals(historicTaskInstance.getName())) {
                Task task = activitiUtil.findTask(processInstance.getProcessInstanceId());
                taskService.addComment(task.getId(), task.getProcessInstanceId(), task.getName() + "-: 不同意", "担保物太便宜了,不予通过");
                // taskDefinitionKey 每个节点都一样 id是不一样的
                String taskDefinitionKey = historicTaskInstance.getTaskDefinitionKey();
                // 流程实例 跳转转任务节点，业务id
                activitiUtil.jumpTask(processInstance, taskDefinitionKey, "6");
            }
        }

    }

    @Test
    public void test02() {
/*        List<HistoricProcessInstance> instanceList = historyService.createHistoricProcessInstanceQuery()
                .processInstanceBusinessKey("5").list();
        for (HistoricProcessInstance historicProcessInstance : instanceList) {
            System.out.println("historicProcessInstance.getStartTime() = " + historicProcessInstance.getStartTime());

        }*/


    }


    @Test
    public void test03() {
        ProcessInstance processInstance = activitiUtil.findProcessInstance("9", KEY);
        BpmnModel bpmnModel = repositoryService.getBpmnModel(processInstance.getProcessDefinitionId());
        List<Process> processes = bpmnModel.getProcesses();
        List<ActProcessNodeVo> processNodeVoList = new ArrayList<>();
        Collection<FlowElement> flowElements = processes.get(0).getFlowElements();
        Collection<FlowElement> elements = new ArrayList<>();

        ActProcessNodeVo firstNode = new ActProcessNodeVo(); // 获取开始结点之后的第一个 节点
        for (FlowElement flowElement : flowElements) {

            if (flowElement instanceof StartEvent) { // 开始事件
                System.out.println("flowElement.getName() = " + flowElement.getName());
                System.out.println("flowElement.getId() = " + flowElement.getId());
                System.out.println("flowElement.getDocumentation() = " + flowElement.getDocumentation());
                List<SequenceFlow> outgoingFlows = ((StartEvent) flowElement).getOutgoingFlows();
                for (SequenceFlow outgoingFlow : outgoingFlows) {
                    FlowElement targetFlowElement = outgoingFlow.getTargetFlowElement();
                    if (targetFlowElement instanceof UserTask) { // 用户事件
                        System.out.println("targetFlowElement.getName() = " + targetFlowElement.getName());
                        System.out.println("targetFlowElement.getId() = " + targetFlowElement.getId());
                        System.out.println("targetFlowElement.getDocumentation() = " + targetFlowElement.getDocumentation());
                        firstNode.setNodeId(targetFlowElement.getId());
                        firstNode.setNodeName(targetFlowElement.getName());
                        firstNode.setProcessDefinitionId(processInstance.getProcessDefinitionId());
                        firstNode.setIndex(0);
                    }
                }
            }
        }
    }

    @Autowired
    private SysUserMapper02 sysUserMapper02;
    @Autowired
    private ActBusinessStatusMapper actBusinessStatusMapper;

    @Test
    public void test04() {

        String processInstanceId = actBusinessStatusMapper.selectByAssignee("10");

        //查询任务办理记录
        List<HistoricTaskInstance> list = historyService.createHistoricTaskInstanceQuery().processInstanceId(processInstanceId)
                .orderByHistoricTaskInstanceEndTime().desc().list();
        // 根据结束事件排序
        list.stream().sorted(Comparator.comparing(HistoricTaskInstance::getEndTime, Comparator.nullsFirst(Date::compareTo))).collect(Collectors.toList());
        // 创建历史流程视图集合
        List<ActHistoryInfoVo> actHistoryInfoVoList = new ArrayList<>();

        for (HistoricTaskInstance historicTaskInstance : list) {
            ActHistoryInfoVo actHistoryInfoVo = new ActHistoryInfoVo();
            // 复制属性
            BeanUtils.copyProperties(historicTaskInstance, actHistoryInfoVo);

            actHistoryInfoVo.setStatus(actHistoryInfoVo.getEndTime() == null ? "待审批" : "审批通过");
            if (actHistoryInfoVo.getEndTime() != null) {
                actHistoryInfoVo.setStatus(historicTaskInstance.getDeleteReason() != null ? "驳回" : "审批通过");
            }

            List<Comment> taskComments = taskService.getTaskComments(historicTaskInstance.getId());
            if (CollectionUtil.isNotEmpty(taskComments)) {
                actHistoryInfoVo.setCommentId(taskComments.get(0).getId());
                String message = taskComments.stream()
                        .map(Comment::getFullMessage).collect(Collectors.joining("。"));
                if (StringUtils.isNotBlank(message)) {
                    actHistoryInfoVo.setComment(message);
                }
            }
            actHistoryInfoVoList.add(actHistoryInfoVo);
        }
        //翻译人员名称
        if (actHistoryInfoVoList.size() > 0) {
            List<Long> assigneeList = actHistoryInfoVoList.stream().map(e -> Long.valueOf(e.getAssignee())).collect(Collectors.toList());
            if (assigneeList.size() > 0) {
                List<SysUser> sysUsers = sysUserMapper02.findByIds(assigneeList);
                actHistoryInfoVoList.forEach(e -> {
                    sysUsers.stream().filter(u -> u.getUserId().toString().equals(e.getAssignee())).findFirst().ifPresent(u -> {
                        e.setNickName(u.getNickName());
                    });
                });
            }
        }
        List<ActHistoryInfoVo> collect = new ArrayList<>();
        //待办理
        List<ActHistoryInfoVo> waitingTask = actHistoryInfoVoList.stream().filter(e -> e.getEndTime() == null).collect(Collectors.toList());
        //已办理
        List<ActHistoryInfoVo> finishTask = actHistoryInfoVoList.stream().filter(e -> e.getEndTime() != null).collect(Collectors.toList());
        collect.addAll(waitingTask);
        collect.addAll(finishTask);

        for (ActHistoryInfoVo actHistoryInfoVo : collect) {
            System.out.println("actHistoryInfoVo = " + actHistoryInfoVo);
        }

    }

    @Test
    public void test05() {
        List<Long> assigneeList = new ArrayList<>();
        assigneeList.add(5L);
        assigneeList.add(6L);
        assigneeList.add(7L);
        List<SysUser> byIds = sysUserMapper02.findByIds(assigneeList);
        for (SysUser byId : byIds) {
            System.out.println("byId = " + byId);
        }
    }


}
