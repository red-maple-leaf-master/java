package com.example.demo.pojo;


import lombok.Data;


/**
 * 【请填写功能名称】对象 enterprise_base_info
 *
 * @author ruoyi
 * @date 2023-02-03
 */
@Data

public class EnterpriseBaseInfo {

    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */

    private Long id;
    /**
     * 企业名称
     */
    private String enterpriseName;
    /**
     * 企业编码
     */
    private String enterpriseCode;
    /**
     * 社会信用代码
     */
    private String creditCode;
    /**
     * 客户来源
     */
    private String customerSource;
    /**
     * 行业类型
     */
    private String industryType;
    /**
     * 企业成立时间
     */
    private String establishmentTime;
    /**
     * 进出口许可证
     */
    private String imAndExLicense;
    /**
     * 注册资金币种
     */
    private String registeredCapitalCurrency;
    /**
     * 工商注册资金
     */
    private String registeredCapital;
    /**
     * 所有制
     */
    private String ownership;
    /**
     * 实际经营地址省份码值
     */
    private String actualBusinessAddressProvince;
    /**
     * 实际经营地址市码值
     */
    private String actualBusinessAddressCity;
    /**
     * 实际经营地址区码值
     */
    private String actualBusinessAddressArea;
    /**
     * 实际经营地址其它信息（街道、门牌号等）
     */
    private String actualBusinessAddressDetails;
    /**
     * 注册地址省份码值
     */
    private String businessRegisterAddressProvince;
    /**
     * 注册地址市码值
     */
    private String businessRegisterAddressCity;
    /**
     * 注册地址区码值
     */
    private String businessRegisterAddressArea;
    /**
     * 注册地址其它信息（街道、门牌号等）
     */
    private String businessRegisterAddressDetails;
    /**
     * 员工人数
     */
    private String employeeNum;
    /**
     * 经营范围
     */
    private String businessScope;
    /**
     * 是否上市公司  0:未上市    1：已上市
     */
    private String listStatus;
    /**
     * 营业执照有效期
     */
    private String businessLicenseValidityPeriod;
    /**
     * 企业类型
     */
    private String enterpriseType;
    /**
     * 法定代表人姓名
     */
    private String legalPersonName;
    /**
     * 法人性别  1：男 2：女  3：其他
     */
    private String legalPersonSex;
    /**
     * 法人证件类型
     */
    private String legalPersonCertificateType;
    /**
     * 法人证件号码
     */
    private String legalPersonCertificateCode;
    /**
     * 法人手机号码
     */
    private String legalPersonPhone;
    /**
     * 法人电子邮件
     */
    private String legalPersonMail;
    /**
     * 法人住所地
     */
    private String legalPersonAddress;
    /**
     * 企业附件
     */
    private String enterpriseFileList;
    /**
     * 申请附件
     */
    private String applyFileList;
    /**
     * 授信额度
     */
    private String creditLine;


}
