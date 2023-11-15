package top.oneyi.TestNG.fastJSONDemo.entity;

import lombok.Data;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: wanyi
 * @Date: 2023/10/27/11:17
 */
@Data
public class OUser {
    private String name;
    private String age;

    public String getName(){
        return "小明";
    }
}
