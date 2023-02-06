package top.oneyi.demo.CompleteFuture;

import java.util.concurrent.*;

/**
 * 使用Future进行异步调用
 */
public class FutureTest {
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        // 创建固定大小的线程池   源码其实就是new 了一个ThreadPoolExecutor对象,
        ExecutorService executorService = Executors.newFixedThreadPool(10);

        long l2 = System.currentTimeMillis();
        FutureTask<Object> objectFutureTask = new FutureTask<>(new Callable<Object>() {
            @Override
            public Object call() throws Exception {
                Thread.sleep(200);
                System.out.println(Thread.currentThread().getName());
                return "查询订单";
            }
        });
        Thread.sleep(100);
//        Object o = objectFutureTask.get(); // 放在这里则会一直阻塞 线程无法结束
        executorService.submit(objectFutureTask);

//        Object o = objectFutureTask.get();  // 放在这里  耗时就是是 538 下面就是300多
        FutureTask<Object> objectFutureTask01 = new FutureTask<>(new Callable<Object>() {
            @Override
            public Object call() throws Exception {

                Thread.sleep(200);
                System.out.println(Thread.currentThread().getName());
                return "查询客户";
            }
        });
        executorService.submit(objectFutureTask01);

        while(objectFutureTask.isDone()){
            // 轮询获取结果
            // 获取每个线程返回的信息  get方法是阻塞方法 线程获取结果以前会一致阻塞 导致异步失败,  里面可以填写超时时间,超过时间返回null
            Object o = objectFutureTask.get();
            Object o1 = objectFutureTask01.get();
            System.out.println( o  + " === "+o1);
        }

        long l3 = System.currentTimeMillis();
        // 关闭线程池
        executorService.shutdown();
        System.out.println("总共耗时"+ (l3 - l2));

    }
}
