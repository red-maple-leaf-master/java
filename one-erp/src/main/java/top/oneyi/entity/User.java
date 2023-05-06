package top.oneyi.entity;

import lombok.Data;
/**
 *  用户表
 * @author oneyi
 * @date 2023/5/6
 */
@Data
public class User {
    private Long id;

    private String username;

    private String loginName;

    private String password;

    private String position;

    private String department;

    private String email;

    private String phonenum;

    private Byte ismanager;

    private Byte isystem;

    private Byte status;

    private String description;

    private String remark;

    private Long tenantId;

}