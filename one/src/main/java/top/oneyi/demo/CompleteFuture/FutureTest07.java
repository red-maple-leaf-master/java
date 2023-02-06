package top.oneyi.demo.CompleteFuture;

import java.util.concurrent.CompletableFuture;

/**
 *  thenCombine
 *  thenCombine最大的不同是连接任务可以是一个独立的CompletableFuture
 *（或者是任意实现了CompletionStage的类型），从而允许前后连接的两个
 *  任务可以并行执行（后置任务不需要等待前置任务执行完成），最后当两个
 *  任务均完成时，再将其结果同时传递给下游处理任务，从而得到最终结果。
 * @author oneyi
 * @date 2023/2/6
 */

public class FutureTest07 {
    public static void main(String[] args) {
        CompletableFuture<Integer> future01 = CompletableFuture.supplyAsync(() -> {
            System.out.println("第一个数值为1");
            return 1;
        });
        CompletableFuture<Integer> future02 = CompletableFuture.supplyAsync(() -> {
            System.out.println("第一个数值为10");
            return 10;
        });
        CompletableFuture<Integer> future03 = future01.thenCombine(future02, (r1, r2) -> {
            System.out.println("使用thenCombine 连接俩个任务 处理过后 数值为" + (r1 + r2));
            return r1 + r2;
        });
        System.out.println("future03.join() = " + future03.join());

        // 这俩个方法跟 thenCombine 类似
        // thenAcceptBoth 有参数 无返回值 俩个参数分别为 俩个任务的返回值
        CompletableFuture future04 = future01.thenAcceptBoth(future02, (r1, r2) -> {
            System.out.println("第一个参数为"+ r1+ "第二参数为"+ r2);


        });
        // runAfterBoth 无参数 无返回值
        CompletableFuture future05 = future01.runAfterBoth(future02, () -> {
            System.out.println("runAfterBoth 处理过了");

        });

    }
}
