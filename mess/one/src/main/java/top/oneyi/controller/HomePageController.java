package top.oneyi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import top.oneyi.common.ServerResponse;
import top.oneyi.service.HomePageService;

import javax.servlet.http.HttpServletRequest;


/**
 * 首页
 *
 * @author oneyi
 * @date 2022/12/13
 */

@RestController
@RequestMapping("/cgAutoListController")
public class HomePageController {

    @Autowired
    private HomePageService homePageService;

    /**
     * 工作台统计
     */
    @GetMapping("/warehouseInfo")
    @ResponseBody
    @Transactional(rollbackFor = Exception.class)
    public ServerResponse warehouseInfo(HttpServletRequest request) {
        return ServerResponse.createBySuccess(homePageService.warehouseInfo());
    }

    /**
     * 首页折线图查询
     */
    @PostMapping("/warehouseInfoDate")
    @ResponseBody
    @Transactional(rollbackFor = Exception.class)
    public ServerResponse warehouseInfoDate(@RequestParam(value = "date[]", required = false) String[] date) {
        String data_begin = null;
        String data_end = null;
        if (date != null) {
            data_begin = date[0];
            data_end = date[1];
        }
        return ServerResponse.createBySuccess(homePageService.warehouseInfoDate(data_begin, data_end));
    }
}
