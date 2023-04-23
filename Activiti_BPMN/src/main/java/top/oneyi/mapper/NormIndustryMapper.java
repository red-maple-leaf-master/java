package top.oneyi.mapper;

import org.apache.ibatis.annotations.Mapper;
import top.oneyi.pojo.NormIndustry;

import java.util.List;

@Mapper
public interface NormIndustryMapper {

    List<NormIndustry> findAll();
}
