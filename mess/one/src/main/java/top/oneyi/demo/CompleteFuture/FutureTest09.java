package top.oneyi.demo.CompleteFuture;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * allOf返回的CompletableFuture是多个任务都执行完成后才会执，只要有一个任务执行异常，则返回的CompletableFuture执行get方法时会抛出异常，如果都是正常执行，则get返回null。
 * <p>
 * anyOf返回的CompletableFuture是多个任务只要其中一个执行完成就会执行，其get返回的是已经执行完成的任务的执行结果，如果该任务执行异常，则抛出异常。
 * <p>
 * join()与get()区别：
 * <p>
 * join()返回计算的结果或者抛出一个unchecked异常(CompletionException)
 * <p>
 * get() 返回一个具体的异常，get() 可以指定超时时间
 *
 * @author oneyi
 * @date 2023/2/8
 */

public class FutureTest09 {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        // 创建异步执行任务:
        CompletableFuture<Double> cf = CompletableFuture.supplyAsync(() -> {
            System.out.println(Thread.currentThread() + " start job1,time->" + System.currentTimeMillis());
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
            }
            System.out.println(Thread.currentThread() + " exit job1,time->" + System.currentTimeMillis());
            return 1.2;
        });
        CompletableFuture<Double> cf2 = CompletableFuture.supplyAsync(() -> {
            System.out.println(Thread.currentThread() + " start job2,time->" + System.currentTimeMillis());
            try {
                Thread.sleep(1500);
            } catch (InterruptedException e) {
            }
            System.out.println(Thread.currentThread() + " exit job2,time->" + System.currentTimeMillis());
            return 3.2;
        });
        CompletableFuture<Double> cf3 = CompletableFuture.supplyAsync(() -> {
            System.out.println(Thread.currentThread() + " start job3,time->" + System.currentTimeMillis());
            try {
                Thread.sleep(1300);
            } catch (InterruptedException e) {
            }
//            throw new RuntimeException("test");
            System.out.println(Thread.currentThread() + " exit job3,time->" + System.currentTimeMillis());
            return 2.2;
        });

        // allof等待所有任务执行完成才执行cf4，如果有一个任务异常终止，则cf4.get时会抛出异常，都是正常执行，cf4.get返回null
        // anyOf是只有一个任务执行完成，无论是正常执行或者执行异常，都会执行cf4，cf4.get的结果就是已执行完成的任务的执行结果
        CompletableFuture cf4 = CompletableFuture.allOf(cf, cf2, cf3).whenComplete((a, b) -> {
            if (b != null) {
                System.out.println("error stack trace->");
                b.printStackTrace();
            } else {
                System.out.println("run succ,result->" + a);
            }
        });

        System.out.println("main thread start cf4.get(),time->" + System.currentTimeMillis());
        // 等待子任务执行完成
        System.out.println("cf4 run result->" + cf4.get());
        System.out.println("main thread exit,time->" + System.currentTimeMillis());

    }
}
