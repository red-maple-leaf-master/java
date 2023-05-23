package top.oneyi.reject;

import org.activiti.engine.*;
import org.activiti.engine.history.HistoricTaskInstance;
import org.activiti.engine.impl.identity.Authentication;
import org.activiti.engine.impl.util.CollectionUtil;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Comment;
import org.activiti.engine.task.Task;

import org.junit.jupiter.api.Test;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import top.oneyi.demo.ActivitiUtil;
import top.oneyi.mapper.ActBusinessStatusMapper;
import top.oneyi.pojo.ActBusinessStatus;
import top.oneyi.pojo.ActHistoryInfoVo;
import top.oneyi.pojo.SysUser;

import javax.annotation.Resource;
import java.util.*;
import java.util.stream.Collectors;

@SpringBootTest
public class LoanApplicationTest {

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

    private static final String KEY = "loanFlow";
    private static final String BusinessKey = "1";

    @Resource
    private ActBusinessStatusMapper businessStatusMapper;

    @Autowired
    private ActivitiUtil activitiUtil;
    /**
     * 启动流程
     */
    @Test
    public void startFlow() {
        Map<String, Object> map = new HashMap<>();
        map.put("khjl", "1");
        map.put("bmjl", "2");
        map.put("zxfzr", "3");
        map.put("zjl", "4");
        Authentication.setAuthenticatedUserId("0");
        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey(KEY, BusinessKey, map);
        String processInstanceId = processInstance.getProcessInstanceId();
        ActBusinessStatus actBusinessStatus = businessStatusMapper.selectByPrimaryKey(BusinessKey);

        actBusinessStatus.setProcessInstanceId(processInstanceId);
        actBusinessStatus.setUpdateTime(new Date());
        actBusinessStatus.setUpdateBy("管理员");
        actBusinessStatus.setStatus("waiting");
        if(actBusinessStatus == null){
            actBusinessStatus.setBusinessKey(BusinessKey);
            actBusinessStatus.setId(BusinessKey);
            actBusinessStatus.setCreateTime(new Date());
            actBusinessStatus.setCreateBy("管理员");
            businessStatusMapper.insert(actBusinessStatus);
        }else{
            businessStatusMapper.updateByPrimaryKey(actBusinessStatus);
        }


    }
    /**
     * 审批==完成任务
     */
    @Test
    public void doTask() {
        ProcessInstance processInstance = activitiUtil.findProcessInstance(BusinessKey, KEY);
        if (processInstance != null) {
            Task task = activitiUtil.findTask(processInstance.getProcessInstanceId());
            taskService.addComment(task.getId(), task.getProcessInstanceId(), "通过意见");
            taskService.complete(task.getId());
        } else {
            System.out.println("任务已终结");
        }
    }

    /**
     * 根据审批人获取任务集合
     */
    @Test
    public void queryTask(){
        List<Task> list = taskService.createTaskQuery()
                .processDefinitionKey(KEY)
//                .processInstanceBusinessKey(BusinessKey)
                .taskAssignee("1")
                .list();
        for (Task task : list) {
            System.out.println("task = " + task);
            System.out.println("task.getProcessInstanceId() = " + task.getProcessInstanceId());
            System.out.println("task.getAssignee() = " + task.getAssignee());
            System.out.println("task.getName() = " + task.getName());
            System.out.println("task.getProcessDefinitionId() = " + task.getProcessDefinitionId());
            System.out.println("===============================================");
        }


    }
    /**
     * 根据业务key获取任务集合
     */
    @Test
    public void queryBusinessTasks(){
        List<Task> list = taskService.createTaskQuery()
                .processDefinitionKey(KEY)
                .processInstanceBusinessKey(BusinessKey)
//                .taskAssignee("1")
                .list();
        for (Task task : list) {
            System.out.println("task = " + task);
            System.out.println("task.getProcessInstanceId() = " + task.getProcessInstanceId());
            System.out.println("task.getAssignee() = " + task.getAssignee());
            System.out.println("task.getName() = " + task.getName());
            System.out.println("task.getProcessDefinitionId() = " + task.getProcessDefinitionId());
            System.out.println("===============================================");
        }


    }
    /**
     * 查询审批历史  返回视图
     */
    @Test
    public void test04() {

        String processInstanceId = businessStatusMapper.selectByAssignee(BusinessKey);

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
/*        //翻译人员名称
        if (actHistoryInfoVoList.size() > 0) {
            List<Long> assigneeList = actHistoryInfoVoList.stream().map(e -> Long.valueOf(e.getAssignee())).collect(Collectors.toList());
            if (assigneeList.size() > 0) {
                List<SysUser> sysUsers = sysUserMapper.findByIds(assigneeList);
                actHistoryInfoVoList.forEach(e -> {
                    sysUsers.stream().filter(u -> u.getUserId().toString().equals(e.getAssignee())).findFirst().ifPresent(u -> {
                        e.setNickName(u.getNickName());
                    });
                });
            }
        }*/
        List<ActHistoryInfoVo> collect = new ArrayList<>();
        //待办理
        List<ActHistoryInfoVo> waitingTask = actHistoryInfoVoList.stream().filter(e -> e.getEndTime() == null).collect(Collectors.toList());
        //已办理
        List<ActHistoryInfoVo> finishTask = actHistoryInfoVoList.stream().filter(e -> e.getEndTime() != null).collect(Collectors.toList());

        collect.addAll(waitingTask);
        collect.addAll(finishTask);

        for (ActHistoryInfoVo actHistoryInfoVo : collect) {
            System.out.println("actHistoryInfoVo = " + actHistoryInfoVo.toString());
        }

    }


    @Test
    public void queryProcessInstance(){
        ProcessInstance processInstance = activitiUtil.findProcessInstance(BusinessKey, KEY);
        System.out.println("processInstance = " + processInstance);
    }

}
