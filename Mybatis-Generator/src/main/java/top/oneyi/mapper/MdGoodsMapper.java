package top.oneyi.mapper;

import java.util.List;
import top.oneyi.pojo.MdGoods;

public interface MdGoodsMapper {
    int deleteByPrimaryKey(String id);

    int insert(MdGoods record);

    MdGoods selectByPrimaryKey(String id);

    List<MdGoods> selectAll();

    int updateByPrimaryKey(MdGoods record);
}