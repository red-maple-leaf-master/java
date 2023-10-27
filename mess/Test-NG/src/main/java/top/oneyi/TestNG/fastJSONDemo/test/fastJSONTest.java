package top.oneyi.TestNG.fastJSONDemo.test;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.testng.annotations.Test;
import top.oneyi.TestNG.fastJSONDemo.entity.OUser;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: wanyi
 * @Date: 2023/10/27/11:20
 */
public class fastJSONTest {

    @Test()
    public void test1() {
        String str ="{\"name\":\"张三\",\"age\":\"12\"}";
        OUser oUser =  JSONObject.parseObject(str, OUser.class);
        System.out.println("oUser = " + oUser);
        Gson gson = new Gson();
        String s = gson.toJson(oUser);
        System.out.println("s = " + s);
        OUser oUser1 = gson.fromJson(s, OUser.class);
        System.out.println("oUser1 = " + oUser1);
    }

    @Test
    public void test(){
        Gson gson = new Gson();
        List<OUser> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            String str ="{\"name\":\"张三\",\"age\":\"12\"}";
            OUser oUser = gson.fromJson(str, OUser.class);
            list.add(oUser);
        }
        String s = JSONObject.toJSONString(list);
        System.out.println("s = " + s);
        List<OUser> oUsers = JSONObject.parseObject(s, new TypeReference<List<OUser>>() {
        });
        System.out.println("list1 = " + oUsers);

        List<OUser> o = gson.fromJson(s, new TypeToken<List<OUser>>() {
        }.getType());
        for (OUser oUser : o) {
            System.out.println("oUser = " + oUser);
        }
    }
}
