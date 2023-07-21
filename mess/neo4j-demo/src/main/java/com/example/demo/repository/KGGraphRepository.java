package com.example.demo.repository;

import com.example.demo.entity.GraphQuery;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.HashMap;

public interface KGGraphRepository {

    /**
     * 在ne4oj中创建一个主节点用唯一标识创建
     *
     * @param label 唯一标识
     */
    void createDomain(String label);

    /**
     * 查询图谱节点和关系
     *
     * @param query 查询条件
     * @return
     */
    HashMap<String, Object> queryGraphResult(GraphQuery query);
}
