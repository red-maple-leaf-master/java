package top.oneyi.pojo.po;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.persistence.Id;

@Data
public class WmOmNoticeI {
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

    private String sysOrgCode;
    /**
     * 所属公司
     */

    private String sysCompanyCode;
    /**
     * 出货通知ID
     */

    private String omNoticeId;

    /**
     * 客户订单号
     */
    private String imCusCode;
    private String omBeizhu;

    /**
     * 出货商品
     */
    private String goodsId;

    private String goodsName;
    //	@Excel(name="其他系统ID")
    private String otherId;

    /**
     * 出货数量
     */
    private String goodsQua;
    /**
     * 出货单位
     */
    private String goodsUnit;

    private java.util.Date goodsProData;
    /**
     * 批次
     */

    private String goodsBatch;
    /**
     * 出货仓位
     */

    private String binOm;
    /**
     * 已出货数量
     */
    private String goodsQuaok;
    /**
     * 预约出货时间
     */
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private String delvData;
    /**
     * 客户
     */

    private String cusCode;
    /**
     * 客户名称
     */

    private String cusName;
    /**
     * 出库影响的库存id
     */
    private String goodsText;
    /**
     * 波次号
     */

    private String waveId;
    /**
     * 状态
     */

    private String omSta;
    /**
     * 基本单位
     */

    private String baseUnit;
    /**
     * 基本单位数量
     */

    private String baseGoodscount;
    /**
     * 下架计划生成
     */

    private String planSta;
    /**
     * 库存出库数量
     */
    private String binId;
    /**
     * 产品属性
     */
    private String chpShuXing;
    /**
     * 商品条码
     */
    private String barCode;

    private String sku;
    /**
     * 销售数量
     */
    private int saleAmount;
    /**
     * 销售单价
     */
    private int salePrice;
    /**
     * 储位
     */
    private String storeArea;
    /**
     * 任务确认状态  0.未确认  1.已确认  2.已下架  3.已拣货  4.已复核  5.已回单  6.已删除
     */
    private String status;
    /**
     * 在库货物编码
     */
    private String storeNum;

    /**
     * 在库货物 id
     */
    private String storeId;

    /**
     * 是否下架
     */
    private String shelveStatus;
    /**
     * 是否拣货
     */
    private String jhStatus;
    /**
     * 是否复核
     */
    private String fhStatus;
    /**
     * 收货人
     */
    private String delvMember;
    /**
     * 最大数量
     */
    private String maxNum;

    private int isShow;
    /**
     * 商品重量
     */
    private String zhlKg;
    /**
     * 托盘编号
     */
    private String palletCode;
    /**
     * 出库最大重量
     */
    private String totalRkWeight;
    /**
     * 装货费
     */
    private String handChargesFee;

    /**
     * 出库申请初始重量
     */
    private String initWeight;
    /**
     * 出库申请原始数量
     */
    private String initNum;
    /**
     * 图片地址
     */
    private String filesAddress;
    /**
     * 操作名字
     */
    private String operationName;
}
