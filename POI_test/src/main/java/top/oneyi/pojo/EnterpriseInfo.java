package top.oneyi.pojo;


import lombok.Data;

import java.util.Date;


/**
 * 【请填写功能名称】对象 enterprise_info
 *
 * @author ruoyi
 * @date 2023-06-06
 */
@Data
public class EnterpriseInfo {

    private static final long serialVersionUID = 1L;

    /**
     *
     */
    private Long id;
    /**
     * 企业名称
     */
    private String enterpriseName;
    /**
     * 企业法人
     */
    private String enterpriseLegalPerson;
    /**
     * 委托代理人
     */
    private String entrustedAgent;
    /**
     * 委托代理人联系方式
     */
    private String entrustedContact;
    /**
     * 企业成立时间
     */
    private Date establishedTime;
    /**
     * 纳税人识别号
     */
    private String taxpayerNum;
    /**
     * 开户银行
     */
    private String depositBank;
    /**
     * 银行账户
     */
    private String bankAccount;
    /**
     * 邮寄地址
     */
    private String mailingAddress;
    /**
     * 接收人
     */
    private String receiver;
    /**
     * 接收人联系方式
     */
    private String receiverContact;

    /*营业执照*/
    private String businessLicense;

    /*法人身份证*/
    private String identityCard;

    /*住所地*/
    private String domicile;

    /*乙方联系方式*/
    private String contact;

    /*银行户名*/
    private String accountName;
}
