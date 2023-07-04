package top.oneyi.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.oneyi.common.responseServer;
import top.oneyi.pojo.po.OneUser;
import top.oneyi.pojo.po.SysUser;
import top.oneyi.service.SysUserService;

import javax.validation.Valid;
import java.util.List;

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

    @RequestMapping("/select")
    public List<SysUser> findAll(SysUser user){
        return sysUserService.findAll(user);
    }

    @PostMapping("/base")
    public String one(@Valid @RequestBody OneUser oneUser){
        return "成功返回";
    }
}
