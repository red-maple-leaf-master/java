package top.oneyi.pojo;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import org.apache.ibatis.type.Alias;

/**
 * md_goods
 */
@Table(name = "md_goods")
public class MdGoods {
    /**
     * 
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
     * 所属客户
     */
    private String suoShuKeHu;

    /**
     * 商品名称
     */
    private String shpMingCheng;

    /**
     * 商品简称
     */
    private String shpJianCheng;

    /**
     * 商品编码
     */
    private String shpBianMa;

    /**
     * 商品型号
     */
    private String shpXingHao;

    /**
     * 商品规格
     */
    private String shpGuiGe;

    /**
     * 商品颜色
     */
    private String shpYanSe;

    /**
     * 产品属性
     */
    private String chpShuXing;

    /**
     * 产品大类
     */
    private String cfWenCeng;

    /**
     * 拆零控制
     */
    private String chlKongZhi;

    /**
     * 码盘单层数量
     */
    private String mpDanCeng;

    /**
     * 码盘层高
     */
    private String mpCengGao;

    /**
     * 计费商品类
     */
    private String jfShpLei;

    /**
     * 商品品牌
     */
    private String shpPinPai;

    /**
     * 商品条码
     */
    private String shpTiaoMa;

    /**
     * 品牌图片
     */
    private String ppTuPian;

    /**
     * 保质期
     */
    private String bzhiQi;

    /**
     * 重量单位
     */
    private String shlDanWei;

    /**
     * (录入 件 箱 个)单位
     */
    private String jshDanWei;

    /**
     * 体积
     */
    private String tiJiCm;

    /**
     * 净重
     */
    private String zhlKg;

    /**
     * 拆零数量
     */
    private String chlShl;

    /**
     * 件数与体积比
     */
    private String jtiJiBi;

    /**
     * 件数与毛重比
     */
    private String jmZhongBi;

    /**
     * 件数与净重比
     */
    private String jjZhongBi;

    /**
     * 尺寸单位
     */
    private String chcDanWei;

    /**
     * 长单品
     */
    private String chDanPin;

    /**
     * 宽单品
     */
    private String kuDanPin;

    /**
     * 高单品
     */
    private String gaoDanPin;

    /**
     * 长整箱
     */
    private String chZhXiang;

    /**
     * 宽整箱
     */
    private String kuZhXiang;

    /**
     * 高整箱
     */
    private String gaoZhXiang;

    /**
     * 商品描述
     */
    private String shpMiaoShu;

    /**
     * 停用
     */
    private String zhuangTai;

    /**
     * 允收天数
     */
    private String zhlKgm;

    /**
     * 商品客户编码
     */
    private String shpBianMakh;

    /**
     * 基准温度
     */
    private String jizhunWendu;

    /**
     * 
     */
    private String ywMingCheng;

    /**
     * 
     */
    private String rwMingCheng;

    /**
     * 
     */
    private String cusName;

    /**
     * 
     */
    private String peisongdian;

    /**
     * 商品类目编码
     */
    private String categoryCode;

    /**
     * 
     */
    private String categoryId;

    /**
     * 
     */
    private String minStock;

    /**
     * 
     */
    private String sku;

    /**
     * 商品类目名称
     */
    private String categoryName;

    /**
     * 装货费模板编号
     */
    private String loadTemplateCode;

    /**
     * 仓储费模板编号
     */
    private String storeTemplateCode;

    /**
     * 卸货费模板编号
     */
    private String unLoadTemplateCode;

    /**
     * 装货费模板名称
     */
    private String loadTemplateName;

    /**
     * 仓储费模板名称
     */
    private String storeTemplateName;

    /**
     * 卸货费模板名称
     */
    private String unLoadTemplateName;

    /**
     * 商品单价
     */
    private String goodPrice;

    /**
     * 
     * @return id 
     */
    public String getId() {
        return id;
    }

    /**
     * 
     * @param id 
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
     * 所属客户
     * @return suo_shu_ke_hu 所属客户
     */
    public String getSuoShuKeHu() {
        return suoShuKeHu;
    }

    /**
     * 所属客户
     * @param suoShuKeHu 所属客户
     */
    public void setSuoShuKeHu(String suoShuKeHu) {
        this.suoShuKeHu = suoShuKeHu;
    }

    /**
     * 商品名称
     * @return shp_ming_cheng 商品名称
     */
    public String getShpMingCheng() {
        return shpMingCheng;
    }

