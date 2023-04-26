package top.oneyi.demo;
 
 
import java.util.HashMap;
 
public class Test {
    public static void main(String[] args) {
        Student student = new Student();
        String name = student.getName();
        name = "张三";
        student.setName(name);
        System.out.println(student.toString());
        HashMap<String, Object> otherInfo = student.getOtherInfo();
        otherInfo.put("name","李思思4");
        otherInfo.put("sex", "男");
        otherInfo.put("age", 23);
        otherInfo.put("phone", "10086");
        otherInfo.put("nickName", "10086dd");
        System.out.println(student.toString());
        System.out.println("✨ "+ name  +" ✨");
    }
}