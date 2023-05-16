package top.oneyi.demo.Thread;

import org.junit.Test;

public class ThreadDemo {

    // 创建资源
    private static final Object resourceA = new Object();
    private static final Object resourceB = new Object();

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

    @Test
    public void test03(){

    }
}
