package top.oneyi.service;

/**
 *首页
 * @author oneyi
 * @date 2022/12/13
 */

public interface HomePageService {
    /**
     * 首页折线图查询
     * @param data_begin
     * @param data_end
     * @return
     */
    Object warehouseInfoDate(String data_begin, String data_end);

    /**
     *工作台统计
     * @return
     */
    Object warehouseInfo();
}
