package com.example.demo.entity;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Tolerate;

import java.util.Date;

@Data
@Builder
public class KgDomain {
    private Integer id;
    /**
     * 图谱名称
     */
    private String name;
    /**
     * 唯一标识
     */
    private String label;
    private long nodeCount;
    private long shipCount;
    /**
     * 图谱状态
     */
    private Integer status;
    private String createUser;
    private String modifyUser;
    /**
     *  创建类型
     */
    private Integer type;
    private Integer commend;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 修改时间
     */
    private Date modifyTime;
    @Tolerate
    public KgDomain(){

    }
}
