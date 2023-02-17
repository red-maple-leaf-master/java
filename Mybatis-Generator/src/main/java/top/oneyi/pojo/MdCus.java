package top.oneyi.pojo;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import org.apache.ibatis.type.Alias;

/**
 * md_cus
 */
@Table(name = "md_cus")
public class MdCus {
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
    private Date createDate;

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
    private Date updateDate;

    /**
     * 所属部门
     */
    private String sysOrgCode;

    /**
     * 所属公司
     */
    private String sysCompanyCode;

    /**
     * 中文全称
     */
    private String zhongWenQch;

    /**
     * 助记码
     */
    private String zhuJiMa;

    /**
     * 客户简称
     */
    private String keHuJianCheng;

    /**
     * 客户编码
     */
    private String keHuBianMa;

    /**
     * 客户英文名称
     */
    private String keHuYingWen;

    /**
     * 曾用企业代码
     */
    private String zengYongQi;

    /**
     * 曾用企业名称
     */
    private String zengYongQiYe;

    /**
     * 客户状态
     */
    private String keHuZhuangTai;

    /**
     * 企业属性
     */
    private String xingYeFenLei;

    /**
     * 客户等级
     */
    private String keHuDengJi;

    /**
     * 所属行业
     */
    private String suoShuXingYe;

    /**
     * 首签日期
     */
    private Date shouQianRiQi;

    /**
     * 终止合作时间
     */
    private Date zhongZhiHeShiJian;

    /**
     * 申请时间
     */
    private Date shenQingShiJian;

    /**
     * 客户属性
     */
    private String keHuShuXing;

    /**
     * 归属组织代码
     */
    private String guiShuZuZh;

    /**
     * 归属省份代码
     */
    private String guiShuSheng;

    /**
     * 归属市代码
     */
    private String guiShuShiDai;

    /**
     * 归属县区代码
     */
    private String guiShu;

    /**
     * 地址
     */
    private String diZhi;

    /**
     * 邮政编码
     */
    private String youZhengBianMa;

    /**
     * 主联系人
     */
    private String zhuLianXiRen;

    /**
     * 电话
     */
    private String dianHua;

    /**
     * 手机
     */
    private String shouJi;

    /**
     * 传真
     */
    private String chuanZhen;

    /**
     * Email地址
     */
    private String emaildiZhi;

    /**
     * 网页地址
     */
    private String wangYeDiZhi;

    /**
     * 法人代表
     */
    private String faRenDaiBiao;

    /**
     * 法人身份证号
     */
    private String faRenShenFen;

    /**
     * 注册资金万元
     */
    private String zhuCeZiJin;

    /**
     * 币别
     */
    private String biBie;

    /**
     * 营业执照号
     */
    private String yingYeZhiZhao;

    /**
     * 税务登记证号
     */
    private String shuiWuDeng;

    /**
     * 组织机构代码证
     */
    private String zuZhiJiGou;

    /**
     * 道路运输经营许可证
     */
    private String daoLuYunShu;

    /**
     * 主营业务
     */
    private String zhuYingYeWu;

    /**
     * 合作意向
     */
    private String heYiXiang;

    /**
     * 批准机关
     */
    private String piZhunJiGuan;

    /**
     * 批准文号
     */
    private String piZhunWenHao;

    /**
     * 注册日期
     */
    private Date zhuCeRiQi;

    /**
     * 备注
     */
    private String beiZhu;

    /**
     * 联系人1
     */
    private String zhuLianXiRen1;

    /**
     * 电话1
     */
    private String dianHua1;

    /**
     * 附件地址
     */
    private String fuJian;

    /**
     * 客户类型
     */
    private String keHuLeiXing;

    /**
     * 计费周期
     */
    private String billingCycle;

    /**
     * 仓储计费方式
     */
    private String storageBillWay;

    /**
     * 保底天数
     */
    private String floorDays;

