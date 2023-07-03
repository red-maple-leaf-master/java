package top.oneyi.api.common.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
// @ApiModel用在类上，通常是实体类，表示一个返回响应数据的信息
@ApiModel(description = "菜单实体")
public class Menu {
    // @ApiModelProperty用在属性上，描述响应类的属性
    @ApiModelProperty(value = "主键")
    private int id;

    @ApiModelProperty(value = "菜单名称")
    private String name;
}
