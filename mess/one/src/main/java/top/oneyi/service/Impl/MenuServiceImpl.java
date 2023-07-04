package top.oneyi.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.oneyi.common.Constant;
import top.oneyi.mapper.MenuMapper;
import top.oneyi.pojo.po.SysMenu;
import top.oneyi.service.MenuService;

import java.util.ArrayList;
import java.util.List;

@Service
public class MenuServiceImpl implements MenuService {

    @Autowired
    private MenuMapper menuMapper;

    /**
     * 查询菜单
     *
     * @param userId
     * @return
     */
    @Override
    public List<SysMenu> queryAllSysMenu(Long userId) {
        List<Long> menuList= menuMapper.queryAllMenuId(userId);
        return getAllSysMenuList(menuList);
    }

    /**
     * 查询父菜单
     *
     * @param parentId
     * @param menuIdList
     * @return
     */
    @Override
    public List<SysMenu> queryListParentId(Long parentId, List<Long> menuIdList) {
        List<SysMenu> sysMenuList = menuMapper.queryListParentId(parentId);
        if(menuIdList == null){
            return sysMenuList;
        }
        List<SysMenu> userMenuList = new ArrayList<>();
        for(SysMenu sysMenu : sysMenuList){
            if(menuIdList.contains(sysMenu.getId())){
                userMenuList.add(sysMenu);
            }
        }
        return userMenuList;
    }

    /**
     * 获取所有菜单列表
     */
    private List<SysMenu> getAllSysMenuList(List<Long> menuIdList){
        //查询根菜单列表
        List<SysMenu> sysMenuList = queryListParentId(0L, menuIdList);
        //递归获取子菜单
        getSysMenuTreeList(sysMenuList, menuIdList);
        return sysMenuList;
    }
    /**
     * 递归
     */
    private List<SysMenu> getSysMenuTreeList(List<SysMenu> sysMenuList, List<Long> menuIdList){
        List<SysMenu> subMenuList = new ArrayList<>();

        for(SysMenu sysMenu : sysMenuList){
            //目录
            if(sysMenu.getType() == Constant.MenuType.CATALOG.getValue()){
                sysMenu.setList(getSysMenuTreeList(queryListParentId(sysMenu.getId(), menuIdList), menuIdList));
            }
            subMenuList.add(sysMenu);
        }
        return subMenuList;
    }
}
