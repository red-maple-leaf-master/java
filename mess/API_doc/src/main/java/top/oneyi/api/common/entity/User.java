package top.oneyi.api.common.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
// @ApiModel用在类上，通常是实体类，表示一个返回响应数据的信息
@ApiModel(description = "用户实体")
public class User {
    /**
     * @ApiModelProperty用在属性上，描述响应类的属性
     */
    @ApiModelProperty(value = "主键")
    private int id;

    @ApiModelProperty(value = "姓名")
    private String name;

    @ApiModelProperty(value = "年龄")
    private int age;

    @ApiModelProperty(value = "地址")
    private String address;
}

