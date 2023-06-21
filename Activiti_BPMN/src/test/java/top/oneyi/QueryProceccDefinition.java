package top.oneyi;



import cn.hutool.core.lang.Pair;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.repository.ProcessDefinitionQuery;
import org.activiti.engine.task.Task;

import org.junit.Test;

import javax.xml.ws.Response;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;

/**
 * 流程定义查询
 *
 * @author zrj
 * @date 2020/12/29
 * @since V1.0
 **/
public class QueryProceccDefinition {

    /**
     * 给定一个包含 0, 1, 2, ..., n 中 n 个数的序列，找出 0 … n 中没有出现在序列中的那个数。
     */
    @Test
    public void test02() {
        int[] arr = new int[]{9, 6, 4, 2, 3, 5, 7, 0, 1};
        int length = arr.length;
        int max = length * (length + 1) / 2;
        System.out.println("max = " + max);
        for (int i : arr) {
            max = max - i;
        }
        System.out.println("max = " + max);
        sort(arr);
    }

    /**
     * 插入排序
     *
     * @param sourceArray
     * @return
     * @throws Exception
     */
    public int[] sort(int[] sourceArray){
        // 对 arr 进行拷贝，不改变参数内容
        int[] arr = Arrays.copyOf(sourceArray, sourceArray.length);

        // 从下标为1的元素开始选择合适的位置插入，因为下标为0的只有一个元素，默认是有序的
        for (int i = 1; i < arr.length; i++) {

            // 记录要插入的数据
            int tmp = arr[i];

            // 从已经排序的序列最右边的开始比较，找到比其小的数
            int j = i;
            while (j > 0 && tmp < arr[j - 1]) {
                arr[j] = arr[j - 1];
                j--;
            }

            // 存在比其小的数，插入
            if (j != i) {
                arr[j] = tmp;
            }

        }
        return arr;
    }

    /**
     * 希尔排序
     *
     * @param arr
     */
    public static void shellSort(int[] arr) {
        int length = arr.length;
        int temp;
        for (int step = length / 2; step >= 1; step /= 2) {
            for (int i = step; i < length; i++) {
                temp = arr[i];
                int j = i - step;
                while (j >= 0 && arr[j] > temp) {
                    arr[j + step] = arr[j];
                    j -= step;
                }
                arr[j + step] = temp;
            }
        }
    }


    @Test
    public void queryProceccDefinition() {
        // 流程定义key
        String processDefinitionKey = "wan";
        //1.得到ProcessEngine对象
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();

        // 获取repositoryService
        RepositoryService repositoryService = processEngine.getRepositoryService();
        // 查询流程定义
        ProcessDefinitionQuery processDefinitionQuery = repositoryService.createProcessDefinitionQuery();

        //遍历查询结果
        List<ProcessDefinition> list = processDefinitionQuery
                .processDefinitionKey(processDefinitionKey)
                .orderByProcessDefinitionVersion().desc().list();

        for (ProcessDefinition processDefinition : list) {
            System.out.println("------------------------");
            System.out.println(" 流 程 部 署 id ： " + processDefinition.getDeploymentId());
            System.out.println("流程定义id： " + processDefinition.getId());
            System.out.println("流程定义名称： " + processDefinition.getName());
            System.out.println("流程定义key： " + processDefinition.getKey());
            System.out.println("流程定义版本： " + processDefinition.getVersion());
        }
    }

    /**
     * 删除指定流程id的流程
     */
    @Test
    public void deleteDeployment() {
        // 流程部署id
        String deploymentId = "5001";
        //1.得到ProcessEngine对象
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();

        // 通过流程引擎获取repositoryService
        RepositoryService repositoryService = processEngine.getRepositoryService();
        //删除流程定义， 如果该流程定义已有流程实例启动则删除时出错
        repositoryService.deleteDeployment(deploymentId);
        //设置true 级联删除流程定义，即使该流程有流程实例启动也可以删除，设
        //置为false非级别删除方式，如果流程
        repositoryService.deleteDeployment(deploymentId, true);
    }

    /**
     * 获取资源
     */
    @Test
    public void getProcessResources() throws IOException {
        // 流程定义id
        String processDefinitionId = "PROCESS_1:1:4";
        //1.得到ProcessEngine对象
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        // 获取repositoryService
        RepositoryService repositoryService = processEngine.getRepositoryService();
        // 流程定义对象
        ProcessDefinition processDefinition = repositoryService
                .createProcessDefinitionQuery()
                .processDefinitionId(processDefinitionId).singleResult();
        //获取bpmn
        String resource_bpmn = processDefinition.getResourceName();
        //获取png
        String resource_png = processDefinition.getDiagramResourceName();
        // 资源信息
        System.out.println("bpmn： " + resource_bpmn);
        System.out.println("png： " + resource_png);
        File file_png = new File("d:/purchasingflow01.png");
        File file_bpmn = new File("d:/purchasingflow01.bpmn");
        // 输出bpmn
        InputStream resourceAsStream = null;
        resourceAsStream = repositoryService.getResourceAsStream(processDefinition.getDeploymentId(), resource_bpmn);
        FileOutputStream fileOutputStream = new FileOutputStream(file_bpmn);
        byte[] b = new byte[1024];
        int len = -1;
        while ((len = resourceAsStream.read(b, 0, 1024)) != -1) {
            fileOutputStream.write(b, 0, len);
        }
        // 输出图片
        resourceAsStream = repositoryService.getResourceAsStream(processDefinition.getDeploymentId(), resource_png);
        fileOutputStream = new FileOutputStream(file_png);
        // byte[] b = new byte[1024];
        // int len = -1;
        while ((len = resourceAsStream.read(b, 0, 1024)) != -1) {
            fileOutputStream.write(b, 0, len);
        }
    }

    @Test
    public void test() {

        //1.得到ProcessEngine对象
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        String assignee = "jerry";
        List<Task> list = processEngine.getTaskService().createTaskQuery().taskAssignee(assignee).list();
        System.out.println("==================[" + assignee + "]的个人任务列表=======================");
        for (Task task : list) {
            System.out.println("name: " + task.getName());
            System.out.println("createName: " + task.getCreateTime());
            System.out.println("assignee: " + task.getAssignee());
        }

    }

    @Test
    public void test01() {
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        String assignee = "田总";
        List<Task> list = processEngine.getTaskService()//与正在执行任务相关的Service
                .createTaskQuery()//创建任务查询对象
                .taskAssignee(assignee)//指定个人任务查询，指定办理人
                .list();
        if (list != null && list.size() > 0) {
            for (Task task : list) {
                System.out.println("任务ID：" + task.getId());
                System.out.println("任务名称：" + task.getName());
                System.out.println("任务创建时间：" + task.getCreateTime());
                System.out.println("任务办理人：" + task.getAssignee());
                System.out.println("流程实例ID：" + task.getProcessInstanceId());
                System.out.println("执行对象ID：" + task.getExecutionId());
                System.out.println("流程定义ID：" + task.getProcessDefinitionId());
            }

        }
    }

    @Test
    public void test03(){
        Pair<Boolean,String> pair = new Pair<>(true,"历史");
        System.out.println(pair.getKey());
        System.out.println(pair.getValue());
    }

}
