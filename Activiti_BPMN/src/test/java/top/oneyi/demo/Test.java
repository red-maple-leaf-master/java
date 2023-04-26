package top.oneyi.demo;
 
 
import java.util.HashMap;
import java.util.Map;

public class Test {
    public static void main(String[] args) {
        Map<String,Object> map = new HashMap<>();
        map.put("roleId",null);
        String roleId = map.get("roleId").toString();
    }
}