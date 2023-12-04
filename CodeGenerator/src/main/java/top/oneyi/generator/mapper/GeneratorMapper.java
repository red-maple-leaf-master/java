package top.oneyi.generator.mapper;

import org.apache.ibatis.annotations.Mapper;
import top.oneyi.generator.domain.GenTable;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: wanyi
 * @Date: 2023/09/20/10:41
 */
@Mapper
public interface GeneratorMapper {

    List<GenTable> tableList(GenTable genTable,int page,int size);
}
