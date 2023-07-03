package top.oneyi.demo;

import cn.hutool.core.map.MapUtil;
import org.apache.ibatis.session.SqlSession;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import top.oneyi.ActivtiSpringBootApplication;
import top.oneyi.mapper.SysUserMapper;

import javax.annotation.Resource;
import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;


@RunWith(SpringRunner.class)//当前类为 springBoot 的测试类
@SpringBootTest(classes = ActivtiSpringBootApplication.class)//加载 SpringBoot 启动类
public class CommonMethods {

    @Resource
    private SysUserMapper sysUserMapper;

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


    @Test
    public void test01() throws JSONException {
        Object object = sqlSession.selectOne("top.oneyi.mapper.UserMapper.findById", 1);
        // bean对象转map
        Map map = beanToMap(object);
        System.out.println("map = " + map);

        JSONObject json = mapToJson(map);
        System.out.println(json.toString());

        String output = output(json);
        System.out.println("output = " + output);
        String resString = json.toString();
        StringBuilder jsonForMatStr = new StringBuilder();
        for(int index =0;index<resString.length();index++){
            // 取出每一个字符
            char c = resString.charAt(index);
            switch (c) {
                case '{':
                case '[':
                case ',':
                    jsonForMatStr.append(c).append("\n").append("\t");
                    break;
                case '}':
                case ']':
                    jsonForMatStr.append("\n").append(c);
                    break;
                default:
                    jsonForMatStr.append(c);
                    break;
            }
        }
        System.out.println(jsonForMatStr.toString());

    }


    public static String output(JSONObject jsonObject) {
        String resString = jsonObject.toString();
        StringBuilder jsonForMatStr = new StringBuilder();
        int level = 0;
        for(int index=0;index<resString.length();index++) {//将字符串中的字符逐个按行输出
            //获取s中的每个字符
            char c = resString.charAt(index);

            //level大于0并且jsonForMatStr中的最后一个字符为\n,jsonForMatStr加入\t
            if (level > 0  && '\n' == jsonForMatStr.charAt(jsonForMatStr.length() - 1)) {
                jsonForMatStr.append(getLevelStr(level));
            }
            //遇到"{"和"["要增加空格和换行，遇到"}"和"]"要减少空格，以对应，遇到","要换行
            switch (c) {
                case '{':
                case '[':
                    jsonForMatStr.append(c).append("\n");
                    level++;
                    break;
                case ',':
                    jsonForMatStr.append(c).append("\n");
                    break;
                case '}':
                case ']':
                    jsonForMatStr.append("\n");
                    level--;
                    jsonForMatStr.append(getLevelStr(level));
                    jsonForMatStr.append(c);
                    break;
                default:
                    jsonForMatStr.append(c);
                    break;
            }
        }
        return jsonForMatStr.toString();
    }
    /**
     * @param level
     * @return
     * @throws
     * @author zjd
     * @date 2021/03/31-14:29
     */
    private static String getLevelStr(int level) {
        StringBuilder levelStr = new StringBuilder();
        for (int levelI = 0; levelI < level; levelI++) {
            levelStr.append("\t");
        }
        return levelStr.toString();
    }


    private JSONObject mapToJson(Map map){
        JSONObject json = new JSONObject();
        try{
            Iterator<Map.Entry<String,String>> iterator = map.entrySet().iterator();
            while(iterator.hasNext()){
                Map.Entry<String, String> entry = iterator.next();
                json.put(entry.getKey(), entry.getValue());
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return json;
    }

    private Map beanToMap(Object object) {
        if (object == null) {
            System.out.println("很抱歉哦,你的对象不存在");
            return null;
        } else {
            Map<String, String> map = new HashMap<>();
            try {
                BeanInfo beanInfo = Introspector.getBeanInfo(object.getClass());
                PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
                for (PropertyDescriptor propertyDescriptor : propertyDescriptors) {
                    String key = propertyDescriptor.getName();
                    if (key.compareToIgnoreCase("class") == 0) {
                        continue;
                    }
                    if (key.compareToIgnoreCase("params") == 0) {
                        continue;
                    }
                    Method getter = propertyDescriptor.getReadMethod();
                    Object value = getter != null ? getter.invoke(object) : null;
                    String v = null;
                    if (value != null) {
                        v = value.toString();
                    }
                    map.put(key, v);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return map;
        }
    }


}
