package top.oneyi.generator;


import lombok.Data;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: wanyi
 * @Date: 2023/12/13/9:01
 */
@Data
public class User {
    private int id;
    private String nickName;
    private int age;
    private String createTime;
    private String updateTime;
    private String remark;
    private int version;


    public static String generateInsertSql() throws SQLException {
        StringBuilder sql = new StringBuilder("INSERT INTO user (");
        StringBuilder values = new StringBuilder("VALUES (");

        for (Field field : User.class.getDeclaredFields()) {
            if (!field.getName().equals("id")) { // 假设"id"是主键，不需要插入
                sql.append(field.getName()).append(",");
                values.append("?").append(",");
            }
        }

        sql.deleteCharAt(sql.length() - 1); // 删除最后一个逗号
        values.deleteCharAt(values.length() - 1); // 删除最后一个逗号

        sql.append(")").append(values).append(")");

        return sql.toString();
    }

    public static PreparedStatement prepareStatement(Connection conn, List<User> userList) throws SQLException {
        String sql = generateInsertSql();
        PreparedStatement pstmt = conn.prepareStatement(sql);

        for (int i = 0; i < userList.size(); i++) {
            User user = userList.get(i);

            for (Field field : User.class.getDeclaredFields()) {
                if (!field.getName().equals("id")) {
                    try {
                        field.setAccessible(true);
                        Object value = field.get(user);

                        switch (field.getType().getSimpleName()) {
                            case "String":
                                pstmt.setString(i * User.class.getDeclaredFields().length + 1, (String) value);
                                break;
                            case "int":
                                pstmt.setInt(i * User.class.getDeclaredFields().length + 1, (int) value);
                                break;
                            // 添加其他类型的处理...
                            default:
                                throw new SQLException("Unsupported field type: " + field.getType());
                        }
                    } catch (IllegalAccessException e) {
                        throw new SQLException("Failed to access field: " + field.getName(), e);
                    }
                }
            }
        }

        return pstmt;
    }

    // 建表语句
    /*

CREATE TABLE `user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `nick_name` varchar(50) NOT NULL COMMENT '昵称',
  `age` int(11) NOT NULL DEFAULT '20' COMMENT '年龄',
  `create_time` varchar(255) DEFAULT NULL,
  `update_time` varchar(255) DEFAULT NULL,
  `remark` varchar(255) DEFAULT NULL,
  `version` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


     */
}
