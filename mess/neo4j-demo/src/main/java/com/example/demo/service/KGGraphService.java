package com.example.demo.service;

import com.example.demo.entity.GraphQuery;

import java.util.HashMap;

public interface KGGraphService {

    /**
     * 创建图谱主节点,给节点上默认属性
     *
     * @param label 唯一标识
     */
    void createDomain(String label);

    /**
     * 搜索框查询相关节点和关系
     *
     * @param query 查询条件
     * @return 返回查询结果
     */
    HashMap<String, Object> queryGraphResult(GraphQuery query);
}
