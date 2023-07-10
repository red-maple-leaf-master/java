package top.oneyi;


import org.activiti.engine.*;

import org.activiti.engine.history.HistoricProcessInstance;

import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.repository.ProcessDefinitionQuery;
import org.activiti.engine.runtime.Execution;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.runtime.ProcessInstanceQuery;
import org.activiti.engine.task.*;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.annotation.AliasFor;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RunWith(SpringRunner.class)//当前类为 springBoot 的测试类
@SpringBootTest(classes = ActivtiSpringBootApplication.class)//加载 SpringBoot 启动类
public class guarantyTest {

    @Autowired
    private RuntimeService runtimeService;
    @Autowired
    private RepositoryService repositoryService;
    @Autowired
    private TaskService taskService;

    @Autowired
    private HistoryService historyService;

    /**
     * 单文件部署
     */
    @Test
    public void test01() {
/*        //1、创建ProcessEngine
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        // 2、得到RepositoryService实例
        RepositoryService repositoryService = processEngine.getRepositoryService();*/
        //3、使用RepositoryService进行部署
        Deployment deployment = repositoryService.createDeployment()
                .addClasspathResource("diagram/guaranty.bpmn") // 添加bpmn资源
                .addClasspathResource("diagram/guaranty.png")  // 添加png资源
                .name("担保物审批流程-部署")
                .deploy();
        //  4、输出部署信息
        System.out.println("流程部署id：" + deployment.getId());
        System.out.println("流程部署名称：" + deployment.getName());
    }

    /**
     * 当担保物提交之后,创建流程
     */
    @Test
    public void add() {
        // 担保物完成提交
        Map<String, Object> map = new HashMap<>();
        map.put("common", "0");
        map.put("khjl", "1");
        map.put("bmjl", "2");
        map.put("zxfzr", "3");
        map.put("zjl", "4");
        map.put("businessId-01", "123");
        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey("financial", "123", map);
        System.out.println("processInstance.getBusinessKey() = " + processInstance.getBusinessKey());
        System.out.println("processInstance.getName() = " + processInstance.getName());
        System.out.println("processInstance.getDescription() = " + processInstance.getDescription());
        System.out.println("processInstance.getId() = " + processInstance.getId());
        System.out.println("processInstance.getStartTime() = " + processInstance.getStartTime());

        Map<String, Object> variables = runtimeService.getVariables(processInstance.getId());
        for (Map.Entry<String, Object> stringObjectEntry : variables.entrySet()) {
            System.out.println("键 " + stringObjectEntry.getKey());
            System.out.println("值 " + stringObjectEntry.getValue());
            System.out.println("======================================");
        }
    }

    /**
     * 创建流程实例
     */
    @Test
    public void createProcessInstance() {
        ProcessDefinition processDefinition = repositoryService.createProcessDefinitionQuery().singleResult();
        Map<String, Object> map = new HashMap<>();
        map.put("khjl", "客户经理");
        map.put("bmjl", "部门经理");
        map.put("zxfzr", "中心负责人");
        map.put("zjl", "总经理");
        ProcessInstance processInstance = runtimeService.startProcessInstanceById(processDefinition.getId(), map);
        System.out.println("processInstance.getBusinessKey() = " + processInstance.getBusinessKey());
        System.out.println("processInstance.getName() = " + processInstance.getName());
        System.out.println("processInstance.getDescription() = " + processInstance.getDescription());
        System.out.println("processInstance.getId() = " + processInstance.getId());
        System.out.println("processInstance.getStartTime() = " + processInstance.getStartTime());
    }

    /**
     * 完成每一个流程实例的当前任务
     */
    @Test
    public void finishTask() {
        String id = "123";
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        HistoryService historyService = processEngine.getHistoryService();
        HistoricProcessInstance historicProcessInstance = historyService
                .createHistoricProcessInstanceQuery()
                .processInstanceBusinessKey(id)
                .singleResult();
        String processInstanceId = historicProcessInstance.getId();
        TaskQuery taskQuery = taskService.createTaskQuery();
        Task task = taskQuery.processInstanceBusinessKey(id) // 业务id
                .processDefinitionKey("financial") // 流程实例key
                .processInstanceId(processInstanceId) // 流程实例id
                .singleResult();

        String taskId = task.getId();
        String processInstanceId1 = task.getProcessInstanceId();
        System.out.println(" 任务id :" + taskId);
        System.out.println(" 业务流程ID　" + processInstanceId1);
        Map<String, Object> variables = taskService.getVariables(task.getId());
        System.out.println("variables = " + variables);
        taskService.addComment(taskId, processInstanceId1, task.getName() + "-通过", "给予通过");
        taskService.complete(task.getId());

//        taskService.setOwner(task.getId(),"中心负责人");
    }

