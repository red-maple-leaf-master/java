package top.oneyi.mapper;

import org.activiti.engine.impl.persistence.entity.TaskEntity;
import org.activiti.engine.task.Task;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ActRuTaskMapper {
    /**
     * 根据角色权限名查询任务
     * @param userRole
     * @return
     */
    List<String> selectByassignee(String userRole);
}
