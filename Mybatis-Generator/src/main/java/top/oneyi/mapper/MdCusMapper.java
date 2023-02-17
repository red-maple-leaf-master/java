package top.oneyi.mapper;

import java.util.List;
import top.oneyi.pojo.MdCus;

public interface MdCusMapper {
    int deleteByPrimaryKey(String id);

    int insert(MdCus record);

    MdCus selectByPrimaryKey(String id);

    List<MdCus> selectAll();

    int updateByPrimaryKey(MdCus record);
}