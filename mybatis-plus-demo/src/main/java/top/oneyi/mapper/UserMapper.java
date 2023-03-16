package top.oneyi.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import top.oneyi.pojo.User;
/**
 *
 * @author oneyi
 * @date 2023/3/16
 */

@Mapper
public interface UserMapper extends BaseMapper<User> {
}
