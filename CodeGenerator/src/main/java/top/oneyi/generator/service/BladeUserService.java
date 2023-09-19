package top.oneyi.generator.service;

import top.oneyi.generator.domain.BladeUser;

import java.util.List;

public interface BladeUserService{

    List<BladeUser> getAll(BladeUser bladeUser);

    Boolean save(BladeUser bladeUser);

    Boolean updateById(BladeUser bladeUser);

    Boolean deleteById(String id);

    BladeUser getById(String id);

}