    /**
     * 获取历史审批信息
     */
    @Test
    public void selectHistory() {
        String id = "123";
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        RuntimeService runtimeService = processEngine.getRuntimeService();
        ProcessInstance financial = runtimeService.createProcessInstanceQuery().processDefinitionKey("financial").processInstanceBusinessKey(id).singleResult();
        String processInstanceId = financial.getProcessInstanceId();
        List<Comment> comments = taskService.getProcessInstanceComments(processInstanceId);//参数为是流程实例ID
        for (Comment comment : comments) {
            System.out.println("comment.getFullMessage() = " + comment.getFullMessage());
            System.out.println("comment.getType() = " + comment.getType());
            System.out.println("comment.getTaskId() = " + comment.getTaskId());
            System.out.println("comment.getUserId() = " + comment.getUserId());
        }
    }


    @Test
    public void test() {
// 开始流程
/*        ProcessInstance pi1 = runtimeService.startProcessInstanceByKey("financial", "businessKey1");
        ProcessInstance pi2 = runtimeService.startProcessInstanceByKey("financial", "businessKey2");*/
        String pId2 = "eef0c8e6-b8f1-11ed-b5a2-a036bc096aaf";
        String pId1 = "eeeb71b0-b8f1-11ed-b5a2-a036bc096aaf";
        // 查询执行流
        Execution exe1 = runtimeService.createExecutionQuery()
                .processInstanceId(pId1).singleResult();
        Execution exe2 = runtimeService.createExecutionQuery()
                .processInstanceId(pId2).singleResult();
        System.out.println("exe1 = " + exe1);
        // 完成第一条流程
        runtimeService.signalEventReceived(exe2.getId());
        exe1 = runtimeService.createExecutionQuery()
                .processInstanceId(pId1).singleResult();
//        runtimeService.signalEventReceived(exe1.getId());
        System.out.println("exe1 = " + exe1);

    }

    /**
     * 查询所有的流程实例上的任务
     */
    @Test
    public void task() {
        TaskQuery taskQuery = taskService.createTaskQuery();
        List<Task> list = taskQuery.processDefinitionKey("financial").list();
        for (Task task : list) {
            System.out.println("task.getId() = " + task.getId());
            System.out.println("task.getAssignee() = " + task.getAssignee());
            System.out.println("task.getProcessInstanceId() = " + task.getProcessInstanceId());
            System.out.println("task.getProcessDefinitionId() = " + task.getProcessDefinitionId());
            System.out.println("task.getProcessVariables() = " + task.getProcessVariables());
            System.out.println("task.getTaskLocalVariables() = " + task.getTaskLocalVariables());
            System.out.println("task.getTaskDefinitionKey() = " + task.getTaskDefinitionKey());
            System.out.println("============================================================");
        }
    }

    /**
     * 挂起实例 激活实例
     */
    @Test
    public void processList() {
        String processInstanceId = "cb8ccef6-b8a9-11ed-af11-a036bc096aaf";
        // 挂起流程实例
        runtimeService.suspendProcessInstanceById(processInstanceId);
        //验证是否挂起   suspended挂起为 true  激活为false
        ProcessInstance processInstance = runtimeService
                .createProcessInstanceQuery()
                .processInstanceId(processInstanceId)
                .singleResult();
        boolean suspended = processInstance.isSuspended();
        //激活流程实例
        runtimeService.activateProcessInstanceById(processInstanceId);
    }

