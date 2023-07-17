package com.example.demo.controller;



import cn.hutool.core.util.IdUtil;
import com.example.demo.entity.KgDomain;
import com.example.demo.service.KGGraphService;
import com.example.demo.service.KGManagerService;
import com.example.demo.utils.R;
import com.example.demo.utils.ReturnStatus;
import com.example.demo.utils.StringUtil;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/neo4j")
@AllArgsConstructor
public class KGBuilderController extends BaseController{


    private final KGManagerService kgManagerService;

    private  final KGGraphService kgGraphService;

    /**
     * 创建领域标签
     *
     * @param name 图谱名称
     * @param type 创建类型
     * @return
     */
    @RequestMapping(value = "/createDomain")
    public R createDomain(String name, Integer type) {
        try {
            if (!StringUtil.isBlank(name)) {

                List<KgDomain> domainItem = kgManagerService.getDomainByName(name);
                if (domainItem.size() > 0) {
                    return R.create(ReturnStatus.Error, "领域已存在");
                } else {
                    // 生成唯一 标识
                    String label=String.format("%s_%s",name, IdUtil.nanoId(6));
                    int domainId = kgManagerService.quickCreateDomain(label,name, type);// 保存到mysql
                    kgGraphService.createDomain(label);// 保存到图数据
                    return R.success(domainId);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            return R.error(e.getMessage());
        }
        return R.success();
    }
}
