package top.oneyi.demo;

import org.apache.ibatis.session.SqlSession;
import org.apache.poi.ss.formula.functions.T;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import top.oneyi.ActivtiSpringBootApplication;
import top.oneyi.mapper.UserMapper;
import top.oneyi.pojo.SysUser;

import javax.annotation.Resource;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.*;


@RunWith(SpringRunner.class)//当前类为 springBoot 的测试类
@SpringBootTest(classes = ActivtiSpringBootApplication.class)//加载 SpringBoot 启动类
public class CommonMethods {

    @Resource
    private UserMapper userMapper;

    @Autowired
    private SqlSession sqlSession;

    @Test
    public void test() {
        Object object = sqlSession.selectOne("top.oneyi.mapper.UserMapper.findById", 1);


    }


}
