package top.oneyi.demo.strategyPattern.common;

import top.oneyi.demo.strategyPattern.api.IReceiptHandleStrategy;
import top.oneyi.demo.strategyPattern.api.Receipt;

/**
 * 上下文类  持有策略接口
 *
 * @author oneyi
 * @date 2023/5/6
 */
public class ReceiptStrategyContext {

    private IReceiptHandleStrategy iReceiptHandleStrategy;

    /**
     * 设置策略接口
     *
     * @param iReceiptHandleStrategy
     */
    public void setIReceiptHandleStrategy(IReceiptHandleStrategy iReceiptHandleStrategy) {
        this.iReceiptHandleStrategy = iReceiptHandleStrategy;
    }

    public void handleReceipt(Receipt receipt) {
        if (iReceiptHandleStrategy != null) {
            iReceiptHandleStrategy.handleReceipt(receipt);
        }
    }
}
