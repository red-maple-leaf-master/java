package top.oneyi.pojo.vo;

import lombok.Data;

import javax.persistence.Table;

/**
 *库存查询
 */
@Data
@Table(name = "wv_stock", schema = "")
public class WvStockVo{
	/**id*/
	private String id;
	/**储位*/
	private String kuWeiBianMa;
	/**托盘*/
	private String binId;
	/**货主*/
	private String cusCode;
	/**货主名称*/
	private String zhongWenQch;
	/**商品编码*/
	private String goodsId;
	private String goodsCode;
	/**商品名称*/
	private String shpMingCheng;
	/**商品数量*/
	private Long goodsQua;
	/**单位*/
	private String baseUnit;
	/**生产日期*/
	private String goodsProData;
	/**保质期*/
	private String bzhiQi;
	/**库存类型*/
	private String kuctype;
	/**到期日*/
	private String dqr;
	/**库位类型*/
	private String kuWeiLeiXing;
	/**规格*/
	private String shpGuiGe;

}
