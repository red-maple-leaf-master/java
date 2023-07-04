package top.oneyi.pojo.po;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class OneUser {


    private Long id;

    @NotNull
    private String name;

    @NotNull
    private Integer age;

}
