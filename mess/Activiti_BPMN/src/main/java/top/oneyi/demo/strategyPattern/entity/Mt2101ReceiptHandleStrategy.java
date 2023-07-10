package top.oneyi.demo.strategyPattern.entity;

import top.oneyi.demo.strategyPattern.api.IReceiptHandleStrategy;
import top.oneyi.demo.strategyPattern.api.Receipt;

/**
 * 策略接口实现类
 *
 * @author oneyi
 * @date 2023/5/6
 */
public class Mt2101ReceiptHandleStrategy implements IReceiptHandleStrategy {
    @Override
    public void handleReceipt(Receipt receipt) {
        System.out.println("解析报文MT2101:" + receipt.getMessage());
    }
}
