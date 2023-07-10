package top.oneyi.demo.CompleteFuture;

import java.util.concurrent.CompletableFuture;

/**
 * thenApply / thenAccept / thenRun互相依赖 流式处理
 * thenAccept / thenRun
 *
 * @author oneyi
 * @date 2023/2/6
 */

public class FutureTest04 {
    public static void main(String[] args) {
        // 创建任务并返回字符串
        CompletableFuture<String> strFuture = CompletableFuture.supplyAsync(() -> {
            return "1";
        });
        CompletableFuture<Void> voidCompletableFuture = strFuture.thenApply((result) -> {
            result = result + "2";
            return result;
        }).thenAccept((result) -> {
            result = result + "2";
        }).thenRun(() -> {

        });
        System.out.println("strFuture = " + strFuture.join());
        System.out.println("voidCompletableFuture.join() = " + voidCompletableFuture.join());
    }
}
