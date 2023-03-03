package top.oneyi;

import org.activiti.engine.TaskService;
import org.activiti.engine.task.Task;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import top.oneyi.demo.ActivitiUtil;

import java.util.HashMap;
import java.util.Map;

@RunWith(SpringRunner.class)//当前类为 springBoot 的测试类
@SpringBootTest(classes = ActivtiSpringBootApplication.class)//加载 SpringBoot 启动类
public class guarantyFlowTest {

    @Autowired
    private ActivitiUtil activitiUtil;

    @Autowired
    private TaskService taskService;

    @Test
    public void create(){
        Map<String, Object> map = new HashMap<>();
        map.put("common","5");
        map.put("khjl", "6");
        map.put("bmjl", "7");
        map.put("zxfzr", "8");
        map.put("zjl", "9");
        activitiUtil.createProcessInstance("key01","financial",map);
    }
    @Test
    public void doTask(){
        Task task = activitiUtil.findTask("key01", "financial");
        taskService.setAssignee(task.getId(),"替换掉同意人");
        taskService.setOwner(task.getId(),"我是审批人或者拥有人");
        taskService.addComment(task.getId(),task.getProcessInstanceId(),task.getOwner()+"-: 同意","通过意见");
        taskService.complete(task.getId());
    }

    @Test
    public void selectComment(){

    }
}
