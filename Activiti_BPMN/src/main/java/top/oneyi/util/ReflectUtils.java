package top.oneyi.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 * 通过反射获取对象的工具类
 * @author oneyi
 * @date 2023/4/26
 */
public class ReflectUtils {

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

    /**
     * 获取属性名数组
     * */
    private String[] getFiledName(Object o){
        Field[] fields=o.getClass().getDeclaredFields();
        String[] fieldNames=new String[fields.length];
        for(int i=0;i<fields.length;i++){
            System.out.println(fields[i].getType());
            fieldNames[i]=fields[i].getName();
        }
        return fieldNames;
    }

    /**
     * 获取属性类型(type)，属性名(name)，属性值(value)的map组成的list
     * */
    private List getFiledsInfo(Object o){
        Field[] fields=o.getClass().getDeclaredFields();
        String[] fieldNames=new String[fields.length];
        List list = new ArrayList();
        Map infoMap=null;
        for(int i=0;i<fields.length;i++){
            infoMap = new HashMap();
            infoMap.put("type", fields[i].getType().toString());
            infoMap.put("name", fields[i].getName());
            infoMap.put("value", getFieldValueByName(fields[i].getName(), o));
            list.add(infoMap);
        }
        return list;
    }

    /**
     * 获取对象的所有属性值，返回一个对象数组
     * */
    public Object[] getFiledValues(Object o){
        String[] fieldNames=this.getFiledName(o);
        Object[] value=new Object[fieldNames.length];
        for(int i=0;i<fieldNames.length;i++){
            value[i]=this.getFieldValueByName(fieldNames[i], o);
        }
        return value;
    }

    /**
     * 根据map转换为对象
     * @param map  需要转换的map
     * @param aClass 转成成的类的class
     * @return Object
     */
    private Object mapToBean(Map<String, Object> map, Class<?> aClass){
        Object object=null;
        try {
            BeanInfo beanInfo = Introspector.getBeanInfo(aClass);
            // 根据class文件创建对象
            object = aClass.newInstance();
            // 获取所有的类属性
            PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
            String pName;
            for (PropertyDescriptor propertyDescriptor : propertyDescriptors) {
                // 获取每个属性
                pName = propertyDescriptor.getName();
                // 比对属性
                if (map.containsKey(pName)) {
                    try {
                        // 主要是id属性
                        if (propertyDescriptor.getPropertyType().getName().equals("java.lang.Long")) {
                            Object filed = map.get(pName);
                            if (filed != null) {
                                propertyDescriptor.getWriteMethod().invoke(object, Long.parseLong(filed.toString()));
                            }
                        } else {
                            // 其他属性
                            propertyDescriptor.getWriteMethod().invoke(object, map.get(pName));
                        }
                    } catch (Exception ex) {
                        Logger.getLogger(pName).log(Level.SEVERE, null, ex);
                    }
                }
            }
        } catch (IntrospectionException | IllegalAccessException | InstantiationException e) {
            e.printStackTrace();
        }
        return object;

    }

    /**
     *  bean转map
     * @param object 需要转换成map的对象
     * @return map
     */
    private Map<String,String> beanToMap(Object object) {
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

    /**
     * map转换为字符串
     * @param map
     * @return
     */
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

    /**
     * json字符串转换为map
     * @param str
     * @return
     */
    public static Map<String, Long> jsonStringToMap(String str) {
        Map<String, Long> map = (Map<String, Long>) JSON.parse(str);
        return map;
    }
}
