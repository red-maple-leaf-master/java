package top.oneyi;

import org.activiti.bpmn.model.*;
import org.activiti.bpmn.model.Process;
import org.activiti.engine.*;
import org.activiti.engine.history.HistoricTaskInstance;
import org.activiti.engine.impl.identity.Authentication;
import org.activiti.engine.impl.util.CollectionUtil;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.runtime.ProcessInstanceQuery;
import org.activiti.engine.task.Comment;
import org.activiti.engine.task.Task;
import org.activiti.engine.task.TaskQuery;
import org.assertj.core.util.DateUtil;
import org.junit.Test;
import org.junit.platform.commons.util.StringUtils;
import org.junit.runner.RunWith;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import top.oneyi.demo.ActivitiUtil;
import top.oneyi.mapper.ActBusinessStatusMapper;
import top.oneyi.mapper.UserMapper;
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
    private static final String BussinessKey = "12";
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
                .name("oneyi")
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
        Authentication.setAuthenticatedUserId("5");
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
        ProcessInstance processInstance = this.startFlow("100", KEY);
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
        ProcessInstance processInstance = activitiUtil.findProcessInstance("100", KEY);
        if (processInstance != null) {

            Task task = activitiUtil.findTask(processInstance.getProcessInstanceId());
            taskService.addComment(task.getId(), task.getProcessInstanceId(), "通过意见");
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
        ProcessInstance processInstance = activitiUtil.findProcessInstance("11", KEY);
        List<HistoricTaskInstance> historicTaskInstances = activitiUtil.historyTasks(processInstance.getProcessInstanceId());
        for (HistoricTaskInstance historicTaskInstance : historicTaskInstances) {
            // 根据跳转指定节点名称确定
            if ("客户提交担保物".equals(historicTaskInstance.getName())) {
//                Task task = activitiUtil.findTask(processInstance.getProcessInstanceId());
//                taskService.addComment(task.getId(), task.getProcessInstanceId(),  "担保物太便宜了,不予通过");
                // taskDefinitionKey 每个节点都一样 id是不一样的
//                String taskDefinitionKey = historicTaskInstance.getTaskDefinitionKey();
                // 流程实例 跳转转任务节点，业务id
//                activitiUtil.jumpTask(processInstance, taskDefinitionKey, "11");
            }
            System.out.println("historicTaskInstance.getName() = " + historicTaskInstance.getName());
        }

    }

    @Test
    public void test02() {




        ProcessInstance processInstance = activitiUtil.findProcessInstance("100", KEY);
        List<HistoricTaskInstance> list1 = historyService.createHistoricTaskInstanceQuery().processInstanceId(processInstance.getProcessInstanceId())
                .orderByHistoricTaskInstanceEndTime().desc().list();
        // 目标节点
        HistoricTaskInstance actTaskNode = list1.get(0);



        List<Task> list = taskService.createTaskQuery().processInstanceId(processInstance.getProcessInstanceId()).list();
        for (Task task : list) {
            // 1 获取流程模型实例  BpmnModel  ProcessDefinitionId 流程部署id ==> financial:3:a6bfbf38-c14d-11ed-a5c2-a036bc096aaf
            BpmnModel bpmnModel = repositoryService.getBpmnModel(task.getProcessDefinitionId());
            // 2 当前节点信息
            FlowNode flowNode = (FlowNode) bpmnModel.getFlowElement(task.getTaskDefinitionKey());
            // 3 获取当前节点原出口连线
            List<SequenceFlow> outgoingFlows = flowNode.getOutgoingFlows();
            // 4 临时存储当前节点原出口连线
            List<SequenceFlow> oldsequenceFlows = new ArrayList<>();
            oldsequenceFlows.addAll(outgoingFlows);
            //5  把当前节点的原出口清空
            outgoingFlows.clear();
            // 6. 获取目标节点信息  也就是第一个提交申请的节点
            FlowNode targetFlowNode = (FlowNode) bpmnModel.getFlowElement(actTaskNode.getTaskDefinitionKey());
            // 7 获取目标节点的入口连线
            List<SequenceFlow> incomingFlows = targetFlowNode.getIncomingFlows();
            // 8. 存储所有目标出口
            List<SequenceFlow> targetSequenceFlow = new ArrayList<>();
            for (SequenceFlow incomingFlow : incomingFlows) {
               // 找到入口连线的源头(获取目标节点的父节点)
                FlowNode source = (FlowNode) incomingFlow.getSourceFlowElement();
                // 创建出口连线集合
                List<SequenceFlow> sequenceFlows;
                if( source instanceof ParallelGateway){
                    // 并行网关: 获取目标节点的父节点(并行网关) 的所有出口
                    sequenceFlows = source.getOutgoingFlows();
                }else{
                    // 其他类型父节点,则获取目标节点的入口连续
                    sequenceFlows = targetFlowNode.getIncomingFlows();
                }
                targetSequenceFlow.addAll(sequenceFlows);
            }
            // 9. 将当前节点的出口设置为新节点的入口连线
            flowNode.setOutgoingFlows(targetSequenceFlow);
            // 10. 完成当前任务，流程就会流向目标节点创建新目标任务
            // 当前任务，完成当前任务
            taskService.addComment(task.getId(), task.getProcessInstanceId(),"申请人撤销申请");
            taskService.setAssignee(task.getId(), "1002我是撤回申请人");
            // 完成任务，就会进行驳回到目标节点，产生目标节点的任务数据
            taskService.complete(task.getId());
            // 11. 完成驳回功能后，将当前节点的原出口方向进行恢复
            flowNode.setOutgoingFlows(oldsequenceFlows);
        }

    }

    @Test
    public void queryTest(){
        ProcessInstance processInstance = activitiUtil.findProcessInstance("100", KEY);
        String processInstId = processInstance.getProcessInstanceId();
        List<Task> newTaskList = taskService.createTaskQuery()
                .processInstanceId(processInstId).list().stream()
                .filter(e-> org.apache.commons.lang3.StringUtils.isBlank(e.getParentTaskId())).collect(Collectors.toList());
        for (Task task : newTaskList) {
            System.out.println("task.getName() = " + task.getName());
            System.out.println("task.getProcessDefinitionId() = " + task.getProcessDefinitionId());
            System.out.println("task.getAssignee() = " + task.getAssignee());
        }
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
    private UserMapper userMapper;
    @Autowired
    private ActBusinessStatusMapper actBusinessStatusMapper;


    /**
     * 查询审批历史  返回视图
     */
    @Test
    public void test04() {

        String processInstanceId = actBusinessStatusMapper.selectByAssignee("11");

        //查询任务办理记录
        List<HistoricTaskInstance> list = historyService.createHistoricTaskInstanceQuery().processInstanceId(processInstanceId)
                .orderByHistoricTaskInstanceEndTime().desc().list();
        // 根据结束事件排序
        list.stream().sorted(Comparator.comparing(HistoricTaskInstance::getEndTime, Comparator.nullsFirst(Date::compareTo))).collect(Collectors.toList());
        String taskId = "";
        if(list.size() > 0){
            HistoricTaskInstance historicTaskInstance = list.get(0);
            taskId=historicTaskInstance.getTaskDefinitionKey();
        }

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
            if(historicTaskInstance.getTaskDefinitionKey().equals(taskId)){
                actHistoryInfoVo.setStatus("已提交");
            }
            List<Comment> taskComments = taskService.getTaskComments(historicTaskInstance.getId());
            System.out.println(taskComments);
            if (CollectionUtil.isNotEmpty(taskComments)) {
                actHistoryInfoVo.setCommentId(taskComments.get(0).getId());
                String message = taskComments.stream()
                        .map(Comment::getFullMessage).collect(Collectors.joining("-"));
                if (!("").equals(message)) {
                    actHistoryInfoVo.setComment(message);
                }
            }
            actHistoryInfoVoList.add(actHistoryInfoVo);
        }
        //翻译人员名称
        if (actHistoryInfoVoList.size() > 0) {
            List<Long> assigneeList = actHistoryInfoVoList.stream().map(e -> Long.valueOf(e.getAssignee())).collect(Collectors.toList());
            if (assigneeList.size() > 0) {
                List<SysUser> sysUsers = userMapper.findByIds(assigneeList);
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
        List<SysUser> byIds = userMapper.findByIds(assigneeList);
        for (SysUser byId : byIds) {
            System.out.println("byId = " + byId);
        }
    }

    /**
     * 提交申请启动流程实例(启动实例流程)
     */
    @Test
    public void test07() {
        String bussinessKey = "16";
        // 业务key判空
        if (StringUtils.isBlank(bussinessKey)) {
            // 抛出异常 或者返回错误代码给前端
        }
        // 判断该业务是否已经开启流程实例
        ProcessInstance processInstance = activitiUtil.findProcessInstance(bussinessKey, KEY);
        if (processInstance != null) {
            // 返回该业务 key 已经绑定了流程实例
        }
        //在流程实例中设置必要参数

        // 设置启动人
        Authentication.setAuthenticatedUserId("5");
        // 启动流程实例（提交申请）
        Map<String, Object> variables = new HashMap<>();
        variables.put("common", "5");
        variables.put("khjl", "6");
        variables.put("bmjl", "7");
        variables.put("zxfzr", "8");
        variables.put("zjl", "9");
        ProcessInstance pi = runtimeService.startProcessInstanceByKey(KEY, bussinessKey, variables);
        // 将流程定义名称 作为 流程实例名称
        // 申请人执行流程
        List<Task> taskList = taskService.createTaskQuery().processInstanceId(pi.getId()).list();
        if (taskList.size() > 1) {
            System.out.println("抛出异常,检查第一个环节是否为申请人");
        }
        // 设置流程定义名称
        runtimeService.setProcessInstanceName(pi.getProcessInstanceId(), pi.getProcessDefinitionName());
        // 设置流程发起人
        taskService.setAssignee(taskList.get(0).getId(), "5");
        taskService.setVariable(taskList.get(0).getId(), "processInstanceId", pi.getProcessInstanceId());
        // 插入业务状态
        ActBusinessStatus actBusinessStatus = new ActBusinessStatus();
        actBusinessStatus.setId(bussinessKey);
        actBusinessStatus.setBusinessKey(bussinessKey);
        actBusinessStatus.setProcessInstanceId(pi.getProcessInstanceId());
        actBusinessStatus.setCreateTime(new Date());
        actBusinessStatus.setUpdateTime(new Date());
        actBusinessStatus.setCreateBy("管理员");
        actBusinessStatus.setUpdateBy("管理员");
        businessStatusMapper.insert(actBusinessStatus);

    }

    /**
     * 查询正在运行的流程实例
     */
    @Test
    public void test06() {

        // 任务发起人
        String person = "5";
        // 流程名称
//        String name="流程定义测试";
        String name = "担保物审批";
        // 创建查询实例对象
        ProcessInstanceQuery query = runtimeService.createProcessInstanceQuery();
//        query.processInstanceNameLikeIgnoreCase(name);
        query.startedBy("5");
        // 分页查
        List<ProcessInstance> processInstances = query.list();
        for (ProcessInstance processInstance : processInstances) {
            System.out.println("processInstance.getProcessInstanceId() = " + processInstance.getProcessInstanceId());
            System.out.println("processInstance.getName() = " + processInstance.getProcessDefinitionName());
            System.out.println("processInstance.getLocalizedName() = " + processInstance.getLocalizedName());
        }
    }


    @Test
    public void test08() {
        String processInstanceId = "1a038d8e-bf2c-11ed-b732-a036bc096aaf";
        List<HistoricTaskInstance> list = historyService.createHistoricTaskInstanceQuery()
                .processInstanceId(processInstanceId)
                .orderByTaskCreateTime()
                .asc()
                .list();
        for (HistoricTaskInstance historicTaskInstance : list) {
            System.out.println("任务名称:" + historicTaskInstance.getName());
            System.out.println("开始事件 : " + DateUtil.formatAsDatetime(historicTaskInstance.getStartTime()));
        }
        Task task = activitiUtil.findTask(processInstanceId);
//        taskService.complete(task.getId());

        list = list.stream().collect(Collectors.collectingAndThen(
                Collectors.toCollection(() -> new TreeSet<>(
                        Comparator.comparing(HistoricTaskInstance::getProcessInstanceId))), ArrayList::new));
    }


    @Test
    public void test09() throws InterruptedException {
        List<SysUser> users = new ArrayList<>();
        SysUser sysUser = new SysUser();
        sysUser.setNickName("占山");
        Thread.sleep(100);
        sysUser.setCreateTime(new Date());
        users.add(sysUser);
        sysUser = new SysUser();
        sysUser.setNickName("lisi");
        Thread.sleep(100);
        sysUser.setCreateTime(new Date());
        users.add(sysUser);
        sysUser = new SysUser();
        sysUser.setNickName("温热 ");
        Thread.sleep(100);
        sysUser.setCreateTime(new Date());
        users.add(sysUser);
        sysUser = new SysUser();
        sysUser.setNickName("的");
        Thread.sleep(100);
        sysUser.setCreateTime(new Date());
        users.add(sysUser);
        sysUser = new SysUser();
        sysUser.setNickName("占山");
        Thread.sleep(100);
        sysUser.setCreateTime(new Date());
        users.add(sysUser);
        sysUser = new SysUser();
        sysUser.setNickName("苏菲");
        Thread.sleep(100);
        sysUser.setCreateTime(new Date());
        users.add(sysUser);
        sysUser = new SysUser();
        sysUser.setNickName("占山");
        Thread.sleep(100);
        sysUser.setCreateTime(new Date());
        users.add(sysUser);
        for (SysUser user : users) {
            System.out.println("user.getNickName() = " + user.getNickName());
            System.out.println("user.getCreateTime() = " + user.getCreateTime());
        }
        System.out.println("=====================================");
        users = users.stream().collect(Collectors.collectingAndThen(
                Collectors.toCollection(() -> new TreeSet<>(
                        Comparator.comparing(SysUser::getNickName)
                )), ArrayList::new));
        for (SysUser user : users) {
            System.out.println("user.getNickName() = " + user.getNickName());
            System.out.println("user.getCreateTime() = " + user.getCreateTime());
        }
    }

}
