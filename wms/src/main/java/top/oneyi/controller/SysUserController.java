package top.oneyi.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.oneyi.common.responseServer;
import top.oneyi.service.SysUserService;

/**
 * 用户
 * @author oneyi
 * @date 2022/12/13
 */

@RestController
@RequestMapping("/NewUser")
public class SysUserController {

    @Autowired
    private SysUserService sysUserService;

    /**
     * 用户登录
     * @param userName
     * @param userPassword
     * @return
     */
    @PostMapping(value = "/login")
    public responseServer loginUser(String userName,String userPassword) {
        return sysUserService.loginSysUser(userName, userPassword);
    }
}
