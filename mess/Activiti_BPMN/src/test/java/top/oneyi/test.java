package top.oneyi;


import org.activiti.bpmn.model.BpmnModel;
import org.activiti.bpmn.model.FlowElement;
import org.activiti.bpmn.model.FlowNode;
import org.activiti.bpmn.model.Process;
import org.activiti.engine.*;
import org.activiti.engine.history.HistoricTaskInstance;
import org.activiti.engine.impl.RepositoryServiceImpl;
import org.activiti.engine.impl.persistence.entity.ProcessDefinitionEntity;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.apache.catalina.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import top.oneyi.demo.*;
import top.oneyi.mapper.SysUserMapper;
import top.oneyi.pojo.ActBusinessStatus;
import top.oneyi.pojo.ActResult;
import top.oneyi.pojo.SysUser;
import top.oneyi.service.ActBusinessStatusService;

import javax.annotation.Resource;
import java.util.*;
import java.util.stream.Collectors;

@RunWith(SpringRunner.class)//当前类为 springBoot 的测试类
@SpringBootTest(classes = ActivtiSpringBootApplication.class)//加载 SpringBoot 启动类
public class test {
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

    @Resource
    private top.oneyi.service.TaskService taskService01;

    @Resource
    private SysUserMapper sysUserMapper;

    @Test
    public void test01(){
        SysUser byId = sysUserMapper.findById(10L);
        byId.setNickName("大是大非地方");
        sysUserMapper.updateByPrimaryKey(byId);
    }

    @Test
    public void test() {
        List<Task> financial = taskService.createTaskQuery().
                processDefinitionKey("financial").
                processInstanceBusinessKey("4").
                list();
        for (Task task : financial) {
            taskService.complete(task.getId());
            System.out.println("task = " + task);
        }
    }

    @Test
    public void test02() {
        String taskId = "";
        //根据要跳转的任务ID获取其任务
        HistoricTaskInstance historicTaskInstance = historyService.createHistoricTaskInstanceQuery().taskId(taskId).singleResult();
        //进而获取流程实例
        ProcessInstance instance = runtimeService
                .createProcessInstanceQuery()
                .processInstanceId(historicTaskInstance.getProcessInstanceId())
                .singleResult();
        //取得流程定义
        ProcessDefinition definition = repositoryService.getProcessDefinition(historicTaskInstance.getProcessDefinitionId());
    }

    @Test
    public void test03() {
        // 获取流程部署id
        ProcessDefinition financial = repositoryService.createProcessDefinitionQuery()
                .processDefinitionKey("financial")
                .latestVersion()
                .singleResult();
        System.out.println("financial.getId() = " + financial.getId());

        //<span style="font-family: Arial, Helvetica, sans-serif;">processDefinitionId</span><span style="font-family: Arial, Helvetica, sans-serif;">    为流程定义Id，该Id可以通过多种方式获得，如通过ProcessDefinitionQuery可以查询一个ProcessDefinition对象，Task对象中也包含</span><span style="font-family: Arial, Helvetica, sans-serif;">processDefinitionId</span><span style="font-family: Arial, Helvetica, sans-serif;"> </span>
        BpmnModel model = repositoryService.getBpmnModel(financial.getId());
        if (model != null) {
            Collection<FlowElement> flowElements = model.getMainProcess().getFlowElements();
            for (FlowElement e : flowElements) {
                System.out.println("flowelement id:" + e.getId() + "  name:" + e.getName() + "   class:" + e.getClass().toString());
            }
        }
    }

    @Test
    public void select() {
        String businessKey = "1";
        String key = "financial";
        ProcessInstance processInstance = runtimeService.createProcessInstanceQuery()
                .processInstanceBusinessKey(businessKey)
                .processDefinitionKey(key).singleResult();
        // 流程实例id
        String processInstanceId = processInstance.getProcessInstanceId();
        // 流程部署id
        String processDefinitionId = processInstance.getProcessDefinitionId();
        BpmnModel model = repositoryService.getBpmnModel(processDefinitionId);

        if (model != null) {
            Collection<FlowElement> flowElements = model.getMainProcess().getFlowElements();
            for (FlowElement e : flowElements) {
                System.out.println("flowelement id:" + e.getId() + "  name:" + e.getName() + "   class:" + e.getClass().toString());
            }

        }
    }

