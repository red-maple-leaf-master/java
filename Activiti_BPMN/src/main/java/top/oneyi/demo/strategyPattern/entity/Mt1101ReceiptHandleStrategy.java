package top.oneyi.demo.strategyPattern.entity;

import top.oneyi.demo.strategyPattern.api.IReceiptHandleStrategy;
import top.oneyi.demo.strategyPattern.api.Receipt;

public class Mt1101ReceiptHandleStrategy implements IReceiptHandleStrategy {

    @Override
    public void handleReceipt(Receipt receipt) {
        System.out.println("解析报文MT1101:" + receipt.getMessage());
    }

}