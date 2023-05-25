package top.oneyi.pojo;

import lombok.Data;

@Data
public class GradingLevel {

    private Long id;
    /**
     * 授信等级
     */
    private String CreditLevel;
    /**
     * 分值区间
     */
    private String scoreInterval;
}
