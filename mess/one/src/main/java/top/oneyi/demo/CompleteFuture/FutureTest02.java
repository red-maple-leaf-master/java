package top.oneyi.demo.CompleteFuture;

import lombok.SneakyThrows;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.function.Supplier;

/**
 * 使用CompletableFuture 实现异步调用
 *
 * @author oneyi
 * @date 2023/2/6
 */

public class FutureTest02 {
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        long start = System.currentTimeMillis();
        CompletableFuture<String> uCompletableFuture = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "成功获取客户信息";
        });

        Thread.sleep(200);
        CompletableFuture<String> stringCompletableFuture = CompletableFuture.supplyAsync(new Supplier<String>() {

            @SneakyThrows
            @Override
            public String get() {
                Thread.sleep(200);
                return "成功获取客户订单信息";
            }
        });
        String mdCus = uCompletableFuture.get();
        System.out.println("mdCus = " + mdCus);
        String mdOrder = stringCompletableFuture.get();
        System.out.println("mdOrder = " + mdOrder);
        long end = System.currentTimeMillis();
        System.out.println("一共耗时" + (end - start));
    }
}
