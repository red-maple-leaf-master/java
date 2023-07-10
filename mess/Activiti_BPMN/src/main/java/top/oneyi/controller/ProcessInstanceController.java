package top.oneyi.controller;

import com.mysql.jdbc.StringUtils;
import lombok.RequiredArgsConstructor;
import org.activiti.bpmn.model.BpmnModel;
import org.activiti.bpmn.model.FlowNode;
import org.activiti.bpmn.model.ParallelGateway;
import org.activiti.bpmn.model.SequenceFlow;
import org.activiti.engine.*;
import org.activiti.engine.history.HistoricTaskInstance;
import org.activiti.engine.impl.identity.Authentication;
import org.activiti.engine.impl.util.CollectionUtil;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.runtime.ProcessInstanceQuery;
import org.activiti.engine.task.Comment;
import org.activiti.engine.task.Task;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tk.mybatis.mapper.util.StringUtil;
import top.oneyi.common.exception.ServiceException;
import top.oneyi.mapper.ActBusinessStatusMapper;
import top.oneyi.mapper.SysUserMapper;
import top.oneyi.pojo.*;
import top.oneyi.service.ActBusinessStatusService;

import javax.validation.constraints.NotBlank;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 流程实例
 *
 * @author oneyi
 * @date 2023/3/23
 */

@RequestMapping("/workflow/processInstance")
@RestController
@RequiredArgsConstructor
public class ProcessInstanceController {
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

    private final ActBusinessStatusService actBusinessStatusService;
    private final ActBusinessStatusMapper businessStatusMapper;
    private final SysUserMapper sysUserMapper;

    /**
     * 提交申请 启动实例
     *
     * @param startProcessBo
     * @return
     */
    @PostMapping("/startWorkFlow")
    public Map<String, Object> startWorkFlow(@RequestBody StartProcessBo startProcessBo) {
        Map<String, Object> map = new HashMap<>(16);
        // 1 判断是否传递过来业务id
        if (StringUtils.isNullOrEmpty(startProcessBo.getBusinessKey())) {
            throw new ServiceException("业务id未传递过来");
        }
        // 2 判断是否开启过流程
        List<ProcessInstance> list = runtimeService.createProcessInstanceQuery().processInstanceBusinessKey(startProcessBo.getBusinessKey()).processDefinitionKey(startProcessBo.getProcessKey()).list();
        List<Task> tasks = taskService.createTaskQuery().processInstanceBusinessKey(startProcessBo.getBusinessKey()).list();
        if (list.size() > 0) {
            // 说明流程已经开始
            // 查询  实例和业务key 中间表  查看流程状态
            map.put("processInstanceId", list.get(0).getProcessInstanceId());

        }
        //启动 实例  设置启动人
        Authentication.setAuthenticatedUserId("5");
        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey(startProcessBo.getProcessKey(), startProcessBo.getBusinessKey(), startProcessBo.getVariables());
        String processInstanceId = processInstance.getProcessInstanceId();
        // 将流程定义名称 作为 流程实例名称
        runtimeService.setProcessInstanceName(processInstanceId, processInstance.getProcessDefinitionName());
        map.put("processInstanceId", processInstanceId);
        return map;
    }

