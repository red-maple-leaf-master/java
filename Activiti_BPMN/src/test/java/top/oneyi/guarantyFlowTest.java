package top.oneyi;

import org.activiti.engine.HistoryService;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.TaskService;
import org.activiti.engine.history.HistoricProcessInstance;
import org.activiti.engine.history.HistoricProcessInstanceQuery;
import org.activiti.engine.history.HistoricTaskInstance;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import top.oneyi.demo.ActivitiUtil;
import top.oneyi.mapper.ActRuTaskMapper;
import top.oneyi.pojo.Entry;

import javax.annotation.Resource;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@RunWith(SpringRunner.class)//当前类为 springBoot 的测试类
@SpringBootTest(classes = ActivtiSpringBootApplication.class)//加载 SpringBoot 启动类
public class guarantyFlowTest {

    @Autowired
    private ActivitiUtil activitiUtil;

    @Autowired
    private TaskService taskService;

    @Resource
    private ActRuTaskMapper actRuTaskMapper;

    @Test
    public void test() {
        List<Map<String, String>> list = actRuTaskMapper.list();
        Entry<String, Object> map = new Entry<>();
        map.setKey("list");
        map.setValue(list);
        System.out.println(map);
    }


    /**
     * 创建流程实例
     */
    @Test
    public void create() throws Exception {
        Map<String, Object> map = new HashMap<>();
        map.put("common", "1");
        map.put("khjl", "2");
        map.put("bmjl", "3");
        map.put("zxfzr", "4");
        map.put("zjl", "5");
        activitiUtil.createProcessInstance("businessKey-004", "financial", map);
    }

    /**
     * 完成任务
     */
    @Test
    public void doTask() {
        Task task = activitiUtil.findTask("5d4e9230-bc96-11ed-b4ef-a036bc096aaf");
        taskService.addComment(task.getId(), task.getProcessInstanceId(), task.getName() + "-: 同意", "通过意见");
        taskService.complete(task.getId());
    }

    /**
     * 跳过任务
     */
    @Test
    public void jumpTest() {
        String taskDefKey = "sid-108998A1-BFC1-4B72-91E6-6D1B484E75B9";
        ProcessInstance processInstance = activitiUtil.findProcessInstance("businessKey-002", "financial");
        activitiUtil.jumpTask(processInstance, taskDefKey, "businessKey-001");
    }

    /**
     * 根据负责人id 和业务key 查找指定任务
     */
    @Test
    public void selectHistoryTask() {
        // 负责人id
        String assigne = "2";
        ProcessInstance processInstance = activitiUtil.findProcessInstance("businessKey-001", "financial");
        List<HistoricTaskInstance> list = activitiUtil.assignHistoryTasks(processInstance.getProcessInstanceId(), assigne);
        for (HistoricTaskInstance historicTaskInstance : list) {
            System.out.println("historicTaskInstance.getName() = " + historicTaskInstance.getName());
            System.out.println("historicTaskInstance.getAssignee() = " + historicTaskInstance.getAssignee());
            System.out.println("historicTaskInstance.getDeleteReason() = " + historicTaskInstance.getDeleteReason());
            System.out.println("historicTaskInstance.getEndTime() = " + historicTaskInstance.getEndTime());
            System.out.println("historicTaskInstance.getStartTime() = " + historicTaskInstance.getStartTime());
            System.out.println("================================================================================");
        }
    }