    /**
     * 商品名称
     * @param shpMingCheng 商品名称
     */
    public void setShpMingCheng(String shpMingCheng) {
        this.shpMingCheng = shpMingCheng;
    }

    /**
     * 商品简称
     * @return shp_jian_cheng 商品简称
     */
    public String getShpJianCheng() {
        return shpJianCheng;
    }

    /**
     * 商品简称
     * @param shpJianCheng 商品简称
     */
    public void setShpJianCheng(String shpJianCheng) {
        this.shpJianCheng = shpJianCheng;
    }

    /**
     * 商品编码
     * @return shp_bian_ma 商品编码
     */
    public String getShpBianMa() {
        return shpBianMa;
    }

    /**
     * 商品编码
     * @param shpBianMa 商品编码
     */
    public void setShpBianMa(String shpBianMa) {
        this.shpBianMa = shpBianMa;
    }

    /**
     * 商品型号
     * @return shp_xing_hao 商品型号
     */
    public String getShpXingHao() {
        return shpXingHao;
    }

    /**
     * 商品型号
     * @param shpXingHao 商品型号
     */
    public void setShpXingHao(String shpXingHao) {
        this.shpXingHao = shpXingHao;
    }

    /**
     * 商品规格
     * @return shp_gui_ge 商品规格
     */
    public String getShpGuiGe() {
        return shpGuiGe;
    }

    /**
     * 商品规格
     * @param shpGuiGe 商品规格
     */
    public void setShpGuiGe(String shpGuiGe) {
        this.shpGuiGe = shpGuiGe;
    }

    /**
     * 商品颜色
     * @return shp_yan_se 商品颜色
     */
    public String getShpYanSe() {
        return shpYanSe;
    }

    /**
     * 商品颜色
     * @param shpYanSe 商品颜色
     */
    public void setShpYanSe(String shpYanSe) {
        this.shpYanSe = shpYanSe;
    }

    /**
     * 产品属性
     * @return chp_shu_xing 产品属性
     */
    public String getChpShuXing() {
        return chpShuXing;
    }

    /**
     * 产品属性
     * @param chpShuXing 产品属性
     */
    public void setChpShuXing(String chpShuXing) {
        this.chpShuXing = chpShuXing;
    }

    /**
     * 产品大类
     * @return cf_wen_ceng 产品大类
     */
    public String getCfWenCeng() {
        return cfWenCeng;
    }

    /**
     * 产品大类
     * @param cfWenCeng 产品大类
     */
    public void setCfWenCeng(String cfWenCeng) {
        this.cfWenCeng = cfWenCeng;
    }

    /**
     * 拆零控制
     * @return chl_kong_zhi 拆零控制
     */
    public String getChlKongZhi() {
        return chlKongZhi;
    }

    /**
     * 拆零控制
     * @param chlKongZhi 拆零控制
     */
    public void setChlKongZhi(String chlKongZhi) {
        this.chlKongZhi = chlKongZhi;
    }

    /**
     * 码盘单层数量
     * @return mp_dan_ceng 码盘单层数量
     */
    public String getMpDanCeng() {
        return mpDanCeng;
    }

    /**
     * 码盘单层数量
     * @param mpDanCeng 码盘单层数量
     */
    public void setMpDanCeng(String mpDanCeng) {
        this.mpDanCeng = mpDanCeng;
    }

    /**
     * 码盘层高
     * @return mp_ceng_gao 码盘层高
     */
    public String getMpCengGao() {
        return mpCengGao;
    }

    /**
     * 码盘层高
     * @param mpCengGao 码盘层高
     */
    public void setMpCengGao(String mpCengGao) {
        this.mpCengGao = mpCengGao;
    }

    /**
     * 计费商品类
     * @return jf_shp_lei 计费商品类
     */
    public String getJfShpLei() {
        return jfShpLei;
    }

    /**
     * 计费商品类
     * @param jfShpLei 计费商品类
     */
    public void setJfShpLei(String jfShpLei) {
        this.jfShpLei = jfShpLei;
    }

    /**
     * 商品品牌
     * @return shp_pin_pai 商品品牌
     */
    public String getShpPinPai() {
        return shpPinPai;
    }

    /**
     * 商品品牌
     * @param shpPinPai 商品品牌
     */
    public void setShpPinPai(String shpPinPai) {
        this.shpPinPai = shpPinPai;
    }

