package top.oneyi.mapper;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import top.oneyi.pojo.po.WmOmNoticeI;
import top.oneyi.pojo.vo.WmOmNoticeIVo;
import top.oneyi.util.MyMapper;

import java.util.List;

@Repository
public interface WmOmNoticeIMapper extends MyMapper<WmOmNoticeI> {
    List<WmOmNoticeIVo> datagridassign(@Param("omNoticeId") String omNoticeId,
                                       @Param("goodsId") String goodsId,
                                       @Param("goodsName") String goodsName,
                                       @Param("omBeizhu") String omBeizhu,
                                       @Param("cusCode") String cusCode,
                                       @Param("storeArea") String storeArea,
                                       @Param("delvMember") String delvMember);

    WmOmNoticeI selectById(String id);

    List<WmOmNoticeI> selectListByNoticeId(String omNoticeId);

    List<WmOmNoticeIVo> shelves(@Param("omNoticeId") String omNoticeId,
                                @Param("goodsId") String goodsId,
                                @Param("goodsName") String goodsName,
                                @Param("omBeizhu") String omBeizhu,
                                @Param("cusCode") String cusCode,
                                @Param("storeArea") String storeArea,
                                @Param("delvMember") String delvMember);

    List<WmOmNoticeIVo> orderPicking(@Param("omNoticeId") String omNoticeId,
                                     @Param("goodsId") String goodsId,
                                     @Param("goodsName") String goodsName,
                                     @Param("omBeizhu") String omBeizhu,
                                     @Param("cusCode") String cusCode,
                                     @Param("storeArea") String storeArea,
                                     @Param("delvMember") String delvMember);

    List<WmOmNoticeIVo> checkTheLoad(@Param("omNoticeId") String omNoticeId,
                                     @Param("goodsId") String goodsId,
                                     @Param("goodsName") String goodsName,
                                     @Param("omBeizhu") String omBeizhu,
                                     @Param("cusCode") String cusCode,
                                     @Param("storeArea") String storeArea,
                                     @Param("delvMember") String delvMember);

    List<WmOmNoticeIVo> inRoadInventory();

    List<WmOmNoticeIVo> delShelves(@Param("omNoticeId") String omNoticeId,
                                   @Param("goodsId") String goodsId,
                                   @Param("goodsName") String goodsName,
                                   @Param("omBeizhu") String omBeizhu,
                                   @Param("cusCode") String cusCode,
                                   @Param("storeArea") String storeArea,
                                   @Param("delvMember") String delvMember);

    int selectCurrentOutboundNum();

}
