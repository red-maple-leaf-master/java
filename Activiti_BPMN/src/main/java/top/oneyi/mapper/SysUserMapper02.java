package top.oneyi.mapper;

import top.oneyi.pojo.ActBusinessStatus;
import top.oneyi.pojo.SysUser;
import top.oneyi.util.MyMapper;

import java.util.List;

public interface SysUserMapper02 extends MyMapper<ActBusinessStatus> {

    List<SysUser> findByIds(List<Long> assigneeList);
}
