package top.oneyi.reject;

import org.activiti.engine.*;
import org.activiti.engine.impl.identity.Authentication;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import top.oneyi.pojo.ActBusinessStatus;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootTest
public class LoanManagementTest {
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

    private static final String KEY = "userFlow";
    private static final String BusinessKey = "1";
    /**
     * 启动流程
     */
    @Test
    public void startFlow() {
        Map<String, Object> map = new HashMap<>();
        map.put("one", "1");
        map.put("two", "11");
        map.put("stree", "12");
        map.put("four", "2");
        map.put("five", "21");
        map.put("six", "22");
        // 指定发起人
        Authentication.setAuthenticatedUserId("0");
        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey(KEY, BusinessKey, map);
    }

    /**
     * 查看可以拾取的任务
     */
    @Test
    public void queryTasks(){
        List<Task> tasks = taskService.createTaskQuery()
                .processInstanceBusinessKey(BusinessKey)
                .processDefinitionKey(KEY)
//                .taskCandidateUser("1")
                .list();
        for (Task task : tasks) {
            System.out.println("task.getAssignee() = " + task.getAssignee());
            System.out.println("task.getOwner() = " + task.getOwner());
            System.out.println("task.getClaimTime() = " + task.getClaimTime());
            System.out.println("task.getDueDate() = " + task.getDueDate());
        }

    }

    /**
     * 任务的交接
     */
    @Test
    public void queryTask(){
    Task task = taskService.createTaskQuery()
                .processInstanceBusinessKey(BusinessKey)
                .processDefinitionKey(KEY)
                .taskAssignee("1")
                .singleResult();
        if(task != null){
            // 任务的交接
            taskService.setAssignee(task.getId(),"王五");
            System.out.println("任务交接给了王五");
        }

    }
    /**
     * 任务的归还
     */
    @Test
    public void outTask(){
        Task task = taskService.createTaskQuery()
                .processInstanceBusinessKey(BusinessKey)
                .processDefinitionKey(KEY)
                .taskAssignee("1")
                .singleResult();
        if(task != null){
            // 拾取对应的任务
            taskService.unclaim(task.getId());
            System.out.println("归还拾取成功");
        }

    }
    /**
     * 任务的委托
     */
    @Test
    public void delegationTask(){
        Task task = taskService.createTaskQuery()
                .processInstanceBusinessKey(BusinessKey)
                .processDefinitionKey(KEY)
                .taskAssignee("我是委托人")
                .singleResult();
        if(task != null){
            // 委托对应的任务
            taskService.delegateTask(task.getId(),"1");
            System.out.println("委托成功");
        }

    }
}
