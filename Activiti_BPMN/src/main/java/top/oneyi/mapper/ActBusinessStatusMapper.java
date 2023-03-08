package top.oneyi.mapper;




import org.apache.ibatis.annotations.Mapper;
import top.oneyi.pojo.ActBusinessStatus;
import top.oneyi.util.MyMapper;

/**
 * 业务状态实体Mapper接口
 *
 * @author gssong
 * @date 2021-10-10
 */
@Mapper
public interface ActBusinessStatusMapper extends MyMapper<ActBusinessStatus> {

     ActBusinessStatus selectByProcessInstanceId(String processInstanceId);
}
