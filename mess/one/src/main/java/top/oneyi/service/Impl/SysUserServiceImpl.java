package top.oneyi.service.Impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.util.StringUtil;
import top.oneyi.common.responseServer;
import top.oneyi.mapper.SysUserMapper;
import top.oneyi.pojo.po.SysUser;
import top.oneyi.service.SysUserService;
import top.oneyi.util.JWTUtil;
import top.oneyi.util.PasswordHelp;

import java.util.List;

/**
 * 用户
 *
 * @author oneyi
 * @date 2022/12/13
 */
@Service
public class SysUserServiceImpl implements SysUserService {

    @Autowired
    private SysUserMapper sysUserMapper;

    /**
     * 用户登录
     *
     * @param userName
     * @param userPassword
     * @return
     */
    @Override
    public responseServer loginSysUser(String userName, String userPassword) {
        // 参数验证
        if (StringUtil.isEmpty(userName) || StringUtil.isEmpty(userPassword)) {
            return responseServer.createByCodeMessage(0, "账号或密码不能为空", null, null, null, null);
        }
        // 查询用户
        SysUser sysUser = sysUserMapper.findByName(userName);
        if (sysUser == null) {
            return responseServer.createByCodeMessage(2, "该用户不存在", null, null, null, null);
        }
        // 查看用户是否废弃
        if (sysUser.getState() == 0) {
            return responseServer.createByCodeMessage(3, "该用户状态异常", null, null, null, null);
        }
        // 将明文密码加密
        String loginPassword = PasswordHelp.passwordSalt(userName, userPassword);
        if (!loginPassword.equals(sysUser.getUserPassword())) {
            return responseServer.createByCodeMessage(4, "账号或密码不正确正确", null, null, null, null);
        }
        //登录成功生成token返回给前端
        String token = JWTUtil.sign(sysUser.getId(), sysUser.getUserName(), sysUser.getUserPassword(), null);
        return responseServer.createByCodeMessage(1, "登录成功 ", token, sysUser.getId(), sysUser.getUserName(), sysUser.getRealName());
    }

    /**
     * 查找所有用户
     *
     * @param user
     * @return
     */
    @Override
    public List<SysUser> findAll(SysUser user) {
        return sysUserMapper.findAll(user);
    }
}
