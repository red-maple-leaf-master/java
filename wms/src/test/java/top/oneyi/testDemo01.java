package top.oneyi;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import top.oneyi.mapper.SysUserMapper;
import top.oneyi.pojo.po.SysUser;

import java.util.HashMap;
import java.util.Map;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = OneApplication.class)
public class testDemo01 {

    @Autowired
    private SysUserMapper sysUserMapper;

    @Test
    public void userTest01(){
        SysUser admin = sysUserMapper.findByName("admin");
        System.out.println("admin = " + admin);
    }

    @Test
    public void logTest01(){
//        Logger logger = LoggerFactory.getLogger(Object.class);
//        logger.info("===========info===========");
//        logger.warn("===========warn===========");
//        logger.trace("===========trace===========");
//        logger.error("===========error===========");
//        logger.debug("===========debug===========");
    }




}
