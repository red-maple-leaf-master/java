package top.oneyi.pojo;

import lombok.Data;
import top.oneyi.annotation.UpdateBy;
import top.oneyi.annotation.UpdateDate;

@Data
public class Person {

    @UpdateDate()
    private Data createTime;
    @UpdateDate()
    private Data updateTime;
    @UpdateBy()
    private String createBy;
    @UpdateBy()
    private String updateBy;
}
