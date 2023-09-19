package top.oneyi.generator.service.impl;

import org.springframework.stereotype.Service;
import top.oneyi.generator.domain.BladeUser;
import top.oneyi.generator.mapper.BladeUserMapper;
import top.oneyi.generator.service.BladeUserService;

import javax.annotation.Resource;
import java.util.List;

/**
* Created with IntelliJ IDEA.
*
* @Author: wanyi
* @Date: 2023/09/19/16:53
*/
@Service
public class BladeUserServiceImpl  implements BladeUserService {

    @Resource
    private BladeUserMapper bladeUserMapper;

    @Override
    public List<BladeUser> getAll(BladeUser bladeUser) {
        return bladeUserMapper.select(bladeUser);
    }

    @Override
    public Boolean save(BladeUser bladeUser) {
        return bladeUserMapper.insert(bladeUser) > 0;
    }

    @Override
    public Boolean updateById(BladeUser bladeUser) {
        return bladeUserMapper.updateByPrimaryKey(bladeUser) > 0;
    }

    @Override
    public Boolean deleteById(String id) {
        return bladeUserMapper.deleteByPrimaryKey(id) > 0;
    }

    @Override
    public BladeUser getById(String id) {
        return bladeUserMapper.selectByPrimaryKey(id);
    }
}
