package top.oneyi.demo.CompleteFuture;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * applyToEither / acceptEither / runAfterEither
 * 这三个方法都是将两个CompletableFuture组合起来，
 * 只要其中一个执行完了就会执行某个任务(其他线程依然会继续执行)，
 * 其区别在于applyToEither会将已经执行完成的任务的执行结果作为方
 * 法入参，并有返回值；acceptEither同样将已经执行完成的任务的执行
 * 结果作为方法入参，但是没有返回值；runAfterEither没有方法入参，
 * 也没有返回值。注意两个任务中只要有一个执行异常，则将该异常信息
 * 作为指定任务的执行结果
 * @author oneyi
 * @date 2023/2/6
 */

public class FutureTest08 {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        // 创建异步执行任务1:
        CompletableFuture<Double> cf = CompletableFuture.supplyAsync(()->{
            System.out.println(Thread.currentThread()+" start job1,time->"+System.currentTimeMillis());
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
            }
            System.out.println(Thread.currentThread()+" exit job1,time->"+System.currentTimeMillis());
            return 1.2;
        });
        // 创建异步执行任务2:
        CompletableFuture<Double> cf2 = CompletableFuture.supplyAsync(()->{
            System.out.println(Thread.currentThread()+" start job2,time->"+System.currentTimeMillis());
            try {
                Thread.sleep(1500);
            } catch (InterruptedException e) {
            }
            System.out.println(Thread.currentThread()+" exit job2,time->"+System.currentTimeMillis());
            return 3.2;
        });

        // cf和cf2的异步任务都执行完成后，会将其执行结果作为方法入参传递给cf3,且有返回值  applyToEither
        CompletableFuture<Double> cf3=cf.applyToEither(cf2,(result)->{
            System.out.println(Thread.currentThread()+" start job3,time->"+System.currentTimeMillis());
            System.out.println("job3 param result->"+result);
            try {
                Thread.sleep(20);
            } catch (InterruptedException e) {
            }
            System.out.println(Thread.currentThread()+" exit job3,time->"+System.currentTimeMillis());
            return result;
        });


        // cf和cf2的异步任务都执行完成后，会将其执行结果作为方法入参传递给cf3,无返回值
        CompletableFuture cf4=cf.acceptEither(cf2,(result)->{
            System.out.println(Thread.currentThread()+" start job4,time->"+System.currentTimeMillis());
            System.out.println("job4 param result->"+result);
            try {
                Thread.sleep(1500);
            } catch (InterruptedException e) {
            }
            System.out.println(Thread.currentThread()+" exit job4,time->"+System.currentTimeMillis());
        });

        // cf4和cf3都执行完成后，执行cf5，无入参，无返回值
        CompletableFuture cf5=cf4.runAfterEither(cf3,()->{
            System.out.println(Thread.currentThread()+" start job5,time->"+System.currentTimeMillis());
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
            }
            System.out.println("cf5 do something");
            System.out.println(Thread.currentThread()+" exit job5,time->"+System.currentTimeMillis());
        });


        System.out.println("main thread start cf.get(),time->"+System.currentTimeMillis());

        // 等待子任务执行完成
        System.out.println("cf run result->"+cf.get());
        System.out.println("cf3 run result->"+cf3.get());
        System.out.println("main thread start cf5.get(),time->"+System.currentTimeMillis());
        System.out.println("cf5 run result->"+cf5.get());
        System.out.println("main thread exit,time->"+System.currentTimeMillis());

    }
}
