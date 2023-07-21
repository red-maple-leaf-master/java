package com.example.demo.service;

import com.example.demo.entity.GraphQuery;
import com.example.demo.repository.KgRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;

@Service

public class KGGraphServiceimpl implements KGGraphService {

    @Resource
    private KgRepository kgRepository;

    /**
     * 创建图谱主节点,给节点上默认属性
     *
     * @param label 唯一标识
     */
    @Override
    public void createDomain(String label) {
        kgRepository.createDomain(label);
    }

    /**
     * 搜索框查询相关节点和关系
     *
     * @param query 查询条件
     * @return 返回查询结果
     */
    @Override
    public HashMap<String, Object> queryGraphResult(GraphQuery query) {
        return kgRepository.queryGraphResult(query);
    }
}