    public ProcessInstance getProcessInstance() {
        String businessKey = "3";
        String key = "financial";
        ProcessInstance processInstance = runtimeService.createProcessInstanceQuery()
                .processInstanceBusinessKey(businessKey)
                .processDefinitionKey(key)
                .singleResult();
        // 流程实例id
        String processInstanceId = processInstance.getProcessInstanceId();
        // 流程部署id
        String processDefinitionId = processInstance.getProcessDefinitionId();
        return processInstance;
    }

    @Test
    public void test04() {
        // 获取流程实例
        ProcessInstance processInstance = this.getProcessInstance();
        //查到任务
        Task task = taskService.createTaskQuery()
                .processInstanceId(processInstance.getId())
                .processInstanceBusinessKey("1").singleResult();
        Map<String, Object> variables;

        String taskId = task.getId();
        // 取得当前任务
        HistoricTaskInstance currTask = historyService
                .createHistoricTaskInstanceQuery().taskId(taskId)
                .singleResult();
        // 取得流程实例
        ProcessInstance instance = runtimeService
                .createProcessInstanceQuery()
                .processInstanceId(currTask.getProcessInstanceId())
                .singleResult();
        if (instance == null) {
            //流程已经结束
        }
        variables = instance.getProcessVariables();
        // 取得流程定义
        ProcessDefinitionEntity definition = (ProcessDefinitionEntity) ((RepositoryServiceImpl) repositoryService)
                .getDeployedProcessDefinition(currTask
                        .getProcessDefinitionId());
        if (definition == null) {
            //流程定义未找到
        }
        BpmnModel bpmnModel = repositoryService.getBpmnModel(definition.getId());
        //获取当前活动对象
        FlowElement flowElement = bpmnModel.getFlowElement(task.getTaskDefinitionKey());


    }

    /**
     * 获取想要跳转的任务实例
     */
    @Test
    public void listTaskTest() {
        // 获取流程实例
        ProcessInstance processInstance = this.getProcessInstance();
        // 获取当前任务
        Task currentTask = taskService.createTaskQuery().processInstanceBusinessKey("3").processInstanceId(processInstance.getId()).singleResult();
        BpmnModel bpmnModel = repositoryService.getBpmnModel(currentTask.getProcessDefinitionId());
        // 获取流程定义
        Process process = bpmnModel.getMainProcess();
        String goalNode = "sid-108998A1-BFC1-4B72-91E6-6D1B484E75B9";
        // 获取目标节点定义
        FlowNode targetNode = (FlowNode) process.getFlowElement(goalNode);
        // 删除当前运行任务，同时返回执行id，该id在并发情况下也是唯一的
        String executionEntityId = managementService.executeCommand(new DeleteTaskCmd(currentTask.getId()));
        // 流程执行到来源节点
        managementService.executeCommand(new SetFLowNodeAndGoCmd(targetNode, executionEntityId));
    }

    @Autowired
    private ActivitiUtil activitiUtil;

    @Test
    public void test05() {

        ProcessInstance financial = activitiUtil.findProcessInstance("3", "financial");
        System.out.println("financial = " + financial);
        String processInstanceId = financial.getProcessInstanceId();
        System.out.println(financial);
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        HistoryService historyService = processEngine.getHistoryService();
        List<HistoricTaskInstance> list = historyService.createHistoricTaskInstanceQuery().processInstanceId(processInstanceId).list();
        for (HistoricTaskInstance historicTaskInstance : list) {
            System.out.println("historicTaskInstance.getTaskDefinitionKey() = " + historicTaskInstance.getTaskDefinitionKey());
            System.out.println("historicTaskInstance.getAssignee() = " + historicTaskInstance.getAssignee());
        }
        System.out.println("经过stream流处理过之后 去重  ");
        list = list.stream().collect(Collectors.collectingAndThen(
                Collectors.toCollection(() -> new TreeSet<>(
                        Comparator.comparing(
                                HistoricTaskInstance::getTaskDefinitionKey))), ArrayList::new));
        for (HistoricTaskInstance historicTaskInstance : list) {
            System.out.println("historicTaskInstance.getTaskDefinitionKey() = " + historicTaskInstance.getTaskDefinitionKey());
            System.out.println("historicTaskInstance.getAssignee() = " + historicTaskInstance.getAssignee());
            System.out.println("historicTaskInstance.getName() = " + historicTaskInstance.getName());

        }


    }

