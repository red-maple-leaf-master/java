package com.example.demo.repository;

import com.example.demo.mapper.KGGraphMapper;
import com.example.demo.utils.Neo4jUtil;

public class KgRepository implements KGGraphMapper {

    /**
     * 在ne4oj中创建一个主节点用唯一标识创建
     * @param label 唯一标识
     */
    public void createDomain(String label) {
        try {
            String cypherSql = String.format(
                    "create (n:`%s`{entityType:0,name:''}) return id(n)", label);
            Neo4jUtil.runCypherSql(cypherSql);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