    /**
     * 商品条码
     * @return shp_tiao_ma 商品条码
     */
    public String getShpTiaoMa() {
        return shpTiaoMa;
    }

    /**
     * 商品条码
     * @param shpTiaoMa 商品条码
     */
    public void setShpTiaoMa(String shpTiaoMa) {
        this.shpTiaoMa = shpTiaoMa;
    }

    /**
     * 品牌图片
     * @return pp_tu_pian 品牌图片
     */
    public String getPpTuPian() {
        return ppTuPian;
    }

    /**
     * 品牌图片
     * @param ppTuPian 品牌图片
     */
    public void setPpTuPian(String ppTuPian) {
        this.ppTuPian = ppTuPian;
    }

    /**
     * 保质期
     * @return bzhi_qi 保质期
     */
    public String getBzhiQi() {
        return bzhiQi;
    }

    /**
     * 保质期
     * @param bzhiQi 保质期
     */
    public void setBzhiQi(String bzhiQi) {
        this.bzhiQi = bzhiQi;
    }

    /**
     * 重量单位
     * @return shl_dan_wei 重量单位
     */
    public String getShlDanWei() {
        return shlDanWei;
    }

    /**
     * 重量单位
     * @param shlDanWei 重量单位
     */
    public void setShlDanWei(String shlDanWei) {
        this.shlDanWei = shlDanWei;
    }

    /**
     * (录入 件 箱 个)单位
     * @return jsh_dan_wei (录入 件 箱 个)单位
     */
    public String getJshDanWei() {
        return jshDanWei;
    }

    /**
     * (录入 件 箱 个)单位
     * @param jshDanWei (录入 件 箱 个)单位
     */
    public void setJshDanWei(String jshDanWei) {
        this.jshDanWei = jshDanWei;
    }

    /**
     * 体积
     * @return ti_ji_cm 体积
     */
    public String getTiJiCm() {
        return tiJiCm;
    }

    /**
     * 体积
     * @param tiJiCm 体积
     */
    public void setTiJiCm(String tiJiCm) {
        this.tiJiCm = tiJiCm;
    }

    /**
     * 净重
     * @return zhl_kg 净重
     */
    public String getZhlKg() {
        return zhlKg;
    }

    /**
     * 净重
     * @param zhlKg 净重
     */
    public void setZhlKg(String zhlKg) {
        this.zhlKg = zhlKg;
    }

    /**
     * 拆零数量
     * @return chl_shl 拆零数量
     */
    public String getChlShl() {
        return chlShl;
    }

    /**
     * 拆零数量
     * @param chlShl 拆零数量
     */
    public void setChlShl(String chlShl) {
        this.chlShl = chlShl;
    }

    /**
     * 件数与体积比
     * @return jti_ji_bi 件数与体积比
     */
    public String getJtiJiBi() {
        return jtiJiBi;
    }

    /**
     * 件数与体积比
     * @param jtiJiBi 件数与体积比
     */
    public void setJtiJiBi(String jtiJiBi) {
        this.jtiJiBi = jtiJiBi;
    }

    /**
     * 件数与毛重比
     * @return jm_zhong_bi 件数与毛重比
     */
    public String getJmZhongBi() {
        return jmZhongBi;
    }

    /**
     * 件数与毛重比
     * @param jmZhongBi 件数与毛重比
     */
    public void setJmZhongBi(String jmZhongBi) {
        this.jmZhongBi = jmZhongBi;
    }

    /**
     * 件数与净重比
     * @return jj_zhong_bi 件数与净重比
     */
    public String getJjZhongBi() {
        return jjZhongBi;
    }

    /**
     * 件数与净重比
     * @param jjZhongBi 件数与净重比
     */
    public void setJjZhongBi(String jjZhongBi) {
        this.jjZhongBi = jjZhongBi;
    }

    /**
     * 尺寸单位
     * @return chc_dan_wei 尺寸单位
     */
    public String getChcDanWei() {
        return chcDanWei;
    }

    /**
     * 尺寸单位
     * @param chcDanWei 尺寸单位
     */
    public void setChcDanWei(String chcDanWei) {
        this.chcDanWei = chcDanWei;
    }

    /**
     * 长单品
     * @return ch_dan_pin 长单品
     */
    public String getChDanPin() {
        return chDanPin;
    }

