package top.oneyi;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.RepositoryService;

import org.activiti.engine.repository.Deployment;
import org.junit.Test;

import java.io.InputStream;
import java.util.zip.ZipInputStream;

/**
 * 流程定义的部署
 * activiti表有哪些？
 * act_re_deployment  部署信息
 * act_re_procdef     流程定义的一些信息
 * act_ge_bytearray   流程定义的bpmn文件及png文件
 *
 * @author zrj
 * @date 2020/12/29
 * @since V1.0
 **/
public class ActivitiDeployment {

    /**
     * 方式一
     * 分别将 bpmn 文件和 png 图片文件部署
     *
     *
     *  -- activiti表有哪些？
     *  -- 部署信息
     * select * from act_re_deployment ;
     *
     * -- 流程定义的一些信息
     * select * from act_re_procdef;
     *
     *  -- 流程定义的bpmn文件及png文件
     * select * from act_ge_bytearray;
     */
    @Test
    public void activitiDeploymentTest() {
        //1.创建ProcessEngine对象
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();

        //2.得到RepositoryService实例
        RepositoryService repositoryService = processEngine.getRepositoryService();

        //3.进行部署
        Deployment deployment = repositoryService.createDeployment()
                .addClasspathResource("diagram/one.bpmn")
                .addClasspathResource("diagram/one.png")
                .name( "测试" )
                .deploy();

        //4.输出部署的一些信息
        System.out.println( deployment.getName() );
        System.out.println( deployment.getId() );
    }

    @Test
    public void test(){
        //1.创建ProcessEngine对象
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        Deployment deployment = processEngine.getRepositoryService()//与流程定义和部署对象相关的Service
                .createDeployment()//创建一个部署对象
                .name("Activiti入门")//添加部署名称
                .addClasspathResource("diagram/two.bpmn")//从classpath的资源中加载，一次只能加载一个文件
                .addClasspathResource("diagram/two.png")
                .deploy();//完成部署
        System.out.println(deployment.getId());

    }

    /**
     * 方式二
     * 将 holiday.bpmn 和 holiday.png 压缩成 zip 包
     */
    @Test
    public void activitiDeploymentTest2() {
        //1.创建ProcessEngine对象
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();

        //2.得到RepositoryService实例
        RepositoryService repositoryService = processEngine.getRepositoryService();

        //3.转化出ZipInputStream流对象
        InputStream is = ActivitiDeployment.class.getClassLoader().getResourceAsStream("diagram/holidayBPMN.zip");

        //将 inputstream流转化为ZipInputStream流
        ZipInputStream zipInputStream = new ZipInputStream( is );

        //3.进行部署
        Deployment deployment = repositoryService.createDeployment()
                .addZipInputStream( zipInputStream )
                .name( "请假申请单流程" )
                .deploy();

        //4.输出部署的一些信息
        System.out.println( deployment.getName() );
        System.out.println( deployment.getId() );
    }

}
