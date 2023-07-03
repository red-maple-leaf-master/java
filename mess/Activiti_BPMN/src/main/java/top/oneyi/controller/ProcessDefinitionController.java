package top.oneyi.controller;

import org.activiti.engine.*;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.DeploymentBuilder;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.repository.ProcessDefinitionQuery;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import top.oneyi.pojo.vo.ProcessDefinitionVo;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipInputStream;

/**
 * 流程定义
 * @author oneyi
 * @date 2023/3/22
 */

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
     * 通过zip或xml部署流程定义
     * @param file
     */
    @PostMapping("/deployByFile")
    public void deployByFile(@RequestParam("file") MultipartFile file) {
        try{
            // 文件名+后缀名
            String filename = file.getOriginalFilename();
            // 文件后缀名
            assert filename != null;
            String suffix = filename.substring(filename.lastIndexOf(".") + 1).toUpperCase();
            InputStream inputStream = file.getInputStream();
            DeploymentBuilder deployment = repositoryService.createDeployment();
            if("zip".equals(suffix)){
                // zip
                deployment.addZipInputStream(new ZipInputStream(inputStream));
            }else{
                // xml 或者  bpmn
                deployment.addInputStream(filename, inputStream);
            }

            // 部署名称
            deployment.name(filename.substring(0, filename.lastIndexOf(".")));

            // 开始部署
            deployment.deploy();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

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

    /**
     *  查询历史流程实例
     * @param key 流程实例key
     * @param id 实例id
     * @return
     */
    @GetMapping("/hisList")
    public List<ProcessDefinitionVo> getHisByPage(String key,String id) {
        ProcessDefinitionQuery query = repositoryService.createProcessDefinitionQuery().processDefinitionKey(key);
        List<ProcessDefinition> definitionList = query.list();
        List<ProcessDefinitionVo> list = new ArrayList<>();
        for (ProcessDefinition processDefinition : definitionList) {
            if(!processDefinition.getId().equals(id)){
                Deployment deployment = repositoryService.createDeploymentQuery().deploymentId(processDefinition.getDeploymentId()).singleResult();
                ProcessDefinitionVo vo = new ProcessDefinitionVo();
                BeanUtils.copyProperties(processDefinition,vo);
                vo.setDeploymentTime(deployment.getDeploymentTime());
                list.add(vo);
            }
        }
        return list;
    }
}
