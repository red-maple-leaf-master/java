package top.oneyi.mapper;


import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import top.oneyi.pojo.po.WmOmNoticeH;
import top.oneyi.util.MyMapper;

import java.util.List;
import java.util.Map;

@Repository
public interface WmOmNoticeHServiceMapper extends MyMapper<WmOmNoticeH> {
    List<WmOmNoticeH> selectWmOmNoticeH(@Param("omNoticeId") String omNoticeId,
                                        @Param("delvData_begin1") String delvData_begin1,
                                        @Param("delvData_end2") String delvData_end2,
                                        @Param("delvMember") String delvMember,
                                        @Param("delvMobile") String delvMobile,
                                        @Param("reMember") String reMember,
                                        @Param("reCarno") String reCarno,
                                        @Param("omSta") String omSta);

    List<WmOmNoticeH> selectWmOmNoticeHByDatagrid(@Param("omNoticeId") String omNoticeId, @Param("delvData_begin1") String delvData_begin1, @Param("delvData_end2") String delvData_end2, @Param("delvMember") String delvMember, @Param("delvMobile") String delvMobile, @Param("reMember") String reMember, @Param("reCarno") String reCarno, @Param("omSta") String omSta);

    List<WmOmNoticeH> selectWmOmNoticeHBydatagriditem(@Param("omNoticeId") String omNoticeId,
                                                      @Param("imCusCode") String imCusCode,
                                                      @Param("omBeizhu") String omBeizhu,
                                                      @Param("goodsName") String goodsName,
                                                      @Param("sku") String sku,
                                                      @Param("binId") String binId);

    List<WmOmNoticeH> datagridbatchconf(@Param("imCusCode") String imCusCode,
                                        @Param("omBeizhu") String omBeizhu,
                                        @Param("omNoticeId") String omNoticeId,
                                        @Param("cusCode") String cusCode,
                                        @Param("delvData_begin") String delvData_begin,
                                        @Param("delvData_end") String delvData_end,
                                        @Param("delvMember") String delvMember,
                                        @Param("delvMobile") String delvMobile,
                                        @Param("reMember") String reMember,
                                        @Param("reMobile") String reMobile,
                                        @Param("reCarno") String reCarno,
                                        @Param("omPlatNo") String omPlatNo,
                                        @Param("omSta") String omSta);

    WmOmNoticeH getOne(String id);

    /**
     * 根据 omNoticeId 查询
     *
     * @param omNoticeId
     * @return
     */
    WmOmNoticeH findByOmNoticeId(String omNoticeId);

    List<Map<String, Object>> find(String omNoticeId);
}
