package top.oneyi;

import org.activiti.engine.*;
import org.activiti.engine.history.HistoricTaskInstance;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;
import top.oneyi.demo.ActivitiStartInstance;
import top.oneyi.demo.ActivitiUtil;
import top.oneyi.mapper.ActBusinessStatusMapper;
import top.oneyi.pojo.ActBusinessStatus;

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

    private static final String KEY ="financial";
    private static final String BussinessKey = "5";
    @Autowired
    private ActBusinessStatusMapper businessStatusMapper;

    @Autowired
    private ActivitiUtil activitiUtil;

    /**
     * 部署流程
     */
    @Test
    public void deploy(){
        Deployment deployment = repositoryService.createDeployment()
                .addClasspathResource("diagram/guaranty.bpmn")
                .addClasspathResource("diagram/guaranty.png")
                .name( "流程定义测试" )
                .deploy();
    }
    /**
     * 启动流程
     */public ProcessInstance startFlow(String businessKey,String key){
        Map<String,Object> map = new HashMap<>();
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
    public void start(){
        // id 为1 的业务
        ProcessInstance processInstance = this.startFlow(BussinessKey, KEY);
//        ProcessInstance processInstance = this.startFlow("2", KEY);
//        ProcessInstance processInstance = this.startFlow("3", KEY);
//        ProcessInstance processInstance = this.startFlow("4", KEY);
    }

    /**
     * 根据审批人查找任务
     */
    @Test
    public void findByassign(){
        List<HistoricTaskInstance> list = historyService.createHistoricTaskInstanceQuery()
                .processDefinitionKey(KEY)
                .orderByHistoricTaskInstanceStartTime()
                .desc()
                .taskAssignee("7")
                .list();
        list = list.stream().collect(Collectors.collectingAndThen(
                Collectors.toCollection(() -> new TreeSet<>(
                        Comparator.comparing(HistoricTaskInstance::getProcessInstanceId))), ArrayList::new));
        Map<String,String> map = new HashMap<>();
        for (HistoricTaskInstance historicTaskInstance : list) {
            String processInstanceId = historicTaskInstance.getProcessInstanceId();
           ActBusinessStatus actBusinessStatus =  businessStatusMapper.selectByProcessInstanceId(processInstanceId);
            if(historicTaskInstance.getDeleteReason() != null && historicTaskInstance.getEndTime() != null){
                map.put("业务key",actBusinessStatus.getBusinessKey());
                map.put("操作人",historicTaskInstance.getAssignee());
                map.put("操作状态 ","已驳回");
            }else if(historicTaskInstance.getDeleteReason() == null && historicTaskInstance.getEndTime() != null){
                map.put("业务key",actBusinessStatus.getBusinessKey());
                map.put("操作人",historicTaskInstance.getAssignee());
                map.put("操作状态 ","已通过");
            }else{
                map.put("业务key",actBusinessStatus.getBusinessKey());
                map.put("操作人",historicTaskInstance.getAssignee());
                map.put("操作状态 ","待审批");
            }
            System.out.println(map);

        }

    }

    /**
     *  根据业务key找到任务进行到哪一步了
     */
    @Test
    public void findByBussiness(){
        Task task = activitiUtil.findTask("4", KEY);
        System.out.println("业务key:" + BussinessKey);
        System.out.println("操作状态: 待审批");
        System.out.println("当前操作人:" + task.getAssignee());
    }
    /**
     * 完成任务
     */
    @Test
    public void doTask() {
        ProcessInstance processInstance = activitiUtil.findProcessInstance(BussinessKey, KEY);
        if(processInstance != null){
            Task task = activitiUtil.findTask(processInstance.getProcessInstanceId());
            taskService.addComment(task.getId(), task.getProcessInstanceId(), task.getName() + "-: 同意", "通过意见");
            taskService.complete(task.getId());
        }else{
            System.out.println("任务已终结");
        }

    }

    /**
     * 跳过任务
     */
    @Test
    public void jumpTest() {
        ProcessInstance processInstance = activitiUtil.findProcessInstance(BussinessKey, KEY);
        List<HistoricTaskInstance> historicTaskInstances = activitiUtil.historyTasks(processInstance.getProcessInstanceId());
        for (HistoricTaskInstance historicTaskInstance : historicTaskInstances) {
            // 根据跳转指定节点名称确定
            if ("客户提交担保物".equals(historicTaskInstance.getName())) {
                // taskDefinitionKey 每个节点都一样 id是不一样的
                String taskDefinitionKey = historicTaskInstance.getTaskDefinitionKey();
                // 流程实例 跳转转任务节点，业务id
                activitiUtil.jumpTask(processInstance, taskDefinitionKey,BussinessKey);
            }
        }

    }






}
