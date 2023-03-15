package top.oneyi.controller;

import org.activiti.engine.*;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.ProcessDefinition;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.oneyi.pojo.vo.ProcessDefinitionVo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequestMapping("/workflow/processDefinition")
@RestController
public class ProcessDefinitionController {

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

    /**
     * 查询流程实例定义
     * @return
     */
    @GetMapping("/list")
    public Map<String,Object> list(){
        List<ProcessDefinition> list = repositoryService.createProcessDefinitionQuery().list();
        List<ProcessDefinitionVo> vos = new ArrayList<>();
        for (ProcessDefinition processDefinition : list) {
            ProcessDefinitionVo vo = new ProcessDefinitionVo();
            BeanUtils.copyProperties(processDefinition,vo);
            // 部署时间
            Deployment deployment = repositoryService.createDeploymentQuery()
                    .deploymentId(processDefinition.getDeploymentId()).singleResult();
            if (deployment != null && deployment.getDeploymentTime() != null) {
                vo.setDeploymentTime(deployment.getDeploymentTime());
            }
            vos.add(vo);
        }
        Map<String,Object> maps = new HashMap<>();
        maps.put("vos",vos);
        return maps;
    }
}
