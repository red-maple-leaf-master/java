package top.oneyi.service;


import top.oneyi.common.responseServer;
import top.oneyi.pojo.po.SysUser;

import java.util.List;

/**
 * 用户
 *
 * @author oneyi
 * @date 2022/12/13
 */
public interface SysUserService {
    /**
     * 用户登录
     *
     * @param userName
     * @param userPassword
     * @return
     */
    responseServer loginSysUser(String userName, String userPassword);

    /**
     * 查找所有用户
     *
     * @param user
     * @return
     */
    List<SysUser> findAll(SysUser user);
}
