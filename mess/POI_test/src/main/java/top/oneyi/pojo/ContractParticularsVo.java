package top.oneyi.pojo;


import lombok.Data;

/**
 * 合同详情
 *
 * @author oneyi
 * @date 2023/6/6
 */

@Data
public class ContractParticularsVo {
    /**
     *
     */

    private Long id;
    /**
     * 贷款编码
     */
    private String loanCode;
    /**
     * 企业编码
     */
    private String enterpriseCode;

    /**
     * 企业名称
     */
    private String enterpriseName;
    /**
     * 产品名称
     */
    private String productName;

    /**
     * 借款金额
     */
    private String loanAmount;
    /**
     * 大写借款金额
     */
    private String capitalLoanAmount;
    /**
     * 期限类型
     */
    private String termType;
    /**
     * 期限
     */
    private String timeLimit;
    /**
     * 年利率
     */
    private String annualInterestRate;
    /**
     * 月利率
     */
    private String monthInterestRate;
    /**
     * 还款方式
     */
    private String repaymentMode;

    /**
     * 违约利率
     */
    private String violateInterestRate;
    /**
     * 手续费率
     */
    private String procedureInterestRate;
    /**
     * 甲方银行户名
     */
    private String partyBankAccountName;
    /**
     * 甲方银行账号
     */
    private String partyBankAccount;
    /**
     * 开户行
     */
    private String openingBank;
    /**
     * 状态  0 未签订 1 已签订
     */
    private String status;
    /**
     * 合同地址
     */
    private String contractImageUrl;
    /**
     * 客户
     */
    private EnterpriseBaseInfo enterpriseBaseInfo;
    /**
     * 借款主体
     */
    private String loanSubject;

    /**
     * 借款用途
     */
    private String loanPurpose;
    /**
     * 社会信用代码
     */
    private String creditCode;
    /**
     * 工商注册资金
     */
    private String registeredCapital;
}
