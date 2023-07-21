package com.example.demo.service;

import com.example.demo.entity.KgDomain;
import com.example.demo.mapper.KnowledgeGraphMapper;
import com.example.demo.utils.DateUtil;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class KGManagerServiceimpl implements KGManagerService {

    @Resource
    private KnowledgeGraphMapper knowledgeGraphMapper;

    /**
     * 根据图谱名字查找
     *
     * @param domainName
     * @return
     */
    @Override
    public List<KgDomain> getDomainByName(String domainName) {
        return knowledgeGraphMapper.getDomainByName(domainName);
    }

    /**
     * 保存图谱名称
     *
     * @param label      唯一标识
     * @param domainName 图谱名称
     * @param type       创建类型
     * @return
     */
    @Override
    public int quickCreateDomain(String label, String domainName, Integer type) {
        KgDomain item = new KgDomain();
        item.setName(domainName);
        item.setLabel(label);
        item.setNodeCount(0);
        item.setShipCount(0);
        item.setCreateUser("tc");
        item.setCommend(0);
        item.setType(type);
        item.setStatus(1);
        return saveDomain(item);
    }

    /**
     * 根据实体保存图谱
     *
     * @param domain
     * @return
     */
    @Override
    public Integer saveDomain(KgDomain domain) {
        domain.setCreateTime(DateUtil.getDateNow());
        domain.setModifyTime(DateUtil.getDateNow());
        knowledgeGraphMapper.saveDomain(domain);
        return domain.getId();
    }
}
