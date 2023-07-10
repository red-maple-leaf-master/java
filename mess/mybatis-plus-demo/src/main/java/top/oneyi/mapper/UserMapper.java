package top.oneyi.mapper;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.apache.ibatis.annotations.Mapper;
import top.oneyi.pojo.User;

import java.util.List;

/**
 * @author oneyi
 * @date 2023/3/16
 */

@Mapper
public interface UserMapper extends BaseMapper<User> {

    List<User> findByIds(List<Long> assigneeList);

    IPage<User> findAll(IPage<User> page, LambdaQueryWrapper<User> wrapper);

    User findById(Long UserId);
}
