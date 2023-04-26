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
//        SysUser sysUser = userMapper.findById(1L);
//        System.out.println("sysUser = " + sysUser);

        Object object = sqlSession.selectOne("top.oneyi.mapper.UserMapper.findById", 1);
        Field[] declaredFields = object.getClass().getDeclaredFields();
        String[] filedNames = new String[declaredFields.length];
        List list = new ArrayList<>();
        Map infoMap = null;
        for (int i = 0; i < declaredFields.length; i++) {
            filedNames[i] = declaredFields[i].getName();

            infoMap = new HashMap();
            infoMap.put("type",declaredFields[i].getType().toString());
            infoMap.put("name",declaredFields[i].getName());
            infoMap.put("value",getFieldValueByName(declaredFields[i].getName(),object));
            list.add(infoMap);

        }
        System.out.println("filedNames = " + Arrays.toString(filedNames));
        System.out.println("=========================================");
        System.out.println("list = " + list);

    }


    /**
     * 根据属性名获取属性值
     * */
    private Object getFieldValueByName(String fieldName, Object o) {
        try {
            String firstLetter = fieldName.substring(0, 1).toUpperCase();
            String getter = "get" + firstLetter + fieldName.substring(1);
            Method method = o.getClass().getMethod(getter, new Class[] {});
            Object value = method.invoke(o, new Object[] {});
            return value;
        } catch (Exception e) {
            return null;
        }
    }

    public void one(String[] str, String splitter) {
        StringJoiner joiner = new StringJoiner(splitter);

        for (String s : str) {
            joiner.add(s);
        }
        System.out.println("joiner.toString() = " + joiner.toString());
    }


    public String approve() throws NoSuchMethodException {


        return "";
    }
}
