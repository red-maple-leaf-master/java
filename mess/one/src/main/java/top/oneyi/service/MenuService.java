package top.oneyi.service;

import top.oneyi.pojo.po.SysMenu;

import java.util.List;

public interface MenuService {
    /**
     * 查询菜单
     *
     * @param userId
     * @return
     */
    List<SysMenu> queryAllSysMenu(Long userId);

    /**
     * 查询父菜单
     *
     * @param parentId
     * @param menuIdList
     * @return
     */
    List<SysMenu> queryListParentId(Long parentId, List<Long> menuIdList);
}
