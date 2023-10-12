package top.oneyi.demo.CompleteFuture;


import java.util.concurrent.*;

public class FutureTest01 {
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        long l2 = System.currentTimeMillis();
        MyThread myThread = new MyThread();
        MyThread myThread02 = new MyThread();
        String str = "我是结果1";
        String str2 = "我是结果2";

        FutureTask<Object> objectFutureTask = new FutureTask<>(myThread, str);
        executorService.submit(objectFutureTask);


        FutureTask<Object> objectFutureTask02 = new FutureTask<>(myThread02, str2);

//        Thread.sleep(200);
        executorService.submit(objectFutureTask02);
        Object o2 = objectFutureTask.get();
        Object o = objectFutureTask02.get();
        System.out.println(o2);
        System.out.println(o);
        long l3 = System.currentTimeMillis();
        // 关闭线程池
        executorService.shutdown();
        System.out.println("总共耗时" + (l3 - l2));

    }
}

class MyThread implements Runnable {

    @Override
    public void run() {
        System.out.println("开启线程" + Thread.currentThread().getName());
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
