package top.oneyi.pojo.vo;


import lombok.Data;
import top.oneyi.pojo.po.WmOmNoticeH;

/**
 * @author ：oneyi
 * @date ：Created in 2022/12/16 14:49：
 */
@Data
public class WmOmNoticeHVo extends WmOmNoticeH {

    /**
     * 计费周期（日结 月结）
     */
    private String billingCycle;
}
