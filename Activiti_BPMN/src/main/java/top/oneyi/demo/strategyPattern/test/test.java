package top.oneyi.demo.strategyPattern.test;

import top.oneyi.demo.strategyPattern.api.Receipt;
import top.oneyi.demo.strategyPattern.common.ReceiptStrategyContext;
import top.oneyi.demo.strategyPattern.common.ReceiptStrategyFactory;
import top.oneyi.demo.strategyPattern.entity.Mt8104ReceiptHandleStrategy;

import java.util.ArrayList;
import java.util.List;

public class test {
    public static void main(String[] args) {
        List<Receipt> receiptList = new ArrayList<>();
        receiptList.add(new Receipt("我是MT2101回执喔","Mt210"));
        receiptList.add(new Receipt("我是MT1101回执喔","Mt110"));
        receiptList.add(new Receipt("我是MT8104回执喔","MT8104"));
        receiptList.add(new Receipt("我是MT9999回执喔","MT9999"));
        receiptList.add(new Receipt("我是MT001回执喔","Mt001"));
        receiptList.add(new Receipt("我是MT002回执喔","Mt002"));

        ReceiptStrategyContext receiptStrategyContext = new ReceiptStrategyContext();
        for (Receipt receipt : receiptList) {
           /* receiptStrategyContext.setIReceiptHandleStrategy(new ReceiptStrategyFactory().getIReciptHandleStrategyImpl(receipt.getMessage()));
            receiptStrategyContext.handleReceipt(receipt);*/
            receiptStrategyContext.setIReceiptHandleStrategy(new Mt8104ReceiptHandleStrategy());
            receiptStrategyContext.handleReceipt(receipt);
        }
    }
}
