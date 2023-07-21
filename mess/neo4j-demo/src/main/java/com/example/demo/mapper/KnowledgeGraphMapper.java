package com.example.demo.mapper;


import com.example.demo.entity.KgDomain;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface KnowledgeGraphMapper {
    /**
     * 根据图谱名字查找
     *
     * @param domainName
     * @return
     */
    List<KgDomain> getDomainByName(String domainName);

    /**
     * 保存图谱名字信息
     *
     * @param domain
     */
    void saveDomain(KgDomain domain);
}
