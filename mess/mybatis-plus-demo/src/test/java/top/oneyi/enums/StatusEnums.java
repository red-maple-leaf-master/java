package top.oneyi.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.neo4j.register.Register;

@Getter
@AllArgsConstructor
public enum StatusEnums {

    ISDELETE("1","已删除"),
    ISUSER("0","未删除");


    private String code;
    private String name;

    @Override
    public String toString() {
        return "StatusEnums{" +
                "code='" + code + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
