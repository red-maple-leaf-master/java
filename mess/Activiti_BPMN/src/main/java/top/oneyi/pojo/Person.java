package top.oneyi.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Person {

    private Long id;
    @NotNull
    private String name;
    @NotNull
    @Min(0)
    private int age;

}
