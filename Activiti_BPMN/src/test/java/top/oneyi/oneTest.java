package top.oneyi;

import org.activiti.engine.*;
import org.activiti.engine.history.HistoricProcessInstance;
import org.activiti.engine.impl.persistence.entity.TaskEntity;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.activiti.engine.task.TaskQuery;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import top.oneyi.mapper.ActRuTaskMapper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RunWith(SpringRunner.class)//当前类为 springBoot 的测试类
@SpringBootTest(classes = ActivtiSpringBootApplication.class)//加载 SpringBoot 启动类
public class oneTest {

    @Autowired
    private RuntimeService runtimeService;
    @Autowired
    private RepositoryService repositoryService;
    @Autowired
    private TaskService taskService;
    // 业务id
    private final static String businessId = "34354543245";
    // 流程实例key
    private final static String key="financial";
    @Autowired
    private ActRuTaskMapper actRuTaskMapper;

    /**
     * 部署流程
     */
    @Test
    public void test01() {
        Deployment deployment = repositoryService.createDeployment()
                .addClasspathResource("diagram/guaranty.bpmn") // 添加bpmn资源
                .addClasspathResource("diagram/guaranty.png")  // 添加png资源
                .name("担保物审批流程-部署")
                .deploy();
        System.out.println("流程部署id：" + deployment.getId());
        System.out.println("流程部署名称：" + deployment.getName());
    }
    /**
     * 创建流程实例
     */
    @Test
    public void add() {
        Map<String, Object> map = new HashMap<>();
        map.put("khjl", "1");
        map.put("bmjl", "2");
        map.put("zxfzr", "3");
        map.put("zjl", "4");
        map.put("businessId","2");
        // 开启实例
        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey(key, "2", map);
        System.out.println("processInstance.getBusinessKey() = " + processInstance.getBusinessKey());
        System.out.println("processInstance.getName() = " + processInstance.getName());
        System.out.println("processInstance.getDescription() = " + processInstance.getDescription());
        System.out.println("processInstance.getId() = " + processInstance.getId());
        System.out.println("processInstance.getStartTime() = " + processInstance.getStartTime());
        // 遍历variables值 对应表 act_ru_variable
        Map<String, Object> variables = runtimeService.getVariables(processInstance.getId());
        for (Map.Entry<String, Object> stringObjectEntry : variables.entrySet()) {
            System.out.println("键 " + stringObjectEntry.getKey());
            System.out.println("值 " + stringObjectEntry.getValue());
            System.out.println("======================================");
        }
    }

    /**
     * 查询任务
     */
    @Test
    public void queryTask(){
        List<Task> list = taskService.createTaskQuery().list();
        for (Task task : list) {
            System.out.println("task = " + task);
            System.out.println("操作人的权限名称为" + task.getAssignee());
        }
    }

    /**
     * 根据当前角色查询任务
     */
    @Test
    public void queryByUserTask(){
        // 假设角色为 khjl 客户经理
        String userRole ="khjl";
        List<String> list = actRuTaskMapper.selectByassignee(userRole);
        for (String task : list) {
            System.out.println("task = " + task);
        }

    }

    /**
     * 完成指定任务业务id
     */
    @Test
    public void doTask(){
        Task task = taskService.createTaskQuery()
                .processInstanceBusinessKey("1")
                .processDefinitionKey(key)
                .singleResult();
        taskService.complete(task.getId());
    }
}
