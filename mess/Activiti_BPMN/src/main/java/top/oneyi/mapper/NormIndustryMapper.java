package top.oneyi.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import top.oneyi.pojo.NormIndustry;

import java.util.List;

@Mapper
public interface NormIndustryMapper {

    List<NormIndustry> findAll();

    NormIndustry findByCode(String code);

    List<NormIndustry> findByParentCode(String parentCode);

    List<NormIndustry> findByParentCodes(List<String> parentCode);

    List<NormIndustry> findByCodeToList();

    List<NormIndustry> getAllFater();
}
