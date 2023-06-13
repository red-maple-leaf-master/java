package top.oneyi.reject;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.repository.Deployment;
import org.junit.Test;

public class InitTest {
    /**
     * 部署贷款流程
     */
    @Test
    public void activitiDeploymentTest01() {
        //1.创建ProcessEngine对象
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();

        //2.得到RepositoryService实例
        RepositoryService repositoryService = processEngine.getRepositoryService();

        //3.进行部署
        Deployment deployment = repositoryService.createDeployment()
                .addClasspathResource("diagram/LendingProcess.bpmn")
                .name("放款流程")
                .deploy();

        //4.输出部署的一些信息
        System.out.println(deployment.getName());
        System.out.println(deployment.getId());
    }

    /**
     * 部署放款流程
     */
    @Test
    public void activitiDeploymentTest02() {
        //1.创建ProcessEngine对象
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();

        //2.得到RepositoryService实例
        RepositoryService repositoryService = processEngine.getRepositoryService();

        //3.进行部署
        Deployment deployment = repositoryService.createDeployment()
                .addClasspathResource("diagram/lendingFlow.bpmn")
                .name("放款流程")
                .deploy();

        //4.输出部署的一些信息
        System.out.println(deployment.getName());
        System.out.println(deployment.getId());
    }
}
