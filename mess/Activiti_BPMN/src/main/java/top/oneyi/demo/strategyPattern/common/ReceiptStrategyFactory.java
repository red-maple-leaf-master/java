package top.oneyi.demo.strategyPattern.common;


import top.oneyi.demo.strategyPattern.api.IReceiptHandleStrategy;
import top.oneyi.demo.strategyPattern.api.Receipt;
import top.oneyi.demo.strategyPattern.entity.Mt1101ReceiptHandleStrategy;
import top.oneyi.demo.strategyPattern.entity.Mt2101ReceiptHandleStrategy;
import top.oneyi.demo.strategyPattern.entity.Mt8104ReceiptHandleStrategy;
import top.oneyi.demo.strategyPattern.entity.Mt9999ReceiptHandleStrategy;

import java.util.HashMap;
import java.util.Map;

/**
 * 策略工厂
 *
 * @author oneyi
 * @date 2023/5/6
 */
public class ReceiptStrategyFactory {

    private static Map<String, IReceiptHandleStrategy> receiptHandleStrategyMap;

    public ReceiptStrategyFactory() {
        receiptHandleStrategyMap = new HashMap<>();
/*        receiptHandleStrategyMap.put("Mt110",new Mt1101ReceiptHandleStrategy());
        receiptHandleStrategyMap.put("Mt210",new Mt2101ReceiptHandleStrategy());
        receiptHandleStrategyMap.put("Mt810",new Mt8104ReceiptHandleStrategy());
        receiptHandleStrategyMap.put("Mt999",new Mt9999ReceiptHandleStrategy());*/
        receiptHandleStrategyMap.put("Mt001", receipt -> {
            System.out.println("解析报文MT001:" + receipt.getMessage());
        });
        receiptHandleStrategyMap.put("Mt002", receipt -> {
            System.out.println("解析报文MT002:" + receipt.getMessage());
        });
    }

    public IReceiptHandleStrategy getIReciptHandleStrategyImpl(String type) {
        return receiptHandleStrategyMap.get(type);
    }
}
