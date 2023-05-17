package top.oneyi.demo.Thread;

import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import top.oneyi.ActivtiSpringBootApplication;
import top.oneyi.controller.AxiosController;

import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.Matchers.equalTo;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
@Slf4j
@RunWith(SpringRunner.class)//当前类为 springBoot 的测试类
@SpringBootTest(classes = ActivtiSpringBootApplication.class)//加载 SpringBoot 启动类
public class ThreadDemo {

    // 创建资源
    private static final Object resourceA = new Object();
    private static final Object resourceB = new Object();

    @Value("${com.didispace.from:}")
    private String from;

    @Test
    public void test04(){
        log.info("com.didispace.from : {}", from);
    }

    @Test
    public void test01() throws InterruptedException {
        Thread threadA = new Thread(() -> {
            try {
                // 获取资源A
                synchronized (resourceA) {
                    System.out.println("我获得了resourceA");
                    synchronized (resourceB) {
                        // 获得B的搜
                        System.out.println("我获得了resourceB");
                        System.out.println("我阻塞了resourceA");
                        resourceA.wait();
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        Thread threadB = new Thread(() -> {
            try {

                Thread.sleep(1000);
                // 获取资源A
                synchronized (resourceA) {
                    System.out.println("我获得了resourceA");
                    synchronized (resourceB) {
                        // 获得B的搜
                        System.out.println("我获得了resourceB");
                        System.out.println("我阻塞了resourceA");
                        resourceA.wait();
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        threadA.start();
        threadB.start();
        threadA.join();
        threadB.join();
    }

    @Test
    public void test02() throws InterruptedException {
        Thread thread = new Thread(() -> {
            try {
                System.out.println("threadOne begin sleep for 2000 seconds");
                Thread.sleep(2000000);
                System.out.println("threadOne awaking");
            } catch (Exception e) {
                System.out.println("threadOne is interrupted while sleeping");
            }
            System.out.println("threadOne-leaving onrmally");
        });
        // 启动线程
        thread.start();
        // 确保子线程进入休眠状态
        Thread.sleep(1000);
        // 打断子线程的休眠 , 让子线程从sleep函数返回
        thread.interrupt();
        // 等待子线程执行完毕
        thread.join();

        System.out.println("main thread is over");
    }

    private MockMvc mvc;

    @Before
    public void setUp(){
        mvc = MockMvcBuilders.standaloneSetup(new AxiosController()).build();
    }


    @Test
    public void test03() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/api/a1").accept(MediaType.APPLICATION_JSON))
                //import static org.hamcrest.Matchers.equalTo;
                //import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
                //import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
                // 要想 status 和 content 可以用 导入上面的依赖
                .andExpect(status().isOk())
                .andExpect(content().string(equalTo("get request")));
    }


}
