package com.example.demo.service;

import com.example.demo.entity.KgDomain;

import java.util.List;

public interface KGManagerService {
    /**
     * 根据图谱名字查找
     * @param domainName
     * @return
     */
    List<KgDomain> getDomainByName(String domainName);

    /**
     * 保存图谱名称
     * @param label 唯一标识
     * @param domain 图谱名称
     * @param type 创建类型
     * @return
     */
    int quickCreateDomain(String label, String domain, Integer type);

    /**
     * 根据实体保存图谱
     * @param domain
     * @return
     */
    Integer saveDomain(KgDomain domain);
}