    /**
     * 该负责人需要经手和未经受的任务
     */
    @Test
    public void selectHistoryTasks() {
        // 负责人id
        String assigne = "2";
        List<HistoricTaskInstance> financial = activitiUtil.findByassigneeHistoryTasks("financial", assigne);
/*        List<String> list = Arrays.asList("businessKey-001","businessKey-002","businessKey-003","businessKey-004");
        Map<String,Object> map = new HashMap<>();
        map.put("businessKey-001",null);
        map.put("businessKey-002",null);
        map.put("businessKey-003",null);
        map.put("businessKey-004",null);*/
        // 1,获取审批通过和审批驳回的历史任务数据
        List<HistoricTaskInstance> HistoricTasks = new ArrayList<>(); // 驳回集合
        List<HistoricTaskInstance> HistoricTasksPass = new ArrayList<>(); // 审批通过集合
        System.out.println("===========================该负责人的所有历史任务start===================================");
        for (HistoricTaskInstance historicTaskInstance : financial) {
            if (historicTaskInstance.getDeleteReason() != null && historicTaskInstance.getEndTime() != null) {
                // 驳回的任务
                HistoricTasks.add(historicTaskInstance);
            }
            if (historicTaskInstance.getDeleteReason() == null && historicTaskInstance.getEndTime() != null) {
                //通过的任务
                HistoricTasksPass.add(historicTaskInstance);
            }
            System.out.println("任务名称:" + historicTaskInstance.getName());
            System.out.println("任务负责人 " + historicTaskInstance.getAssignee());
            System.out.println("删除原因 " + historicTaskInstance.getDeleteReason());
            System.out.println("任务结束时间" + historicTaskInstance.getEndTime());
            System.out.println("任务开始时间 " + historicTaskInstance.getStartTime());
            System.out.println("流程定义id" + historicTaskInstance.getProcessInstanceId());
            System.out.println("historicTask.getTaskDefinitionKey() = " + historicTaskInstance.getTaskDefinitionKey());

        }
        System.out.println("==============================该负责人的所有历史任务end===============================");

        System.out.println("==================================取得所有的审批驳回的历史任务 start===============================");
        for (HistoricTaskInstance historicTask : HistoricTasks) {
            System.out.println(historicTask);
            System.out.println("任务名称:" + historicTask.getName());
            System.out.println("任务负责人 " + historicTask.getAssignee());
            System.out.println("删除原因 " + historicTask.getDeleteReason());
            System.out.println("任务结束时间" + historicTask.getEndTime());
            System.out.println("任务开始时间 " + historicTask.getStartTime());
            System.out.println("流程定义id" + historicTask.getProcessInstanceId());
            System.out.println("historicTask.getTaskDefinitionKey() = " + historicTask.getTaskDefinitionKey());

        }
        System.out.println("====================================取得所有的审批驳回的历史任务 end===========================");
        //2,反复驳回的数据会有重复的历史数据需要去重
        HistoricTasks = HistoricTasks.stream().collect(Collectors.collectingAndThen(
                Collectors.toCollection(() -> new TreeSet<>(
                        Comparator.comparing(HistoricTaskInstance::getProcessInstanceId))), ArrayList::new));
        // 3 获得已完成的和驳回的数据
        System.out.println("==================================去重之后审批驳回的历史任务 start===============================");
        for (HistoricTaskInstance historicTask : HistoricTasks) {

            System.out.println("任务名称:" + historicTask.getName());
            System.out.println("任务负责人 " + historicTask.getAssignee());
            System.out.println("删除原因 " + historicTask.getDeleteReason());
            System.out.println("任务结束时间" + historicTask.getEndTime());
            System.out.println("任务开始时间 " + historicTask.getStartTime());
            System.out.println("流程定义id" + historicTask.getProcessInstanceId());
            System.out.println("historicTask.getTaskDefinitionKey() = " + historicTask.getTaskDefinitionKey());

        }
        System.out.println("====================================去重之后审批驳回的历史任务 end===========================");

        System.out.println("==================================审批通过的历史任务 start===============================");
        for (HistoricTaskInstance historicTasksPass : HistoricTasksPass) {

            System.out.println("任务名称:" + historicTasksPass.getName());
            System.out.println("任务负责人 " + historicTasksPass.getAssignee());
            System.out.println("删除原因 " + historicTasksPass.getDeleteReason());
            System.out.println("任务结束时间" + historicTasksPass.getEndTime());
            System.out.println("任务开始时间 " + historicTasksPass.getStartTime());
            System.out.println("流程定义id" + historicTasksPass.getProcessInstanceId());
            System.out.println("historicTask.getTaskDefinitionKey() = " + historicTasksPass.getTaskDefinitionKey());

        }
        System.out.println("====================================审批通过的历史任务 end===========================");

    }