    /**
     * 长单品
     * @param chDanPin 长单品
     */
    public void setChDanPin(String chDanPin) {
        this.chDanPin = chDanPin;
    }

    /**
     * 宽单品
     * @return ku_dan_pin 宽单品
     */
    public String getKuDanPin() {
        return kuDanPin;
    }

    /**
     * 宽单品
     * @param kuDanPin 宽单品
     */
    public void setKuDanPin(String kuDanPin) {
        this.kuDanPin = kuDanPin;
    }

    /**
     * 高单品
     * @return gao_dan_pin 高单品
     */
    public String getGaoDanPin() {
        return gaoDanPin;
    }

    /**
     * 高单品
     * @param gaoDanPin 高单品
     */
    public void setGaoDanPin(String gaoDanPin) {
        this.gaoDanPin = gaoDanPin;
    }

    /**
     * 长整箱
     * @return ch_zh_xiang 长整箱
     */
    public String getChZhXiang() {
        return chZhXiang;
    }

    /**
     * 长整箱
     * @param chZhXiang 长整箱
     */
    public void setChZhXiang(String chZhXiang) {
        this.chZhXiang = chZhXiang;
    }

    /**
     * 宽整箱
     * @return ku_zh_xiang 宽整箱
     */
    public String getKuZhXiang() {
        return kuZhXiang;
    }

    /**
     * 宽整箱
     * @param kuZhXiang 宽整箱
     */
    public void setKuZhXiang(String kuZhXiang) {
        this.kuZhXiang = kuZhXiang;
    }

    /**
     * 高整箱
     * @return gao_zh_xiang 高整箱
     */
    public String getGaoZhXiang() {
        return gaoZhXiang;
    }

    /**
     * 高整箱
     * @param gaoZhXiang 高整箱
     */
    public void setGaoZhXiang(String gaoZhXiang) {
        this.gaoZhXiang = gaoZhXiang;
    }

    /**
     * 商品描述
     * @return shp_miao_shu 商品描述
     */
    public String getShpMiaoShu() {
        return shpMiaoShu;
    }

    /**
     * 商品描述
     * @param shpMiaoShu 商品描述
     */
    public void setShpMiaoShu(String shpMiaoShu) {
        this.shpMiaoShu = shpMiaoShu;
    }

    /**
     * 停用
     * @return zhuang_tai 停用
     */
    public String getZhuangTai() {
        return zhuangTai;
    }

    /**
     * 停用
     * @param zhuangTai 停用
     */
    public void setZhuangTai(String zhuangTai) {
        this.zhuangTai = zhuangTai;
    }

    /**
     * 允收天数
     * @return zhl_kgm 允收天数
     */
    public String getZhlKgm() {
        return zhlKgm;
    }

    /**
     * 允收天数
     * @param zhlKgm 允收天数
     */
    public void setZhlKgm(String zhlKgm) {
        this.zhlKgm = zhlKgm;
    }

    /**
     * 商品客户编码
     * @return SHP_BIAN_MAKH 商品客户编码
     */
    public String getShpBianMakh() {
        return shpBianMakh;
    }

    /**
     * 商品客户编码
     * @param shpBianMakh 商品客户编码
     */
    public void setShpBianMakh(String shpBianMakh) {
        this.shpBianMakh = shpBianMakh;
    }

    /**
     * 基准温度
     * @return JIZHUN_WENDU 基准温度
     */
    public String getJizhunWendu() {
        return jizhunWendu;
    }

    /**
     * 基准温度
     * @param jizhunWendu 基准温度
     */
    public void setJizhunWendu(String jizhunWendu) {
        this.jizhunWendu = jizhunWendu;
    }

    /**
     * 
     * @return yw_ming_cheng 
     */
    public String getYwMingCheng() {
        return ywMingCheng;
    }

    /**
     * 
     * @param ywMingCheng 
     */
    public void setYwMingCheng(String ywMingCheng) {
        this.ywMingCheng = ywMingCheng;
    }

    /**
     * 
     * @return rw_ming_cheng 
     */
    public String getRwMingCheng() {
        return rwMingCheng;
    }

    /**
     * 
     * @param rwMingCheng 
     */
    public void setRwMingCheng(String rwMingCheng) {
        this.rwMingCheng = rwMingCheng;
    }

    /**
     * 
     * @return cus_name 
     */
    public String getCusName() {
        return cusName;
    }