    @Test
    public void test06() {
        List<String> list = new ArrayList<>();
        list.add("1");
        list.add("2");
        list.add("3");
        list.add("4");
        list.add("5");
        list.forEach(s -> {
            if (s.equals("3")) {
                System.out.println("我进去了");
            } else {
                System.out.println("s = " + s);
            }

        });
    }

    @Test
    public void test07() {
        List<String> list = new ArrayList<>();
        list.add("1");
        list.add("2");
        list.add("3");
        list.add("4");
        list.add("5");
        List<String> list2 = new ArrayList<>();
        List<String> list3 = new ArrayList<>();
        List<String> list4 = null;
        Arrays.asList(list2, list3, list);
        for (String s : list4) {
            System.out.println(s);
        }

    }

    @Autowired
    private ActBusinessStatusService actBusinessStatusService;

    @Test
    public void test08() {
//        actBusinessStatusService.list(new ActBusinessStatus());

        ActResult actResult = new ActResult();
        actResult.setReject(Arrays.asList("1", "2", "3"));
        actResult.setApprove(Arrays.asList("4", "5", "6"));
        actResult.setApprovalPending(Arrays.asList("7", "8", "9"));
        Map<String, List<String>> build = actResult.build("4", "1", "3");
        System.out.println("build.get(\"1\") = " + build.get("1"));
        System.out.println("build.get(\"3\") = " + build.get("3"));
        System.out.println("build.get(\"4\") = " + build.get("4"));

        String[] strings = {"4", "1", "3"};
        for (String string : strings) {
            List<String> strings1 = build.get(string);

        }
    }

    @Test
    public void test09() {
        List<ProcessDefinition> list = repositoryService.createProcessDefinitionQuery().list();
        for (ProcessDefinition processDefinition : list) {
            System.out.println("processDefinition.getId() = " + processDefinition.getId());
            System.out.println("processDefinition.getName() = " + processDefinition.getName());
            System.out.println("processDefinition.getKey() = " + processDefinition.getKey());
            System.out.println("processDefinition.getVersion() = " + processDefinition.getVersion());
            System.out.println("processDefinition.getResourceName() = " + processDefinition.getResourceName());
        }
    }

    @Test
    public void test10() {
        SinglyLinkedList node = new SinglyLinkedList();
        node.addFirst(1);
        node.addFirst(2);
        node.addFirst(3);
        node.addFirst(4);

        for (int i = 0; i < 4; i++) {
            System.out.println(node.get(i));
        }

        SinglyLinkedListSentinel list = new SinglyLinkedListSentinel();
        list.addLast(1);
    }

    @Test
    public void test11() {
        List<String> list = new ArrayList<>();
        List<String> list2=null ;
        list.add("1");
        list.add("2");
        list.add("3");
        list.add("4");
        list.add("5");
        StringJoiner add = new StringJoiner(",");
        assert list2 != null;
        list2.forEach(add::add);
        System.out.println("add = " + add);

    }


    @Test
    public void test12(){
        ProcessInstance processInstance = runtimeService
                .createProcessInstanceQuery()
                .processInstanceId("74791a6c-bf17-11ed-a002-a036bc096aaf")
                .startedBy("5")
                .singleResult();
//        assert processInstance != null;
        System.out.println("processInstance = " + processInstance);

    }

}