    /**
     * 流程实例查询对象 的创建。
     */
    @Test
    public void qrocessInstanceQuery() {
        String processDefinitionKey = "financial";//流程定义key
        //创建 流程实例查询对象
        ProcessInstanceQuery processInstanceQuery = runtimeService.createProcessInstanceQuery();
        //查询出多条记录
        List<ProcessInstance> processInstanceList = processInstanceQuery
                .processDefinitionKey(processDefinitionKey)//根据流程定义的key来查询
                //.processDefinitionVersion(1)//根据流程定义的版本号查询
                //.processDefinitionId("activit_key:4:1") //根据流程定义的id查询
                .orderByProcessDefinitionKey() //按照流程定义key的排序
                .desc() //降序
                .list();
        for (ProcessInstance processInstance : processInstanceList) {
            System.out.println("流程实例ID： " + processInstance.getId());
            System.out.println("正在活动的节点ID： " + processInstance.getActivityId());
            System.out.println("流程定义的ID： " + processInstance.getProcessDefinitionId());
            System.out.println("processInstance.getName() = " + processInstance.getName());
            System.out.println("流程定义名称 " + processInstance.getProcessDefinitionName());
            System.out.println("流程定义key" + processInstance.getProcessDefinitionKey());

        }


        //如果能确定数据库查询的结果只有一条记录，可以采用 singleResult
       /* ProcessInstance processInstance = processInstanceQuery
                .processDefinitionKey(processDefinitionKey)//根据流程定义的key来查询
                .singleResult();
        System.out.println("流程实例ID： " + processInstance.getId());
        System.out.println("正在活动的节点ID： " + processInstance.getActivityId());
        System.out.println("流程定义的ID： " + processInstance.getProcessDefinitionId());*/

        //查询激活的流程实例
        List<ProcessInstance> activateList = runtimeService.createProcessInstanceQuery().processDefinitionKey(processDefinitionKey).active().list();
        Assert.assertTrue(activateList.size() > 0);

        //相反 查询挂起的流程则是
        List<ProcessInstance> suspendList = runtimeService.createProcessInstanceQuery().processDefinitionKey(processDefinitionKey).suspended().list();
        Assert.assertTrue(suspendList.size() == 0);

        //根据变量来查询
        // 根据title='启动流程',以及processDefinitionKey来作为查询条件进行查询
        List<ProcessInstance> varList = runtimeService.createProcessInstanceQuery().variableValueEquals("担保物审批流程 部署", "financial").list();
        Assert.assertTrue(varList.size() > 0);

    }

    /**
     * 执行流（流程对象）的查询
     * 执行流的查询
     * RuntimeService中有createExecutionQuery方法可以得到一个ExecutionQuery对象，该对象就可以根据执行流的相关数据查询执行流
     */
    @Test
    public void executionQueryTest() {
        String processDefinitionKey = "financial";
        List<Execution> executionList = runtimeService.createExecutionQuery().processDefinitionKey(processDefinitionKey).list();
        for (Execution execution : executionList) {
            System.out.println("execution.getActivityId() = " + execution.getActivityId());
            System.out.println("execution.getId() = " + execution.getId());
            System.out.println("execution.getDescription() = " + execution.getDescription());
            System.out.println("execution.getProcessInstanceId() = " + execution.getProcessInstanceId());
            System.out.println("execution.getParentId() = " + execution.getParentId());
            System.out.println("execution.getParentProcessInstanceId() = " + execution.getParentProcessInstanceId());
            System.out.println("==================================================");
        }
        Assert.assertTrue(executionList.size() > 0);
    }

    /**
     * 流程实例的删除
     */
    @Test
    public void deleteProcessInstanceTest() {
        String processDefinitionKey = "financial";
        List<ProcessInstance> list = runtimeService.createProcessInstanceQuery()
                .processDefinitionKey(processDefinitionKey)
                .list();
        if (list.size() > 1) {
            System.out.println("流程实例的长度为:" + list.size());
            for (ProcessInstance processInstance : list) {
                String processInstanceId = processInstance.getProcessInstanceId();
                runtimeService.deleteProcessInstance(processInstanceId, "删除测试");
            }
        } else {
            System.out.println("流程实例的长度为:" + list.size());
            ProcessInstance processInstance = runtimeService.createProcessInstanceQuery()
                    .processDefinitionKey(processDefinitionKey).singleResult();
            String processInstanceId = processInstance.getProcessInstanceId();
            runtimeService.deleteProcessInstance(processInstanceId, "删除测试");
        }


    }

    /**
     * 流程实例的状态查询（就是查询流程正在执行，还是已经结束）
     * <p>
     * 注：在流程执行的过程中，创建的流程实例ID在整个流程执行过程中都不会变化，当流程结束后，流程实例会在正在执行的流程对象表中删除act_ru_execution
     */
    @Test
    public void queryProcessInstanceState() {
        String processDefinitionKey = "financial";
        List<ProcessInstance> list = runtimeService.createProcessInstanceQuery()
                .processDefinitionKey(processDefinitionKey).list();
        for (ProcessInstance processInstance : list) {
            if (processInstance != null) {
                System.out.println("当前流程处在：" + processInstance.getActivityId());
            } else {
                System.out.println("当前流程已结束");
            }
            System.out.println("================================================");
        }

    }

    @Test
    public void test03() {
        String key = "financial";
        String id = "";
        ProcessDefinitionQuery query = repositoryService.createProcessDefinitionQuery().processDefinitionKey(key);

        List<ProcessDefinition> definitionList = query.list();
        for (ProcessDefinition processDefinition : definitionList) {
            if (!processDefinition.getId().equals(id)) {
                Deployment deployment = repositoryService.createDeploymentQuery().deploymentId(processDefinition.getDeploymentId()).singleResult();

            }
        }


    }
}
