package top.oneyi.pojo.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author ：oneyi
 * @date ：Created in 2022/10/26 18:42：
 * dto对象 构造传递的对象
 */
@Data
public class WvStockDto implements Serializable {
    /**
     * id
     */
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
    private java.util.Date createTime;

    /**
     * 库存类型
     */
    private String kuctype;
    /**
     * 储位
     */
    private String kuWeiBianMa;
    /**
     * 托盘
     */
    private String binId;
    /**
     * 货主
     */
    private String cusCode;
    /**
     * 货主名称
     */
    private String zhongWenQch;
    /**
     * 商品编码
     */
    private String goodsId;
    private String goodsCode;
    /**
     * 商品数量
     */
    private Long goodsQua;
    /**
     * 商品名称
     */
    private String shpMingCheng;

    /**
     * 保质期
     */
    private String bzhiQi;
    //允收天数
    private String yushoutianshu;

    /**
     * 单位
     */
    private String goodsUnit;

    private String sttSta;

    private String moveSta;

    /*移动日期*/
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd")
    private String lastMove[];
    //最后移动时间范围
    private String lastMoveBegin;
    private String lastMoveEnd;
    //@JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private String goodsProData[];
    //@JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private String goodsProDataBegin;
    //@JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private String goodsProDataEnd;
    //商品数量范围
    private Long goodsQuaBegin;
    private Long goodsQuaEnd;
    //最后拣货时间时间
    private String lastDateBegin;
    private String lastDateEnd;
}