    /**
     * 保底重量
     */
    private String floorWeights;

    /**
     * 仓储费
     */
    private String storageCharge;

    /**
     * 计费数量
     */
    private String billingNumber;

    /**
     * 租赁储位面积
     */
    private String binNumber;

    /**
     * 租赁托盘数量
     */
    private String stockNumber;

    /**
     * 计费重量数量
     */
    private String billingWeight;

    /**
     * 费用周期
     */
    private String chargeCycle;

    /**
     * 费用折扣
     */
    private String chargeDiscount;

    /**
     * 装货费计费方式
     */
    private String handChargeBillWay;

    /**
     * 装货费
     */
    private String handCharges;

    /**
     * 卸货费
     */
    private String landCharges;

    /**
     * 数量
     */
    private String number;

    /**
     * 重量
     */
    private String weight;

    /**
     * 合同有效期
     */
    private String contractLife;

    /**
     * 卸货费计费方式
     */
    private String landChargeBillWay;

    /**
     * 有效期
     */
    private String expirDate;

    /**
     * 仓储费模板
     */
    private String storageTemplate;

    /**
     * 装货费模板
     */
    private String handChargeTemplate;

    /**
     * 卸货费模板
     */
    private String landChargeTemplate;

    /**
     * 主键
     * @return id 主键
     */
    public String getId() {
        return id;
    }

    /**
     * 主键
     * @param id 主键
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * 创建人名称
     * @return create_name 创建人名称
     */
    public String getCreateName() {
        return createName;
    }

    /**
     * 创建人名称
     * @param createName 创建人名称
     */
    public void setCreateName(String createName) {
        this.createName = createName;
    }

    /**
     * 创建人登录名称
     * @return create_by 创建人登录名称
     */
    public String getCreateBy() {
        return createBy;
    }

    /**
     * 创建人登录名称
     * @param createBy 创建人登录名称
     */
    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    /**
     * 创建日期
     * @return create_date 创建日期
     */
    public Date getCreateDate() {
        return createDate;
    }

    /**
     * 创建日期
     * @param createDate 创建日期
     */
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    /**
     * 更新人名称
     * @return update_name 更新人名称
     */
    public String getUpdateName() {
        return updateName;
    }

    /**
     * 更新人名称
     * @param updateName 更新人名称
     */
    public void setUpdateName(String updateName) {
        this.updateName = updateName;
    }

    /**
     * 更新人登录名称
     * @return update_by 更新人登录名称
     */
    public String getUpdateBy() {
        return updateBy;
    }

