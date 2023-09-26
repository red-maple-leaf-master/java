package top.one.easyjdbc.entity;


import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 实体类
 *
 * @author Blade
 * @since 2021-07-23
 */
@Data
public class Goods implements Serializable {

	private static final long serialVersionUID = 1L;


	private Integer id;

	/**
	 * 商品编号
	 */
	private String goodsNum;
	/**
	 * 商品名称
	 */
	private String goodsName;
	/**
	 * 商品分类编号
	 */
	private String goodsClassificationNum;
	/**
	 * 商品规格
	 */
	private String goodsSpec;
	/**
	 * 商品重量/单个
	 */
	private Double goodsWeight;
	/**
	 * 商品拥有人
	 */
	private String goodsUser;
	/**
	 * 创建时间
	 */
	private Date createTime;
	/**
	 * 更新时间
	 */
	private Date updateTime;
	/**
	 * 计重单位
	 */
	private String goodsUnit;
	/**
	 * 商品颜色
	 */
	private String goodsColor;
	/**
	 * 商品材质
	 */
	private String goodsMaterial;

	/**
	 * 商品审核时间
	 */
	private Date goodsCheckTime;

	/**
	 * 商品审核状态
	 */
	private Integer goodsCheckState;

	/**
	 * 商品自定义
	 */
	private String goodsCustom;
	/**
	 * 商品自定义
	 */
	private String goodsImportingCountry;
	/**
	 * 生产商
	 */
	private String goodsManufacturer;
	/**
	 * 尺寸单位
	 */
	private String specUnit;
	/**
	 * 重量单位
	 */

	private String weightUnit;

	private String goodsEnclosureAccessAddress;
	/**
	 * 场地
	 */
	private String site;
	/**
	 * 等级规格补充
	 */
	private String gradeSpecificationMark;
	/**
	 * 等级规格
	 */
	private String gradeSpecification;

}
