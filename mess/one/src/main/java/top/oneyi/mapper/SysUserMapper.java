package top.oneyi.mapper;



import org.springframework.stereotype.Repository;
import top.oneyi.pojo.po.SysUser;
import top.oneyi.util.MyMapper;

import java.util.List;

/**
 * 用户
 * @author oneyi
 * @date 2022/12/13
 */
@Repository
public interface SysUserMapper extends MyMapper<SysUser> {
    /**
     * 根据用户名查询用户
     * @param userName
     * @return
     */
    SysUser findByName(String userName);

    /**
     * 查找所有用户
     * @param user
     * @return
     */
    List<SysUser> findAll(SysUser user);
}