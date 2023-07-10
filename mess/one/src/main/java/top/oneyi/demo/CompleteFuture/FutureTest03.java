package top.oneyi.demo.CompleteFuture;

import java.util.concurrent.CompletableFuture;

/**
 * thenApply
 *
 * @author oneyi
 * @date 2023/2/6
 */

public class FutureTest03 {
    public static void main(String[] args) throws InterruptedException {
        long start = System.currentTimeMillis();
        CompletableFuture<Void> voidCompletableFuture = CompletableFuture.runAsync(() -> {
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("我是异步异步实行的第一步  runAsync 我没有返回值");
        });

        Thread.sleep(200);
        CompletableFuture<String> uCompletableFuture = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("我是异步实行的第二步  supplyAsync  我有返回值");
            return "执行成功";
        });
        // 接收前置任务的返回值进行 处理并返回  238  需要等前置处理之后才能接着进行下面的操作
 /*       CompletableFuture<String> objectCompletableFuture = uCompletableFuture.thenApply((s) -> {
            System.out.println(s);
            return "我是中间处理流程处理的信息为" + s ;
        });*/
        // 259   异步  是另一个新的线程进行的操作,不在是原来的线程
        CompletableFuture<String> objectCompletableFuture = uCompletableFuture.thenApplyAsync((s) -> {
            System.out.println(s);
            return "我是中间处理流程处理的信息为" + s;
        });
        long end = System.currentTimeMillis();

        System.out.println("voidCompletableFuture.join() = " + voidCompletableFuture.join());
        System.out.println("uCompletableFuture.join() = " + uCompletableFuture.join());
        System.out.println("objectCompletableFuture.join() = " + objectCompletableFuture.join());
        System.out.println("一共耗时为: " + (end - start));
    }
}
