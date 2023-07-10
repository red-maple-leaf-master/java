package top.oneyi.pojo.vo;


import lombok.Data;
import top.oneyi.pojo.po.WmOmNoticeI;

@Data
public class WmOmNoticeIVo extends WmOmNoticeI {
    private String shpMingCheng;
    /**
     * 客户编码
     */
    private String cusCode;
    /**
     * 客户名称
     */
    private String cusName;
    private String qmOkQuat;
    /**
     * 储位名称
     */
    private String locationName;
    ;
    /**
     * 仓库名称1
     */
    private String storeName;
    /**
     * 仓库编码
     */
    private String storeCode;
    /**
     * 储位编码
     */
    private String kuWeiBianMa;
    /**
     * 最大可用库存
     */
    private String maxNum;
    /**
     * 仓库名称2
     */
    private String warehouseName;
    /**
     * 可用库存
     */
    private String availableStock;
    /**
     * 商品编码
     */
    private String shpBianMa;
    /**
     * 商品数量
     */
    private String goodsCount;
    /**
     * 商品净重
     */
    private String zhlKg;
    /**
     * 托盘名称
     */
    private String tinName;
    /**
     * 设置申请的出库库存(出库申请的查看详情)
     */
    private String availableWeight;


}
