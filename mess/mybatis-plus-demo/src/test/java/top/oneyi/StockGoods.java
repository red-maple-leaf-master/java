package top.oneyi;

import lombok.Data;

import java.math.BigDecimal;


@Data
public class StockGoods {
    /**
     * 最大重量
     */
    private BigDecimal totalWeight;
    /**
     * 最大数量
     */
    private BigDecimal totalNum;
    /**
     * 可用数量
     */
    private BigDecimal sgAvailableStockNum;
    /**
     * 可用重量
     */
    private BigDecimal sgAvailableStockWeight;

    public void setSgAvailableStockWeight(BigDecimal sgAvailableStockWeight,BigDecimal unit) {
        this.sgAvailableStockWeight = sgAvailableStockWeight;
        this.setSgAvailableStockNum(sgAvailableStockWeight.divide(unit,0,BigDecimal.ROUND_UP));
    }
}