    /**
     * 通过流程实例id 查询流程审批记录
     *
     * @param processInstanceId
     * @return
     */
    @GetMapping("/getHistoryInfoList/{processInstanceId}")
    public List<ActHistoryInfoVo> getHistoryInfoList(@NotBlank(message = "流程实例id不能为空") @PathVariable String processInstanceId) {
        // 排序查到所有的历史任务
        List<HistoricTaskInstance> list = historyService.createHistoricTaskInstanceQuery()
                .processInstanceId(processInstanceId)
                .orderByHistoricTaskInstanceEndTime()
                .desc().list();
        List<ActHistoryInfoVo> historyInfoList = new ArrayList<>();
        for (HistoricTaskInstance historicTaskInstance : list) {
            ActHistoryInfoVo actHistoryInfoVo = new ActHistoryInfoVo();
            // 复制属性
            BeanUtils.copyProperties(historicTaskInstance, actHistoryInfoVo);

            actHistoryInfoVo.setStatus(actHistoryInfoVo.getEndTime() == null ? "待处理" : "已处理");
            List<Comment> taskComments = taskService.getTaskComments(historicTaskInstance.getId());
            if (CollectionUtil.isNotEmpty(taskComments)) {
                actHistoryInfoVo.setCommentId(taskComments.get(0).getId());
                String message = taskComments.stream()
                        .map(Comment::getFullMessage).collect(Collectors.joining("。"));
                if (org.apache.commons.lang3.StringUtils.isNotBlank(message)) {
                    actHistoryInfoVo.setComment(message);
                }
            }
            historyInfoList.add(actHistoryInfoVo);
        }

        //翻译人员名称
        if (historyInfoList.size() > 0) {
            List<Long> assigneeList = historyInfoList.stream().map(e -> Long.valueOf(e.getAssignee())).collect(Collectors.toList());
            if (assigneeList.size() > 0) {
                List<SysUser> sysUsers = sysUserMapper.findByIds(assigneeList);
                historyInfoList.forEach(e -> {
                    sysUsers.stream().filter(u -> u.getUserId().toString().equals(e.getAssignee())).findFirst().ifPresent(u -> {
                        e.setNickName(u.getNickName());
                    });
                });
            }
        }
        return historyInfoList;
    }

    /**
     * 查询正在运行的流程实例
     *
     * @param req
     * @return
     */
    @GetMapping("/getProcessInstRunningByPage")
    public List<ProcessInstRunningVo> getProcessInstRunningByPage(ProcessInstRunningBo req) {
        // 实例创建对象
        ProcessInstanceQuery query = runtimeService.createProcessInstanceQuery();
        if (req.getName() != null) {
            query.processInstanceNameLikeIgnoreCase(req.getName());
        }
        if (req.getStartUserId() != null) {
            query.startedBy(req.getStartUserId());
        }
        List<ProcessInstance> processInstances = query.listPage(req.getFirstResult(), req.getPageSize());
        List<ProcessInstRunningVo> processInstRunningVoList = new ArrayList<>();
        long total = query.count();
        //办理人
        StringBuilder currTaskInfo = new StringBuilder();
        //办理人id
        StringBuilder currTaskInfoId = new StringBuilder();
        //办理人集合
        List<String> nickNameList = null;
        for (ProcessInstance processInstance : processInstances) {
            ProcessInstRunningVo processInstRunningVo = new ProcessInstRunningVo();
            BeanUtils.copyProperties(processInstance, processInstRunningVo);
            SysUser sysUser = sysUserMapper.selectByPrimaryKey(processInstance.getStartUserId());
            if (sysUser != null) {
                processInstRunningVo.setStartUserNickName(sysUser.getNickName());
            }
            processInstRunningVo.setIsSuspended(processInstance.isSuspended() ? "挂起" : "激活");
            // 获取任务 只要 ParentTaskId 为 null的
            List<Task> list = taskService.createTaskQuery().processInstanceId(processInstance.getProcessInstanceId()).list();
            list = list.stream().filter(s -> StringUtil.isEmpty(s.getParentTaskId())).collect(Collectors.toList());
            for (Task task : list) {
                if (task.getAssignee() != null) {
                    String[] split = task.getAssignee().split(",");
                    List<Long> userIds = new ArrayList<>();
                    for (String userId : split) {
                        userIds.add(Long.valueOf(userId));
                    }
                    //办理人
                    List<SysUser> sysUsers = sysUserMapper.findByIds(userIds);
                    if (CollectionUtil.isNotEmpty(sysUsers)) {
                        nickNameList = sysUsers.stream().map(SysUser::getNickName).collect(Collectors.toList());
                        StringJoiner joiner = new StringJoiner(",");
                        nickNameList.forEach(joiner::add);
                        currTaskInfo.append("任务名【").append(task.getName()).append("】，办理人【").append(joiner).append("】");
                        currTaskInfoId.append(task.getAssignee());
                    }
                }
                // 当前办理人
                processInstRunningVo.setCurrTaskInfo(currTaskInfo.toString());
                // 当前办理人ID
                processInstRunningVo.setCurrTaskInfoId(currTaskInfoId.toString());
                processInstRunningVoList.add(processInstRunningVo);
            }

        }

        return processInstRunningVoList;
    }

