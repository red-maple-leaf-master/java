package top.oneyi.service;

import top.oneyi.common.responseServer;

/**
 * 用户
 * @author oneyi
 * @date 2022/12/13
 */
public interface SysUserService {
    /**
     * 用户登录
     * @param userName
     * @param userPassword
     * @return
     */
    responseServer loginSysUser(String userName, String userPassword);
}
