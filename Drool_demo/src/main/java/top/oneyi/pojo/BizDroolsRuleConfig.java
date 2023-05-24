package top.oneyi.pojo;

import lombok.Data;

@Data
public class BizDroolsRuleConfig {

    private String id;
    //'规则编码'
    private String ruleCode;
    //'规则名称'
    private String ruleName;
    // '保证金范围最小值'
    private String min;
    // '保证金范围最大值'
    private String max;
    //'固定保费'
    private String fixedFee;
    // 费率(小数)
    private String feeRate;

    private String createBy;

    private String createTime;

    private String updateBy;

    private String update_time;
}
