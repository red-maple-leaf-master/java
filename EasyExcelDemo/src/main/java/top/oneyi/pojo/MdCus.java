package top.oneyi.pojo;


import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @Title: Entity
 * @Description: 客户
 * @author oneyi
 * @date Created in 2022/10/20 10:00：    md_cus
 * @version V1.0   
 *
 */
@Data
@Table(name = "md_cus")
public class MdCus implements java.io.Serializable {
	/**主键*/
	@Id
	@GeneratedValue(generator="UUID")
	private java.lang.String id;
	/**创建人名称*/
	private java.lang.String createName;
	/**创建人登录名称*/
	private java.lang.String createBy;
	/**创建日期*/
	private java.util.Date createDate;
	/**更新人名称*/
	private java.lang.String updateName;
	/**更新人登录名称*/
	private java.lang.String updateBy;
	/**更新日期*/
	private java.util.Date updateDate;
	/**所属部门*/
	private java.lang.String sysOrgCode;
	/**所属公司*/
	private java.lang.String sysCompanyCode;
	/**中文全称*/
	private java.lang.String zhongWenQch;
	/**助记码*/
	private java.lang.String zhuJiMa;
	/**客户简称*/
	private java.lang.String keHuJianCheng;
	/**客户编码*/
	private java.lang.String keHuBianMa;
	/**客户英文名称*/
	private java.lang.String keHuYingWen;
	/**曾用企业代码*/
	private java.lang.String zengYongQi;
	/**曾用企业名称*/
	private java.lang.String zengYongQiYe;
	/**客户意向书*/
	private java.lang.String keHuZhuangTai;
	/**企业属性*/
 	private java.lang.String xingYeFenLei;
	/**客户等级*/
	private java.lang.String keHuDengJi;
	/**所属行业*/
	private java.lang.String suoShuXingYe;
	/**首签日期*/
	private java.util.Date shouQianRiQi;
	/**终止合作时间*/
	private java.util.Date zhongZhiHeShiJian;
	/**申请时间*/
	private java.util.Date shenQingShiJian;
	/**客户属性*/
	private java.lang.String keHuShuXing;
	/**归属组织代码*/
	private java.lang.String guiShuZuZh;
	/**归属省份代码*/
	private java.lang.String guiShuSheng;
	/**归属市代码*/
	private java.lang.String guiShuShiDai;
	/**归属县区代码*/
	private java.lang.String guiShu;
	/**地址*/
	private java.lang.String diZhi;
	/**邮政编码*/
	private java.lang.String youZhengBianMa;
	/**主联系人*/
	private java.lang.String zhuLianXiRen;
	/**电话*/
	private java.lang.String dianHua;
	/**主联系人*/
	private java.lang.String zhuLianXiRen1;
	/**电话*/
	private java.lang.String dianHua1;
	/**手机*/
	private java.lang.String shouJi;
	/**传真*/
	private java.lang.String chuanZhen;
	/**Email地址*/
	private java.lang.String emaildiZhi;
	/**网页地址*/
	private java.lang.String wangYeDiZhi;
	/**法人代表*/
	private java.lang.String faRenDaiBiao;
	/**法人身份证号*/
	private java.lang.String faRenShenFen;
	/**注册资金万元*/
	private java.lang.String zhuCeZiJin;
	/**币别*/
	private java.lang.String biBie;
	/**营业执照号*/
	private java.lang.String yingYeZhiZhao;
	/**税务登记证号*/
	private java.lang.String shuiWuDeng;
	/**组织机构代码证*/
	private java.lang.String zuZhiJiGou;
	/**道路运输经营许可证*/
	private java.lang.String daoLuYunShu;
	/**主营业务*/
	private java.lang.String zhuYingYeWu;
	/**合作意向*/
	private java.lang.String heYiXiang;
	/**批准机关*/
	private java.lang.String piZhunJiGuan;
	/**批准文号*/
	private java.lang.String piZhunWenHao;
	/**注册日期*/
	private java.util.Date zhuCeRiQi;
	/**备注*/
	private java.lang.String beiZhu;

}
