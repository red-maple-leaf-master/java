package top.oneyi.demo.CompleteFuture;

import java.util.concurrent.CompletableFuture;

/**
 * exceptionally 异常返回 exceptionally 方法指定某个任务执行异常时执行的回调方法，
 * 会将抛出异常作为参数传递到回调方法中，如果该任务正常执行，则exceptionally方法返
 * 回的CompletionStage的result就是该任务正常执行的结果。
 *
 * @author oneyi
 * @date 2023/2/6
 */

public class FutureTest05 {
    public static void main(String[] args) {
        CompletableFuture<String> scf = CompletableFuture.supplyAsync(() -> {
            System.out.println("正常的运行程序");
            try {
                // 处理之后后面还是正常运行, exceptionally 方法失效
                throw new RuntimeException("运行时异常");
            } catch (Exception e) {
                e.printStackTrace();
            }
            return "成功运行";

        });
        CompletableFuture<String> ec = scf.exceptionally((param) -> {
            System.out.println("异常之后 使用exceptionally 截断处理流,之后的流式处理无法完成");
            return String.valueOf(param) + "第一次运行";
        });
        CompletableFuture<String> ec2 = scf.thenApply((param) -> {
            System.out.println("异常之后第二次");
            return String.valueOf(param) + "第二次运行";
        });

        System.out.println("scf.join() = " + scf.join());
        System.out.println("ec.join() = " + ec.join());
        System.out.println("ec2 = " + ec2);
    }
}
