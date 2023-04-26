package top.oneyi.demo;

import cn.hutool.core.map.MapUtil;
import org.apache.commons.lang3.StringUtils;
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
import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;


@RunWith(SpringRunner.class)//当前类为 springBoot 的测试类
@SpringBootTest(classes = ActivtiSpringBootApplication.class)//加载 SpringBoot 启动类
public class CommonMethods {

    @Resource
    private UserMapper userMapper;

    @Autowired
    private SqlSession sqlSession;

    @Test
    public void test() throws Exception {
        Object object = sqlSession.selectOne("top.oneyi.mapper.UserMapper.findById", 1);
        Field[] declaredFields = object.getClass().getDeclaredFields();
        Map<String, Object> obj = new HashMap<>();
        for (Field declaredField : declaredFields) {
            String name = declaredField.getName();
            String fileName = declaredField.getName();
            String firstFileName = fileName.substring(0, 1).toUpperCase();
            String getter = "get" + firstFileName + fileName.substring(1);
            Method method = object.getClass().getMethod(getter, new Class[]{});
            Object invoke = method.invoke(object, new Object[]{});
            obj.put(name, invoke);
        }
        System.out.println("obj = " + obj);

        Class<?> aClass = Class.forName("top.oneyi.pojo.SysUser");
        mapToBean(obj, aClass);
    }

    private Object mapToBean(Map<String, Object> obj, Class<?> aClass) throws IntrospectionException, InstantiationException, IllegalAccessException {
        BeanInfo beanInfo = Introspector.getBeanInfo(aClass);
        Object o = aClass.newInstance();
        PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
        String pName;
        for (PropertyDescriptor propertyDescriptor : propertyDescriptors) {
            pName = propertyDescriptor.getName();
            if (obj.containsKey(pName)) {
                try {
                    if (propertyDescriptor.getPropertyType().getName().equals("java.lang.Long")) {
                        Object filed = obj.get(pName);
                        if (filed != null) {
                            propertyDescriptor.getWriteMethod().invoke(o, Long.parseLong(filed.toString()));
                        }
                    } else {
                        propertyDescriptor.getWriteMethod().invoke(o, obj.get(pName));
                    }
                } catch (Exception ex) {
                    Logger.getLogger(MapUtil.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
       return o;
    }


}
