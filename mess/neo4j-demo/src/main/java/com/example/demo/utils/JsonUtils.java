package com.example.demo.utils;


import com.alibaba.fastjson2.JSONArray;
import com.alibaba.fastjson2.JSONObject;

import java.util.Map;

/**
 * @author aliqingge
 * @date 2020/6/15 16:04
 */
public class JsonUtils {

    private static boolean isReplace = false;

    /**
     * 获取指定json属性对应得数据
     *
     * @param objJson
     * @param reg
     * @return
     */
    public static Object getValueByKey(Object objJson, String reg) {
        //如果obj为json数组
        if (objJson instanceof JSONArray) {
            JSONArray objArray = (JSONArray) objJson;
            for (int i = 0; i < objArray.size(); i++) {
                return getValueByKey(objArray.get(i), reg);
            }
        }
        //如果为json对象
        else if (objJson instanceof JSONObject) {
            JSONObject jsonObject = (JSONObject) objJson;
            for (Map.Entry<String, Object> entry : jsonObject.entrySet()) {
                String key = entry.getKey();
                if (reg.equals(key)) {
                    return entry.getValue();
                }
                Object o = find(reg, jsonObject, key);
                if (null != o) {
                    return o;
                }
            }
        }
        return null;
    }

    /**
     * 获取指定json属性对应得数据
     *
     * @param objJson
     * @param reg
     * @return
     */
    public static boolean isValueByKey(Object objJson, String reg) {
        //如果obj为json数组
        if (objJson instanceof JSONArray) {
            JSONArray objArray = (JSONArray) objJson;
            for (int i = 0; i < objArray.size(); i++) {
                getValueByKey(objArray.get(i), reg);
            }
        }
        //如果为json对象
        else if (objJson instanceof JSONObject) {
            JSONObject jsonObject = (JSONObject) objJson;
            for (Map.Entry<String, Object> entry : jsonObject.entrySet()) {
                String key = entry.getKey();
                if (reg.equals(key)) {
                    return true;
                }
                find(reg, jsonObject, key);
            }
        }
        return false;
    }

    /**
     * 根据key 替换原位置数据
     *
     * @param oldJson
     * @param newJson
     * @param reg
     * @return
     */
    public static boolean replaceValueByKey(Object oldJson, Object newJson, String reg) {
        //如果obj为json数组
        if (oldJson instanceof JSONArray) {
            JSONArray objArray = (JSONArray) oldJson;
            for (int i = 0; i < objArray.size(); i++) {
                getValueByKey(objArray.get(i), reg);
            }
        }
        //如果为json对象
        else if (oldJson instanceof JSONObject) {
            JSONObject jsonObject = (JSONObject) oldJson;
            for (Map.Entry<String, Object> entry : jsonObject.entrySet()) {
                String key = entry.getKey();
                if (reg.equals(key)) {
                    entry.setValue(newJson);
                    JsonUtils.isReplace = true;
                }
                Object object = jsonObject.get(key);
                //如果得到的是数组
                if (object instanceof JSONArray) {
                    JSONArray objArray = (JSONArray) object;
                    replaceValueByKey(objArray, newJson, reg);
                }
                //如果key中是一个json对象
                else if (object instanceof JSONObject) {
                    replaceValueByKey(object, newJson, reg);
                }
            }
        }
        return JsonUtils.isReplace;
    }

    private static Object find(String reg, JSONObject jsonObject, String key) {
        Object object = jsonObject.get(key);
        //如果得到的是数组
        if (object instanceof JSONArray) {
            JSONArray objArray = (JSONArray) object;
            return getValueByKey(objArray, reg);
        }
        //如果key中是一个json对象
        else if (object instanceof JSONObject) {
            return getValueByKey(object, reg);
        }
        return null;
    }
}