    /**
     * 更新人登录名称
     * @param updateBy 更新人登录名称
     */
    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }

    /**
     * 更新日期
     * @return update_date 更新日期
     */
    public Date getUpdateDate() {
        return updateDate;
    }

    /**
     * 更新日期
     * @param updateDate 更新日期
     */
    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    /**
     * 所属部门
     * @return sys_org_code 所属部门
     */
    public String getSysOrgCode() {
        return sysOrgCode;
    }

    /**
     * 所属部门
     * @param sysOrgCode 所属部门
     */
    public void setSysOrgCode(String sysOrgCode) {
        this.sysOrgCode = sysOrgCode;
    }

    /**
     * 所属公司
     * @return sys_company_code 所属公司
     */
    public String getSysCompanyCode() {
        return sysCompanyCode;
    }

    /**
     * 所属公司
     * @param sysCompanyCode 所属公司
     */
    public void setSysCompanyCode(String sysCompanyCode) {
        this.sysCompanyCode = sysCompanyCode;
    }

    /**
     * 中文全称
     * @return zhong_wen_qch 中文全称
     */
    public String getZhongWenQch() {
        return zhongWenQch;
    }

    /**
     * 中文全称
     * @param zhongWenQch 中文全称
     */
    public void setZhongWenQch(String zhongWenQch) {
        this.zhongWenQch = zhongWenQch;
    }

    /**
     * 助记码
     * @return zhu_ji_ma 助记码
     */
    public String getZhuJiMa() {
        return zhuJiMa;
    }

    /**
     * 助记码
     * @param zhuJiMa 助记码
     */
    public void setZhuJiMa(String zhuJiMa) {
        this.zhuJiMa = zhuJiMa;
    }

    /**
     * 客户简称
     * @return ke_hu_jian_cheng 客户简称
     */
    public String getKeHuJianCheng() {
        return keHuJianCheng;
    }

    /**
     * 客户简称
     * @param keHuJianCheng 客户简称
     */
    public void setKeHuJianCheng(String keHuJianCheng) {
        this.keHuJianCheng = keHuJianCheng;
    }

    /**
     * 客户编码
     * @return ke_hu_bian_ma 客户编码
     */
    public String getKeHuBianMa() {
        return keHuBianMa;
    }

    /**
     * 客户编码
     * @param keHuBianMa 客户编码
     */
    public void setKeHuBianMa(String keHuBianMa) {
        this.keHuBianMa = keHuBianMa;
    }

    /**
     * 客户英文名称
     * @return ke_hu_ying_wen 客户英文名称
     */
    public String getKeHuYingWen() {
        return keHuYingWen;
    }

    /**
     * 客户英文名称
     * @param keHuYingWen 客户英文名称
     */
    public void setKeHuYingWen(String keHuYingWen) {
        this.keHuYingWen = keHuYingWen;
    }

    /**
     * 曾用企业代码
     * @return zeng_yong_qi 曾用企业代码
     */
    public String getZengYongQi() {
        return zengYongQi;
    }

    /**
     * 曾用企业代码
     * @param zengYongQi 曾用企业代码
     */
    public void setZengYongQi(String zengYongQi) {
        this.zengYongQi = zengYongQi;
    }

    /**
     * 曾用企业名称
     * @return zeng_yong_qi_ye 曾用企业名称
     */
    public String getZengYongQiYe() {
        return zengYongQiYe;
    }

    /**
     * 曾用企业名称
     * @param zengYongQiYe 曾用企业名称
     */
    public void setZengYongQiYe(String zengYongQiYe) {
        this.zengYongQiYe = zengYongQiYe;
    }

    /**
     * 客户状态
     * @return ke_hu_zhuang_tai 客户状态
     */
    public String getKeHuZhuangTai() {
        return keHuZhuangTai;
    }

    /**
     * 客户状态
     * @param keHuZhuangTai 客户状态
     */
    public void setKeHuZhuangTai(String keHuZhuangTai) {
        this.keHuZhuangTai = keHuZhuangTai;
    }

    /**
     * 企业属性
     * @return xing_ye_fen_lei 企业属性
     */
    public String getXingYeFenLei() {
        return xingYeFenLei;
    }

    /**
     * 企业属性
     * @param xingYeFenLei 企业属性
     */
    public void setXingYeFenLei(String xingYeFenLei) {
        this.xingYeFenLei = xingYeFenLei;
    }

    /**
     * 客户等级
     * @return ke_hu_deng_ji 客户等级
     */
    public String getKeHuDengJi() {
        return keHuDengJi;
    }

    /**
     * 客户等级
     * @param keHuDengJi 客户等级
     */
    public void setKeHuDengJi(String keHuDengJi) {
        this.keHuDengJi = keHuDengJi;
    }

    /**
     * 所属行业
     * @return suo_shu_xing_ye 所属行业
     */
    public String getSuoShuXingYe() {
        return suoShuXingYe;
    }

    /**
     * 所属行业
     * @param suoShuXingYe 所属行业
     */
    public void setSuoShuXingYe(String suoShuXingYe) {
        this.suoShuXingYe = suoShuXingYe;
    }

    /**
     * 首签日期
     * @return shou_qian_ri_qi 首签日期
     */
    public Date getShouQianRiQi() {
        return shouQianRiQi;
    }

    /**
     * 首签日期
     * @param shouQianRiQi 首签日期
     */
    public void setShouQianRiQi(Date shouQianRiQi) {
        this.shouQianRiQi = shouQianRiQi;
    }

    /**
     * 终止合作时间
     * @return zhong_zhi_he_shi_jian 终止合作时间
     */
    public Date getZhongZhiHeShiJian() {
        return zhongZhiHeShiJian;
    }

    /**
     * 终止合作时间
     * @param zhongZhiHeShiJian 终止合作时间
     */
    public void setZhongZhiHeShiJian(Date zhongZhiHeShiJian) {
        this.zhongZhiHeShiJian = zhongZhiHeShiJian;
    }

    /**
     * 申请时间
     * @return shen_qing_shi_jian 申请时间
     */
    public Date getShenQingShiJian() {
        return shenQingShiJian;
    }

    /**
     * 申请时间
     * @param shenQingShiJian 申请时间
     */
    public void setShenQingShiJian(Date shenQingShiJian) {
        this.shenQingShiJian = shenQingShiJian;
    }

    /**
     * 客户属性
     * @return ke_hu_shu_xing 客户属性
     */
    public String getKeHuShuXing() {
        return keHuShuXing;
    }

    /**
     * 客户属性
     * @param keHuShuXing 客户属性
     */
    public void setKeHuShuXing(String keHuShuXing) {
        this.keHuShuXing = keHuShuXing;
    }

    /**
     * 归属组织代码
     * @return gui_shu_zu_zh 归属组织代码
     */
    public String getGuiShuZuZh() {
        return guiShuZuZh;
    }

    /**
     * 归属组织代码
     * @param guiShuZuZh 归属组织代码
     */
    public void setGuiShuZuZh(String guiShuZuZh) {
        this.guiShuZuZh = guiShuZuZh;
    }

    /**
     * 归属省份代码
     * @return gui_shu_sheng 归属省份代码
     */
    public String getGuiShuSheng() {
        return guiShuSheng;
    }

    /**
     * 归属省份代码
     * @param guiShuSheng 归属省份代码
     */
    public void setGuiShuSheng(String guiShuSheng) {
        this.guiShuSheng = guiShuSheng;
    }

    /**
     * 归属市代码
     * @return gui_shu_shi_dai 归属市代码
     */
    public String getGuiShuShiDai() {
        return guiShuShiDai;
    }

    /**
     * 归属市代码
     * @param guiShuShiDai 归属市代码
     */
    public void setGuiShuShiDai(String guiShuShiDai) {
        this.guiShuShiDai = guiShuShiDai;
    }

    /**
     * 归属县区代码
     * @return gui_shu 归属县区代码
     */
    public String getGuiShu() {
        return guiShu;
    }

    /**
     * 归属县区代码
     * @param guiShu 归属县区代码
     */
    public void setGuiShu(String guiShu) {
        this.guiShu = guiShu;
    }

    /**
     * 地址
     * @return di_zhi 地址
     */
    public String getDiZhi() {
        return diZhi;
    }

    /**
     * 地址
     * @param diZhi 地址
     */
    public void setDiZhi(String diZhi) {
        this.diZhi = diZhi;
    }

    /**
     * 邮政编码
     * @return you_zheng_bian_ma 邮政编码
     */
    public String getYouZhengBianMa() {
        return youZhengBianMa;
    }

    /**
     * 邮政编码
     * @param youZhengBianMa 邮政编码
     */
    public void setYouZhengBianMa(String youZhengBianMa) {
        this.youZhengBianMa = youZhengBianMa;
    }

    /**
     * 主联系人
     * @return zhu_lian_xi_ren 主联系人
     */
    public String getZhuLianXiRen() {
        return zhuLianXiRen;
    }

    /**
     * 主联系人
     * @param zhuLianXiRen 主联系人
     */
    public void setZhuLianXiRen(String zhuLianXiRen) {
        this.zhuLianXiRen = zhuLianXiRen;
    }

    /**
     * 电话
     * @return dian_hua 电话
     */
    public String getDianHua() {
        return dianHua;
    }

    /**
     * 电话
     * @param dianHua 电话
     */
    public void setDianHua(String dianHua) {
        this.dianHua = dianHua;
    }

    /**
     * 手机
     * @return shou_ji 手机
     */
    public String getShouJi() {
        return shouJi;
    }

    /**
     * 手机
     * @param shouJi 手机
     */
    public void setShouJi(String shouJi) {
        this.shouJi = shouJi;
    }

    /**
     * 传真
     * @return chuan_zhen 传真
     */
    public String getChuanZhen() {
        return chuanZhen;
    }

    /**
     * 传真
     * @param chuanZhen 传真
     */
    public void setChuanZhen(String chuanZhen) {
        this.chuanZhen = chuanZhen;
    }

    /**
     * Email地址
     * @return Emaildi_zhi Email地址
     */
    public String getEmaildiZhi() {
        return emaildiZhi;
    }

    /**
     * Email地址
     * @param emaildiZhi Email地址
     */
    public void setEmaildiZhi(String emaildiZhi) {
        this.emaildiZhi = emaildiZhi;
    }

    /**
     * 网页地址
     * @return wang_ye_di_zhi 网页地址
     */
    public String getWangYeDiZhi() {
        return wangYeDiZhi;
    }

    /**
     * 网页地址
     * @param wangYeDiZhi 网页地址
     */
    public void setWangYeDiZhi(String wangYeDiZhi) {
        this.wangYeDiZhi = wangYeDiZhi;
    }

    /**
     * 法人代表
     * @return fa_ren_dai_biao 法人代表
     */
    public String getFaRenDaiBiao() {
        return faRenDaiBiao;
    }

    /**
     * 法人代表
     * @param faRenDaiBiao 法人代表
     */
    public void setFaRenDaiBiao(String faRenDaiBiao) {
        this.faRenDaiBiao = faRenDaiBiao;
    }

    /**
     * 法人身份证号
     * @return fa_ren_shen_fen 法人身份证号
     */
    public String getFaRenShenFen() {
        return faRenShenFen;
    }

    /**
     * 法人身份证号
     * @param faRenShenFen 法人身份证号
     */
    public void setFaRenShenFen(String faRenShenFen) {
        this.faRenShenFen = faRenShenFen;
    }

    /**
     * 注册资金万元
     * @return zhu_ce_zi_jin 注册资金万元
     */
    public String getZhuCeZiJin() {
        return zhuCeZiJin;
    }

    /**
     * 注册资金万元
     * @param zhuCeZiJin 注册资金万元
     */
    public void setZhuCeZiJin(String zhuCeZiJin) {
        this.zhuCeZiJin = zhuCeZiJin;
    }

    /**
     * 币别
     * @return bi_bie 币别
     */
    public String getBiBie() {
        return biBie;
    }

    /**
     * 币别
     * @param biBie 币别
     */
    public void setBiBie(String biBie) {
        this.biBie = biBie;
    }

    /**
     * 营业执照号
     * @return ying_ye_zhi_zhao 营业执照号
     */
    public String getYingYeZhiZhao() {
        return yingYeZhiZhao;
    }

    /**
     * 营业执照号
     * @param yingYeZhiZhao 营业执照号
     */
    public void setYingYeZhiZhao(String yingYeZhiZhao) {
        this.yingYeZhiZhao = yingYeZhiZhao;
    }

    /**
     * 税务登记证号
     * @return shui_wu_deng 税务登记证号
     */
    public String getShuiWuDeng() {
        return shuiWuDeng;
    }

    /**
     * 税务登记证号
     * @param shuiWuDeng 税务登记证号
     */
    public void setShuiWuDeng(String shuiWuDeng) {
        this.shuiWuDeng = shuiWuDeng;
    }

    /**
     * 组织机构代码证
     * @return zu_zhi_ji_gou 组织机构代码证
     */
    public String getZuZhiJiGou() {
        return zuZhiJiGou;
    }

    /**
     * 组织机构代码证
     * @param zuZhiJiGou 组织机构代码证
     */
    public void setZuZhiJiGou(String zuZhiJiGou) {
        this.zuZhiJiGou = zuZhiJiGou;
    }

    /**
     * 道路运输经营许可证
     * @return dao_lu_yun_shu 道路运输经营许可证
     */
    public String getDaoLuYunShu() {
        return daoLuYunShu;
    }

    /**
     * 道路运输经营许可证
     * @param daoLuYunShu 道路运输经营许可证
     */
    public void setDaoLuYunShu(String daoLuYunShu) {
        this.daoLuYunShu = daoLuYunShu;
    }

    /**
     * 主营业务
     * @return zhu_ying_ye_wu 主营业务
     */
    public String getZhuYingYeWu() {
        return zhuYingYeWu;
    }

    /**
     * 主营业务
     * @param zhuYingYeWu 主营业务
     */
    public void setZhuYingYeWu(String zhuYingYeWu) {
        this.zhuYingYeWu = zhuYingYeWu;
    }

    /**
     * 合作意向
     * @return he_yi_xiang 合作意向
     */
    public String getHeYiXiang() {
        return heYiXiang;
    }

    /**
     * 合作意向
     * @param heYiXiang 合作意向
     */
    public void setHeYiXiang(String heYiXiang) {
        this.heYiXiang = heYiXiang;
    }

    /**
     * 批准机关
     * @return pi_zhun_ji_guan 批准机关
     */
    public String getPiZhunJiGuan() {
        return piZhunJiGuan;
    }

    /**
     * 批准机关
     * @param piZhunJiGuan 批准机关
     */
    public void setPiZhunJiGuan(String piZhunJiGuan) {
        this.piZhunJiGuan = piZhunJiGuan;
    }

    /**
     * 批准文号
     * @return pi_zhun_wen_hao 批准文号
     */
    public String getPiZhunWenHao() {
        return piZhunWenHao;
    }

    /**
     * 批准文号
     * @param piZhunWenHao 批准文号
     */
    public void setPiZhunWenHao(String piZhunWenHao) {
        this.piZhunWenHao = piZhunWenHao;
    }

    /**
     * 注册日期
     * @return zhu_ce_ri_qi 注册日期
     */
    public Date getZhuCeRiQi() {
        return zhuCeRiQi;
    }

    /**
     * 注册日期
     * @param zhuCeRiQi 注册日期
     */
    public void setZhuCeRiQi(Date zhuCeRiQi) {
        this.zhuCeRiQi = zhuCeRiQi;
    }

    /**
     * 备注
     * @return bei_zhu 备注
     */
    public String getBeiZhu() {
        return beiZhu;
    }

    /**
     * 备注
     * @param beiZhu 备注
     */
    public void setBeiZhu(String beiZhu) {
        this.beiZhu = beiZhu;
    }

    /**
     * 联系人1
     * @return zhu_lian_xi_ren1 联系人1
     */
    public String getZhuLianXiRen1() {
        return zhuLianXiRen1;
    }

    /**
     * 联系人1
     * @param zhuLianXiRen1 联系人1
     */
    public void setZhuLianXiRen1(String zhuLianXiRen1) {
        this.zhuLianXiRen1 = zhuLianXiRen1;
    }

    /**
     * 电话1
     * @return dian_hua1 电话1
     */
    public String getDianHua1() {
        return dianHua1;
    }

    /**
     * 电话1
     * @param dianHua1 电话1
     */
    public void setDianHua1(String dianHua1) {
        this.dianHua1 = dianHua1;
    }

    /**
     * 附件地址
     * @return fu_jian 附件地址
     */
    public String getFuJian() {
        return fuJian;
    }

    /**
     * 附件地址
     * @param fuJian 附件地址
     */
    public void setFuJian(String fuJian) {
        this.fuJian = fuJian;
    }

    /**
     * 客户类型
     * @return ke_hu_lei_xing 客户类型
     */
    public String getKeHuLeiXing() {
        return keHuLeiXing;
    }

    /**
     * 客户类型
     * @param keHuLeiXing 客户类型
     */
    public void setKeHuLeiXing(String keHuLeiXing) {
        this.keHuLeiXing = keHuLeiXing;
    }

    /**
     * 计费周期
     * @return billing_cycle 计费周期
     */
    public String getBillingCycle() {
        return billingCycle;
    }

    /**
     * 计费周期
     * @param billingCycle 计费周期
     */
    public void setBillingCycle(String billingCycle) {
        this.billingCycle = billingCycle;
    }

    /**
     * 仓储计费方式
     * @return storage_bill_way 仓储计费方式
     */
    public String getStorageBillWay() {
        return storageBillWay;
    }

    /**
     * 仓储计费方式
     * @param storageBillWay 仓储计费方式
     */
    public void setStorageBillWay(String storageBillWay) {
        this.storageBillWay = storageBillWay;
    }

    /**
     * 保底天数
     * @return floor_days 保底天数
     */
    public String getFloorDays() {
        return floorDays;
    }

    /**
     * 保底天数
     * @param floorDays 保底天数
     */
    public void setFloorDays(String floorDays) {
        this.floorDays = floorDays;
    }

    /**
     * 保底重量
     * @return floor_weights 保底重量
     */
    public String getFloorWeights() {
        return floorWeights;
    }

    /**
     * 保底重量
     * @param floorWeights 保底重量
     */
    public void setFloorWeights(String floorWeights) {
        this.floorWeights = floorWeights;
    }

    /**
     * 仓储费
     * @return storage_charge 仓储费
     */
    public String getStorageCharge() {
        return storageCharge;
    }

    /**
     * 仓储费
     * @param storageCharge 仓储费
     */
    public void setStorageCharge(String storageCharge) {
        this.storageCharge = storageCharge;
    }

    /**
     * 计费数量
     * @return billing_number 计费数量
     */
    public String getBillingNumber() {
        return billingNumber;
    }

    /**
     * 计费数量
     * @param billingNumber 计费数量
     */
    public void setBillingNumber(String billingNumber) {
        this.billingNumber = billingNumber;
    }

    /**
     * 租赁储位面积
     * @return bin_number 租赁储位面积
     */
    public String getBinNumber() {
        return binNumber;
    }

    /**
     * 租赁储位面积
     * @param binNumber 租赁储位面积
     */
    public void setBinNumber(String binNumber) {
        this.binNumber = binNumber;
    }

    /**
     * 租赁托盘数量
     * @return stock_number 租赁托盘数量
     */
    public String getStockNumber() {
        return stockNumber;
    }

    /**
     * 租赁托盘数量
     * @param stockNumber 租赁托盘数量
     */
    public void setStockNumber(String stockNumber) {
        this.stockNumber = stockNumber;
    }

    /**
     * 计费重量数量
     * @return billing_weight 计费重量数量
     */
    public String getBillingWeight() {
        return billingWeight;
    }

    /**
     * 计费重量数量
     * @param billingWeight 计费重量数量
     */
    public void setBillingWeight(String billingWeight) {
        this.billingWeight = billingWeight;
    }

    /**
     * 费用周期
     * @return charge_cycle 费用周期
     */
    public String getChargeCycle() {
        return chargeCycle;
    }

    /**
     * 费用周期
     * @param chargeCycle 费用周期
     */
    public void setChargeCycle(String chargeCycle) {
        this.chargeCycle = chargeCycle;
    }

    /**
     * 费用折扣
     * @return charge_discount 费用折扣
     */
    public String getChargeDiscount() {
        return chargeDiscount;
    }

    /**
     * 费用折扣
     * @param chargeDiscount 费用折扣
     */
    public void setChargeDiscount(String chargeDiscount) {
        this.chargeDiscount = chargeDiscount;
    }

    /**
     * 装货费计费方式
     * @return hand_charge_bill_way 装货费计费方式
     */
    public String getHandChargeBillWay() {
        return handChargeBillWay;
    }

    /**
     * 装货费计费方式
     * @param handChargeBillWay 装货费计费方式
     */
    public void setHandChargeBillWay(String handChargeBillWay) {
        this.handChargeBillWay = handChargeBillWay;
    }

    /**
     * 装货费
     * @return hand_charges 装货费
     */
    public String getHandCharges() {
        return handCharges;
    }

    /**
     * 装货费
     * @param handCharges 装货费
     */
    public void setHandCharges(String handCharges) {
        this.handCharges = handCharges;
    }

    /**
     * 卸货费
     * @return land_charges 卸货费
     */
    public String getLandCharges() {
        return landCharges;
    }

    /**
     * 卸货费
     * @param landCharges 卸货费
     */
    public void setLandCharges(String landCharges) {
        this.landCharges = landCharges;
    }

    /**
     * 数量
     * @return number 数量
     */
    public String getNumber() {
        return number;
    }

    /**
     * 数量
     * @param number 数量
     */
    public void setNumber(String number) {
        this.number = number;
    }

    /**
     * 重量
     * @return weight 重量
     */
    public String getWeight() {
        return weight;
    }

    /**
     * 重量
     * @param weight 重量
     */
    public void setWeight(String weight) {
        this.weight = weight;
    }

    /**
     * 合同有效期
     * @return contract_life 合同有效期
     */
    public String getContractLife() {
        return contractLife;
    }

    /**
     * 合同有效期
     * @param contractLife 合同有效期
     */
    public void setContractLife(String contractLife) {
        this.contractLife = contractLife;
    }

    /**
     * 卸货费计费方式
     * @return land_charge_bill_way 卸货费计费方式
     */
    public String getLandChargeBillWay() {
        return landChargeBillWay;
    }

    /**
     * 卸货费计费方式
     * @param landChargeBillWay 卸货费计费方式
     */
    public void setLandChargeBillWay(String landChargeBillWay) {
        this.landChargeBillWay = landChargeBillWay;
    }

    /**
     * 有效期
     * @return expir_date 有效期
     */
    public String getExpirDate() {
        return expirDate;
    }

    /**
     * 有效期
     * @param expirDate 有效期
     */
    public void setExpirDate(String expirDate) {
        this.expirDate = expirDate;
    }

    /**
     * 仓储费模板
     * @return storage_template 仓储费模板
     */
    public String getStorageTemplate() {
        return storageTemplate;
    }

    /**
     * 仓储费模板
     * @param storageTemplate 仓储费模板
     */
    public void setStorageTemplate(String storageTemplate) {
        this.storageTemplate = storageTemplate;
    }

    /**
     * 装货费模板
     * @return hand_charge_template 装货费模板
     */
    public String getHandChargeTemplate() {
        return handChargeTemplate;
    }

    /**
     * 装货费模板
     * @param handChargeTemplate 装货费模板
     */
    public void setHandChargeTemplate(String handChargeTemplate) {
        this.handChargeTemplate = handChargeTemplate;
    }

    /**
     * 卸货费模板
     * @return land_charge_template 卸货费模板
     */
    public String getLandChargeTemplate() {
        return landChargeTemplate;
    }

    /**
     * 卸货费模板
     * @param landChargeTemplate 卸货费模板
     */
    public void setLandChargeTemplate(String landChargeTemplate) {
        this.landChargeTemplate = landChargeTemplate;
    }
}