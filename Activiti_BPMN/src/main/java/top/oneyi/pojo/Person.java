package top.oneyi.pojo;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class Person {
    private Long id;
    private String name;

    private int age;

}
