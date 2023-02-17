package top.oneyi.service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import top.oneyi.mapper.UserMapper;
import top.oneyi.pojo.User;
import top.oneyi.service.UserService;
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
}
