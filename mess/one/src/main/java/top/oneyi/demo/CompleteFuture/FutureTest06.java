package top.oneyi.demo.CompleteFuture;

import java.util.concurrent.CompletableFuture;

/**
 *whenComplete主要用于注入任务完成时的回调通知逻辑。
 * 这个解决了传统future在任务完成时，无法主动发起通知的问题。
 * 前置任务会将计算结果或者抛出的异常作为入参传递给回调通知函数
 *
 * 还有一个 handle 跟这个类似 不过 handle 有返回值
 * @author oneyi
 * @date 2023/2/6
 */

public class FutureTest06 {
    public static void main(String[] args) {
        CompletableFuture<Integer> integerCompletableFuture = CompletableFuture.supplyAsync(() -> {
            System.out.println("运算之后结果为  1 ");
            return 1;
        });
        // w为前面的传递过来的结果,,e为异常对象 不为空就是有异常
        CompletableFuture<Integer> wwaa = integerCompletableFuture.whenComplete((w, e) -> {
            if (e != null) {
                System.out.println("失败");
            } else {
                System.out.println("结果为:" + w + 1);
            }
        });
        System.out.println("wwaa.join() = " + wwaa.join());
    }
}