    /**
     * 
     * @param cusName 
     */
    public void setCusName(String cusName) {
        this.cusName = cusName;
    }

    /**
     * 
     * @return peisongdian 
     */
    public String getPeisongdian() {
        return peisongdian;
    }

    /**
     * 
     * @param peisongdian 
     */
    public void setPeisongdian(String peisongdian) {
        this.peisongdian = peisongdian;
    }

    /**
     * 商品类目编码
     * @return category_code 商品类目编码
     */
    public String getCategoryCode() {
        return categoryCode;
    }

    /**
     * 商品类目编码
     * @param categoryCode 商品类目编码
     */
    public void setCategoryCode(String categoryCode) {
        this.categoryCode = categoryCode;
    }

    /**
     * 
     * @return category_id 
     */
    public String getCategoryId() {
        return categoryId;
    }

    /**
     * 
     * @param categoryId 
     */
    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    /**
     * 
     * @return min_stock 
     */
    public String getMinStock() {
        return minStock;
    }

    /**
     * 
     * @param minStock 
     */
    public void setMinStock(String minStock) {
        this.minStock = minStock;
    }

    /**
     * 
     * @return sku 
     */
    public String getSku() {
        return sku;
    }

    /**
     * 
     * @param sku 
     */
    public void setSku(String sku) {
        this.sku = sku;
    }

    /**
     * 商品类目名称
     * @return category_name 商品类目名称
     */
    public String getCategoryName() {
        return categoryName;
    }

    /**
     * 商品类目名称
     * @param categoryName 商品类目名称
     */
    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    /**
     * 装货费模板编号
     * @return load_template_code 装货费模板编号
     */
    public String getLoadTemplateCode() {
        return loadTemplateCode;
    }

    /**
     * 装货费模板编号
     * @param loadTemplateCode 装货费模板编号
     */
    public void setLoadTemplateCode(String loadTemplateCode) {
        this.loadTemplateCode = loadTemplateCode;
    }

    /**
     * 仓储费模板编号
     * @return store_template_code 仓储费模板编号
     */
    public String getStoreTemplateCode() {
        return storeTemplateCode;
    }

    /**
     * 仓储费模板编号
     * @param storeTemplateCode 仓储费模板编号
     */
    public void setStoreTemplateCode(String storeTemplateCode) {
        this.storeTemplateCode = storeTemplateCode;
    }

    /**
     * 卸货费模板编号
     * @return un_load_template_code 卸货费模板编号
     */
    public String getUnLoadTemplateCode() {
        return unLoadTemplateCode;
    }

    /**
     * 卸货费模板编号
     * @param unLoadTemplateCode 卸货费模板编号
     */
    public void setUnLoadTemplateCode(String unLoadTemplateCode) {
        this.unLoadTemplateCode = unLoadTemplateCode;
    }

    /**
     * 装货费模板名称
     * @return load_template_name 装货费模板名称
     */
    public String getLoadTemplateName() {
        return loadTemplateName;
    }

    /**
     * 装货费模板名称
     * @param loadTemplateName 装货费模板名称
     */
    public void setLoadTemplateName(String loadTemplateName) {
        this.loadTemplateName = loadTemplateName;
    }

    /**
     * 仓储费模板名称
     * @return store_template_name 仓储费模板名称
     */
    public String getStoreTemplateName() {
        return storeTemplateName;
    }

    /**
     * 仓储费模板名称
     * @param storeTemplateName 仓储费模板名称
     */
    public void setStoreTemplateName(String storeTemplateName) {
        this.storeTemplateName = storeTemplateName;
    }

    /**
     * 卸货费模板名称
     * @return un_load_template_name 卸货费模板名称
     */
    public String getUnLoadTemplateName() {
        return unLoadTemplateName;
    }

    /**
     * 卸货费模板名称
     * @param unLoadTemplateName 卸货费模板名称
     */
    public void setUnLoadTemplateName(String unLoadTemplateName) {
        this.unLoadTemplateName = unLoadTemplateName;
    }

    /**
     * 商品单价
     * @return good_price 商品单价
     */
    public String getGoodPrice() {
        return goodPrice;
    }

    /**
     * 商品单价
     * @param goodPrice 商品单价
     */
    public void setGoodPrice(String goodPrice) {
        this.goodPrice = goodPrice;
    }
}