    @Test
    public void newHistoryTask() {
        // 负责人id
        String assigne = "2";
        List<HistoricTaskInstance> financial = activitiUtil.findByassigneeHistoryTasks("financial", assigne);
        // 1,获取审批通过和审批驳回的历史任务数据
        List<HistoricTaskInstance> collect = financial.stream().filter(distinctByKey(HistoricTaskInstance::getProcessInstanceId))
                .collect(Collectors.toList());
/*        for (HistoricTaskInstance historicTaskInstance : financial) {
            System.out.println("historicTaskInstance.getAssignee() = " + historicTaskInstance.getAssignee());
            System.out.println("historicTaskInstance.getProcessInstanceId() = " + historicTaskInstance.getProcessInstanceId());
            System.out.println("historicTaskInstance.getCreateTime() = " + historicTaskInstance.getCreateTime());
            System.out.println("historicTaskInstance.getEndTime() = " + historicTaskInstance.getEndTime());
            System.out.println("======================================================");
        }*/

        for (HistoricTaskInstance historicTaskInstance : collect) {
            if (historicTaskInstance.getDeleteReason() != null && historicTaskInstance.getEndTime() != null) {
                System.out.println("===================驳回的任务=========================");
                // 驳回的任务
                System.out.println("historicTaskInstance.getAssignee() = " + historicTaskInstance.getAssignee());
                System.out.println("historicTaskInstance.getProcessInstanceId() = " + historicTaskInstance.getProcessInstanceId());
                System.out.println("historicTaskInstance.getCreateTime() = " + historicTaskInstance.getCreateTime());
                System.out.println("historicTaskInstance.getEndTime() = " + historicTaskInstance.getEndTime());

            }
            if (historicTaskInstance.getDeleteReason() == null && historicTaskInstance.getEndTime() != null) {
                System.out.println("===================通过的任务=========================");
                //通过的任务
                System.out.println("historicTaskInstance.getAssignee() = " + historicTaskInstance.getAssignee());
                System.out.println("historicTaskInstance.getProcessInstanceId() = " + historicTaskInstance.getProcessInstanceId());
                System.out.println("historicTaskInstance.getCreateTime() = " + historicTaskInstance.getCreateTime());
                System.out.println("historicTaskInstance.getEndTime() = " + historicTaskInstance.getEndTime());

            }
        }
    }

    private <T> Predicate<T> distinctByKey(Function<? super T, Object> keyExtractor) {
        Map<Object, Boolean> concurrentHashMap = new ConcurrentHashMap<>();
        return t -> concurrentHashMap.putIfAbsent(keyExtractor.apply(t), Boolean.TRUE) == null;
    }


    @Test
    public void test01() {
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        HistoryService historyService = processEngine.getHistoryService();
        List<HistoricTaskInstance> list = historyService
                .createHistoricTaskInstanceQuery()
                .processDefinitionKey("financial")
                .taskAssignee("2")
                .orderByTaskCreateTime()
                .desc()
                .finished()
                .list();
        list = list.stream().filter(s -> "2".equals(s.getAssignee())).collect(Collectors.toList());
        System.out.println("一共"+ list.size()+"条任务历史数据");
        for (HistoricTaskInstance historicTaskInstance : list) {
            System.out.println("historicTaskInstance.getAssignee() = " + historicTaskInstance.getAssignee());
            System.out.println("historicTaskInstance.getProcessInstanceId() = " + historicTaskInstance.getProcessInstanceId());
            System.out.println("historicTaskInstance.getCreateTime() = " + historicTaskInstance.getCreateTime());
            System.out.println("historicTaskInstance.getEndTime() = " + historicTaskInstance.getEndTime());
            System.out.println("======================================================");
        }

    }

    @Test
    public void test02(){
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        HistoryService historyService = processEngine.getHistoryService();
        HistoricProcessInstanceQuery financial = historyService.createHistoricProcessInstanceQuery().processInstanceName("financial").processInstanceBusinessKey("10");
        HistoricProcessInstance historicProcessInstance = financial.singleResult();
        System.out.println(historicProcessInstance);
/*        System.out.println("historicProcessInstance.getBusinessKey() = " + historicProcessInstance.getBusinessKey());
        System.out.println("historicProcessInstance.getName() = " + historicProcessInstance.getName());
        System.out.println("historicProcessInstance.getProcessDefinitionKey() = " + historicProcessInstance.getProcessDefinitionKey());
        System.out.println("historicProcessInstance.getProcessDefinitionName() = " + historicProcessInstance.getProcessDefinitionName());*/
    }
}
