package top.oneyi.generator;

import lombok.Data;

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

    // 建表语句
    /*


     CREATE TABLE `user` (
     `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键id',
     `nick_name` varchar(50) NOT NULL COMMENT '昵称',
     `age` int(11) NOT NULL DEFAULT '20' COMMENT '年龄',
     PRIMARY KEY (`id`)
     ) ENGINE=InnoDB AUTO_INCREMENT=1000001 DEFAULT CHARSET=utf8;


     */
}
