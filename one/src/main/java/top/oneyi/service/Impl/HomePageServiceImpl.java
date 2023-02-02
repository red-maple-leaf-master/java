package top.oneyi.service.Impl;

import org.springframework.stereotype.Service;
import top.oneyi.service.HomePageService;
/**
 *首页
 * @author oneyi
 * @date 2022/12/13
 */

@Service
public class HomePageServiceImpl implements HomePageService {
    /**
     * 首页折线图查询
     *
     * @param data_begin
     * @param data_end
     * @return
     */
    @Override
    public Object warehouseInfoDate(String data_begin, String data_end) {
        return null;
    }
    /**
     * 工作台统计
     *
     * @return
     */
    @Override
    public Object warehouseInfo() {
        return null;
    }
}
