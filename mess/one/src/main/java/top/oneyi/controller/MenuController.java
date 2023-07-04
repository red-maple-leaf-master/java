package top.oneyi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.oneyi.common.ServerResponse;
import top.oneyi.pojo.po.SysMenu;
import top.oneyi.service.MenuService;
import top.oneyi.util.JWTUtil;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 菜单
 * @author oneyi
 * @date 2022/12/13
 */
@RestController
@RequestMapping("/Menu")
public class MenuController {

    @Autowired
    private MenuService menuService;

    @Resource
    private HttpServletRequest request;
    /**
     * 查询菜单
     * @return
     */
    @GetMapping("/userMenu")
    public ServerResponse<List<SysMenu>> user() {
        String token = request.getHeader("token");
        List<SysMenu> sysMenuList = menuService.queryAllSysMenu(JWTUtil.getUserId(token));
        return ServerResponse.createBySuccess(sysMenuList);
    }

}
