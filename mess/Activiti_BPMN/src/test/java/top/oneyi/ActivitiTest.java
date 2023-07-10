package top.oneyi;

import org.activiti.engine.*;
import org.activiti.engine.history.HistoricProcessInstance;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.repository.ProcessDefinitionQuery;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.activiti.engine.test.ActivitiRule;
import org.apache.commons.lang3.StringUtils;
import org.assertj.core.util.Maps;
import org.junit.Rule;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import top.oneyi.demo.ActivitiUtil;


import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class ActivitiTest {

    /**
     * 单文件部署
     */
    @Test
    public void test01() {
//        1、创建ProcessEngine
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
//        2、得到RepositoryService实例
        RepositoryService repositoryService = processEngine.getRepositoryService();
//        3、使用RepositoryService进行部署
        Deployment deployment = repositoryService.createDeployment()
                .addClasspathResource("diagram/guaranty.bpmn") // 添加bpmn资源
                .addClasspathResource("diagram/guaranty.png")  // 添加png资源
                .name("测试流程1")
                .deploy();
//        4、输出部署信息
        System.out.println("流程部署id：" + deployment.getId());
        System.out.println("流程部署名称：" + deployment.getName());
    }

    /**
     * 启动流程实例
     */
    @Test
    public void testStartProcess() {
//        1、创建ProcessEngine
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
//        2、获取RunTimeService
        RuntimeService runtimeService = processEngine.getRuntimeService();
//        3、根据流程定义Id启动流程
//      runtimeService.startProcessInstanceByKey("wan");
        Map<String, Object> map = new HashMap<>();
        map.put("common", "6");
        map.put("khjl", "6");
        map.put("bmjl", "7");
        map.put("zxfzr", "8");
        map.put("zjl", "9");
        // 启动流程实例 添加业务id
        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey("financial", "1234", map);
//        输出内容
        System.out.println("流程定义id：" + processInstance.getProcessDefinitionId());
        System.out.println("流程实例id：" + processInstance.getId());
        System.out.println("当前活动Id：" + processInstance.getActivityId());
    }

    /**
     * 查询当前个人待执行的任务
     */
    @Test
    public void testFindPersonalTaskList() {
//        任务负责人
        String assignee = "zhangsan";
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
//        创建TaskService
        TaskService taskService = processEngine.getTaskService();
//        根据流程key 和 任务负责人 查询任务
        List<Task> list = taskService.createTaskQuery()
                .processDefinitionKey("wan") //流程Key
//                .taskAssignee(assignee)//只查询该任务负责人的任务
                .list();

        for (Task task : list) {
            System.out.println("流程实例id：" + task.getProcessInstanceId());
            System.out.println("任务id：" + task.getId());
            System.out.println("任务负责人：" + task.getAssignee());
            System.out.println("任务名称：" + task.getName());
        }
/// ==================================================================================

        // 根据 实例key 和特定的任务负责人 查询到任务集合   获取任务集合
        List<Task> taskList = taskService.createTaskQuery()
                .processDefinitionKey("creditFlow")
                .taskAssignee(assignee)
                .list();

//使用stream流获取到对应的 流程实例id集合
        Set<String> processInstanceIds = taskList.stream().map(Task::getProcessInstanceId).collect(Collectors.toSet());
// 流程实例集合id 查询流程实例集合
        RuntimeService runtimeService = processEngine.getRuntimeService();
        List<ProcessInstance> processInstanceList = runtimeService.createProcessInstanceQuery().processInstanceIds(processInstanceIds).list();
//再根据流程实例集合  获取业务id集合
        List<String> businessKeyList = processInstanceList.stream().map(ProcessInstance::getBusinessKey).collect(Collectors.toList());
//根据业务id集合获取业务记录集合
//        List<EnterpriseCreditInfoVo> creditInfoList = new ArrayList<>();
//        creditInfoList = baseMapper.selectVoBatchIds(businessKeyList);


    }

    // 完成任务  任务id 10005   12505  实例id   10001   12501
    @Test
    public void completTask() {
//        获取引擎
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
//        获取taskService
        TaskService taskService = processEngine.getTaskService();
        ProcessInstance processInstance = processEngine.getRuntimeService()
                .createProcessInstanceQuery()
                .processInstanceBusinessKey("1234")
                .processDefinitionKey("financial").singleResult();
//        根据流程key 和 任务的负责人 查询任务
//        返回一个任务对象
        Task task = taskService.createTaskQuery()
                .processDefinitionKey("financial") //流程Key
                .processInstanceBusinessKey("1234")
//                .taskAssignee("lisi")  //要查询的负责人
                .singleResult();
        taskService.addComment(task.getId(), processInstance.getProcessInstanceId(), task.getName() + ":审批不通过", "内容不合格  写的什么玩意");
//        taskService.complete(task.getId());
        ActivitiUtil util = new ActivitiUtil();
        // 跳到指定的节点
        util.jumpTask(processInstance, "sid-108998A1-BFC1-4B72-91E6-6D1B484E75B9", "1234");


        // 根据businessKey获得流程实例id
//        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();

//        HistoryService historyService = processEngine.getHistoryService();
//        HistoricProcessInstance historicProcessInstance = historyService.createHistoricProcessInstanceQuery().processInstanceBusinessKey("id").singleResult();
//        String processInstanceId = ObjectUtil.isNotEmpty(historicProcessInstance) ? historicProcessInstance.getId() : "";
//        System.out.println(processInstanceId);


//根据流程实例id获取任务，流程实例是唯一的，因此查询出任务也是唯一的
//        ActRuTask actRuTask = actRuTaskMapper.selectOne(new QueryWrapper<ActRuTask>().eq("PROC_INST_ID_", processInstanceId));
//        String taskId = actRuTask.getId();


//获取TaskService
//        TaskService taskService = processEngine.getTaskService();
//        taskService.complete(taskId);


    }

    /**
     * 查询流程定义
     */
    @Test
    public void queryProcessDefinition() {
        //        获取引擎
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
//        repositoryService
        RepositoryService repositoryService = processEngine.getRepositoryService();
//        得到ProcessDefinitionQuery 对象
        ProcessDefinitionQuery processDefinitionQuery = repositoryService.createProcessDefinitionQuery();
//          查询出当前所有的流程定义
//          条件：processDefinitionKey =evection
//          orderByProcessDefinitionVersion 按照版本排序
//        desc倒叙
//        list 返回集合
        List<ProcessDefinition> definitionList = processDefinitionQuery.processDefinitionKey("wan")
                .orderByProcessDefinitionVersion()
                .desc()
                .list();
//      输出流程定义信息
        for (ProcessDefinition processDefinition : definitionList) {
            System.out.println("流程定义 id=" + processDefinition.getId());
            System.out.println("流程定义 name=" + processDefinition.getName());
            System.out.println("流程定义 key=" + processDefinition.getKey());
            System.out.println("流程定义 Version=" + processDefinition.getVersion());
            System.out.println("流程部署ID =" + processDefinition.getDeploymentId());
        }

    }


    @Rule
    public ActivitiRule activitiRule = new ActivitiRule();


    @Test
    public void test02() {
        ProcessDefinition processDefinition = activitiRule.getRepositoryService().createProcessDefinitionQuery().singleResult();
        RuntimeService runtimeService = activitiRule.getRuntimeService();
        Map<String, Object> variables = new HashMap<>();
        variables.put("key1", "value1");
        ProcessInstance processInstance = runtimeService.startProcessInstanceById(processDefinition.getId(), variables);
        System.out.println("processInstance = " + processInstance);
        System.out.println("processInstance.getName() = " + processInstance.getName());
        System.out.println("processDefinition.getDeploymentId() = " + processDefinition.getDeploymentId());

    }


}