    /**
     * 激活或挂起流程实例
     *
     * @param data
     */
    @PutMapping("/state")
    public void updateProcInstState(@RequestBody Map<String, Object> data) {
        try {
            String processInstId = data.get("processInstId").toString();
            String reason = data.get("reason").toString();
            // 1. 查询指定流程实例的数据
            ProcessInstance processInstance = runtimeService.createProcessInstanceQuery()
                    .processInstanceId(processInstId)
                    .singleResult();
            // 2 判断流程实例的状态
            if (processInstance.isSuspended()) {
                // 如果是已挂起,则更新为激活状态
                runtimeService.activateProcessInstanceById(processInstId);
            } else {
                //如果是已激活 则更新为挂起状态
                runtimeService.suspendProcessInstanceById(processInstId);
            }
            ActBusinessStatus actBusinessStatus = businessStatusMapper.selectByProcessInstanceId(processInstId);
            if (actBusinessStatus == null) {
                throw new ServiceException("当前流程异常，未生成act_business_status对象");
            }
            actBusinessStatus.setSuspendedReason(reason);
            businessStatusMapper.updateByPrimaryKey(actBusinessStatus);


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 撤销申请
     *
     * @param processInstId
     */
    @GetMapping("/cancelProcessApply/{processInstId}")
    public void cancelProcessApply(@NotBlank(message = "流程实例id不能为空") @PathVariable String processInstId) {
        // 1 判断  当前登陆人是否流程创建人
        ProcessInstance processInstance = runtimeService.createProcessInstanceQuery().processInstanceId(processInstId).startedBy("5").singleResult();
        if (processInstance == null) {
            throw new ServiceException("流程不是该审批人提交,撤销失败!");
        }
        // 2 检验流程的状态

        // 3 查询到回撤节点信息
        List<HistoricTaskInstance> list1 = historyService.createHistoricTaskInstanceQuery().processInstanceId(processInstance.getProcessInstanceId())
                .orderByHistoricTaskInstanceEndTime().desc().list();
        // 目标节点
        HistoricTaskInstance actTaskNode = list1.get(0);
        // 获取当前任务
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
                if (source instanceof ParallelGateway) {
                    // 并行网关: 获取目标节点的父节点(并行网关) 的所有出口
                    sequenceFlows = source.getOutgoingFlows();
                } else {
                    // 其他类型父节点,则获取目标节点的入口连续
                    sequenceFlows = targetFlowNode.getIncomingFlows();
                }
                targetSequenceFlow.addAll(sequenceFlows);
            }
            // 9. 将当前节点的出口设置为新节点的入口连线
            flowNode.setOutgoingFlows(targetSequenceFlow);
            // 10. 完成当前任务，流程就会流向目标节点创建新目标任务
            // 当前任务，完成当前任务
            taskService.addComment(task.getId(), task.getProcessInstanceId(), "申请人撤销申请");
            taskService.setAssignee(task.getId(), "1002我是撤回申请人");
            // 完成任务，就会进行驳回到目标节点，产生目标节点的任务数据
            taskService.complete(task.getId());
            // 11. 完成驳回功能后，将当前节点的原出口方向进行恢复
            flowNode.setOutgoingFlows(oldsequenceFlows);

        }
    }
}
