package top.oneyi.pojo.po;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.persistence.Id;


/**
 * @Title: Entity
 * @Description: 出货通知抬头
 * @Table(name = "wm_om_notice_h", schema = "")
 */
@Data
public class WmOmNoticeH {
    /**
     * 主键
     */
    @Id
    private String id;
    /**
     * 创建人名称
     */
    private String createName;
    /**
     * 创建人登录名称
     */

    private String createBy;
    /**
     * 创建日期
     */
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private java.util.Date createDate;
    /**
     * 更新人名称
     */

    private String updateName;
    /**
     * 更新人登录名称
     */

    private String updateBy;
    /**
     * 更新日期
     */
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private java.util.Date updateDate;
    /**
     * 所属部门
     */
    private String readonly;

    private String wherecon;

    private String sysOrgCode;
    /**
     * 所属公司
     */
    private String orderTypeCode;

    private String sysCompanyCode;
    /**
     * 客户编码
     */
    private String cusCode;
    /**
     * 客户订单号
     */
    private String imCusCode;
    /**
     * 要求交货时间
     */
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private java.util.Date delvData;
    /**
     * 收货人
     */
    private String delvMember;
    /**
     * 收货人电话
     */
    private String delvMobile;
    /**
     * 收货人地址
     */
    private String delvAddr;
    /**
     * 承运人
     */
    private String reMember;
    /**
     * 承运人电话
     */
    private String reMobile;
    /**
     * 承运人车号
     */
    private String reCarno;
    /**
     * 发货月台
     */
    private String omPlatNo;
    /**
     * 备注
     */
    private String omBeizhu;
    /**
     * 状态
     */
    private String omSta;
    /**
     * 出货单号
     */
    private String omNoticeId;
    /**
     * 附件
     */
    private String fuJian;
    /**
     * 三方客户
     */
    private String ocusCode;
    /**
     * "三方客户名称
     */
    private String ocusName;

    private String printStatus;
    /**
     * 对接单据类型
     */
    private String piClass;
    /**
     * 账套
     */
    private String piMaster;
    /**
     * 送货方式
     */
    private String delvMethod;

    private String storeCode;
    /**
     * 拣货人
     */
    private String jhUser;
    /**
     * "复核人")
     */
    private String fhUser;
    /**
     * 出库状态 1.待确认  2.待下架  3.待拣货  4.待复核  5.已出库  6.已删除
     */
    private String status;
    /**
     * 销售时间
     */
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private java.util.Date saleData;
    /**
     * 收货人地址
     */
    private String shrAddress;
    /**
     * 储位
     */
    private String storeArea;
    /**
     * 出货仓位
     */
    private String binOm;
    /**
     * 驾驶证
     */
    private String driverLicense;
    /**
     * 行驶证
     */
    private String departureLicense;
    /**
     * 结算单证
     */
    private String settlement;
    /**
     * 出库日期
     */
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private java.util.Date outboundDate;
    //	是否需要装货费
    private String isHandCharges;
    /**
     * 操作名字
     */
    private String operationName;

    /*    *//**托盘编号*//*
    private String palletCode;
    *//**托盘名称*//*
    private String tinName;*/
}
