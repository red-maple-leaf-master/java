package top.oneyi.mapper;

import org.springframework.stereotype.Repository;
import top.oneyi.pojo.po.SysMenu;

import java.util.List;

@Repository
public interface MenuMapper {
    /**查询用户的所有菜单ID
     * @param userId
     * @return
     */
    List<Long> queryAllMenuId(Long userId);
    /**根据父级菜单查询子菜单
     * @param parentId
     * @return
     */
    List<SysMenu> queryListParentId(Long parentId);
}
