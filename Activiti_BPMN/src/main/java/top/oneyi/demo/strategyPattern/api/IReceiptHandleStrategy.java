package top.oneyi.demo.strategyPattern.api;
/**
 * 回执处理策略接口
 * @author oneyi
 * @date 2023/5/6
 */
public interface IReceiptHandleStrategy {
    /**
     * 回执策略接口
     * @param receipt
     */
    void handleReceipt(Receipt receipt);
}
