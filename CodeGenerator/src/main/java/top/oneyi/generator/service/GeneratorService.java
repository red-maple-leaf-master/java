package top.oneyi.generator.service;

import top.oneyi.generator.domain.GenTable;
import top.oneyi.generator.domain.Generator;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: wanyi
 * @Date: 2023/09/20/10:41
 */
public interface GeneratorService {
    /**
     * 预览代码
     * @param generator        生成代码所需作者包名等数据
     * @return
     */
    Map<String, Object> preview(Generator generator);

    /**
     *  获取数据库表列表
     * @param genTable
     * @return
     */
    List<GenTable> tableList(GenTable genTable);
}
