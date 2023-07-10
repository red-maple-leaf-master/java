package top.oneyi.mapper;

import org.activiti.engine.impl.persistence.entity.TaskEntity;
import org.activiti.engine.task.Task;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Service;
import top.oneyi.pojo.Entry;

import java.util.List;
import java.util.Map;

@Mapper
public interface ActRuTaskMapper {
    /**
     * 根据角色权限名查询任务
     *
     * @param userRole
     * @return
     */
    List<String> selectByassignee(String userRole);


    /*    @Select("select t.NAME_,t.ASSIGNEE_,t.OWNER_,c.MESSAGE_,c.TYPE_,t.START_TIME_ from act_hi_taskinst as t " +
                "left join act_hi_comment as c on t.ID_ = c.TASK_ID_")*/
    public List<Map<String, String>> list();
}
